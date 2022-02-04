package fr.committer.demo.kafkastream.user;

import fr.committer.demo.kafkastream.user.domain.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserProcessor {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user) {
        kafkaTemplate.send("input-topic", user)
                .addCallback(
                        result -> log.info("Message sent to topic: {}", user.getFirstName()),
                        ex -> log.error("Failed to send message", ex)
                );
    }
}
