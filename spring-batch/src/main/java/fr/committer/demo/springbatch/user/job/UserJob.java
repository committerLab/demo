package fr.committer.demo.springbatch.user.job;

import fr.committer.demo.springbatch.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.reactive.TransactionContextManager;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
@Slf4j
public class UserJob extends DefaultBatchConfigurer {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    private String fileOutput = "users.csv";

    /*
     * STEP 1
     */
    @Bean
    public Step step(JdbcCursorItemReader<User> reader, FlatFileItemWriter writer) {
        return stepBuilderFactory.get("step")
                .<User, User> chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public JdbcCursorItemReader<User> reader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<User>()
                .name("cursorItemReader")
                .dataSource(dataSource)
                .sql("SELECT first_name, last_name, phone, email, address, zip_code, country, birth_date FROM user")
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .build();
    }

    @Bean
    public FlatFileItemWriter<User> writer() {
        BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"firstName", "lastName", "phone", "email", "address", "zipCode", "country", "birthDate"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<User> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileItemWriter<User>    flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setName("personItemWriter");
        flatFileItemWriter.setResource(new FileSystemResource("users.csv"));
        flatFileItemWriter.setLineAggregator(lineAggregator);

        return flatFileItemWriter;
    }
    /**
     * Création du Job
     * @param step
     * @return
     */
    @Bean
    public Job importUserJob(Step step) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
