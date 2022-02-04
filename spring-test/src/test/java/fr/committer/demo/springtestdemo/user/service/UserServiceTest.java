package fr.committer.demo.springtestdemo.user.service;

import fr.committer.demo.springtestdemo.user.domain.User;
import fr.committer.demo.springtestdemo.user.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldGetListOf2Users_ok(){
        Mockito.when(userRepository.findAll()).thenReturn(getListOfUsers());
        List<User> users = userService.getAllUsers();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(5, users.get(0).getId());
        Assertions.assertEquals("Porter", users.get(0).getFirstName());
        Assertions.assertEquals("Upton", users.get(0).getLastName());
        Assertions.assertEquals("02 21 25 83 76", users.get(0).getPhone());
        Assertions.assertEquals("porter.upton8941@icloud.ca", users.get(0).getEmail());
        Assertions.assertEquals("894-1234 Enim St.", users.get(0).getAddress());
        Assertions.assertEquals("Costa Rica", users.get(0).getCountry());
        Assertions.assertEquals("18582", users.get(0).getZipCode());
        Assertions.assertEquals(LocalDate.of(1945, 8, 17), users.get(0).getBirthDate());

        Assertions.assertEquals(4, users.get(1).getId());
        Assertions.assertEquals("Jacobson", users.get(1).getFirstName());
        Assertions.assertEquals("Priscilla", users.get(1).getLastName());
        Assertions.assertEquals("08 31 38 45 01", users.get(1).getPhone());
        Assertions.assertEquals("priscilla-jacobson937@yahoo.net", users.get(1).getEmail());
        Assertions.assertEquals("362-8078 Mi. Rd.", users.get(1).getAddress());
        Assertions.assertEquals("Sweden", users.get(1).getCountry());
        Assertions.assertEquals("34956", users.get(1).getZipCode());
        Assertions.assertEquals(LocalDate.of(1980,2,12), users.get(1).getBirthDate());

    }
    private List<User> getListOfUsers(){
        return Lists.newArrayList(
                createUser(
                        5,
                        "Porter" ,
                        "Upton" ,
                        "02 21 25 83 76" ,
                        "porter.upton8941@icloud.ca" ,
                        "894-1234 Enim St." ,
                        "Costa Rica" ,
                        "18582",
                        LocalDate.of(1945, 8, 17)),
                createUser(4,
                        "Jacobson" ,
                        "Priscilla" ,
                        "08 31 38 45 01" ,
                        "priscilla-jacobson937@yahoo.net" ,
                        "362-8078 Mi. Rd." ,
                        "Sweden" ,
                        "34956",
                        LocalDate.of(1980,2,12))
        );
    }
    private User createUser(Integer id, String firstName, String lastName, String phone, String email, String address, String country, String zipCode, LocalDate birthDate){
        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .address(address)
                .country(country)
                .zipCode(zipCode)
                .birthDate(birthDate)
                .build();
    }
}
