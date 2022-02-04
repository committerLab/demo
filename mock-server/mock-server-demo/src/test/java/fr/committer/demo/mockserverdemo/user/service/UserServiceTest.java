package fr.committer.demo.mockserverdemo.user.service;

import fr.committer.demo.mockserverdemo.domain.User;
import fr.committer.demo.mockserverdemo.repository.UserRepository;
import fr.committer.demo.mockserverdemo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Test
    void shouldFindUserById_ok(){
        // GIVEN
        Integer userId = 1;
        Mockito.when(userRepository.findById(Mockito.eq(1)))
                .thenReturn(Optional.of(
                        User.builder()
                                .id(1)
                                .firstName("Homer")
                                .lastName("SIMPSON")
                                .phone("0111223344")
                                .email("azert@gmail.com")
                                .address("11 rue de la rue")
                                .country("France")
                                .zipCode("12345")
                                .birthDate(LocalDate.of(2000, 1, 12))
                                .build()
                ));
        // WHEN
        Optional<User> user = userService.findUserById(userId);
        // THEN
        Assertions.assertTrue(user.isPresent());
        user.ifPresent(user1 -> {
            Assertions.assertEquals(1, user1.getId());
            Assertions.assertEquals("Homer", user1.getFirstName());
            Assertions.assertEquals("SIMPSON", user1.getLastName());
            Assertions.assertEquals("0111223344", user1.getPhone());
            Assertions.assertEquals("azert@gmail.com", user1.getEmail());
            Assertions.assertEquals("11 rue de la rue", user1.getAddress());
            Assertions.assertEquals("France", user1.getCountry());
            Assertions.assertEquals("12345", user1.getZipCode());
            Assertions.assertEquals(LocalDate.of(2000, 1, 12), user1.getBirthDate());
        });
    }
}
