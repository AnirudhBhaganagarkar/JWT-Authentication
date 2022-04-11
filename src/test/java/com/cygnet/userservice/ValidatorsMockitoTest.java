package com.cygnet.userservice;

import com.cygnet.userservice.entity.User;
import com.cygnet.userservice.repositorys.UserRepository;
import com.cygnet.userservice.validators.CheckMailIDValidator;
import com.cygnet.userservice.validators.UniqueMailIDValidator;
import com.cygnet.userservice.validators.UniqueUsernameValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.*;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ValidatorsMockitoTest.class})
class ValidatorsMockitoTest
{
    private static Validator validator;

    @Mock
    private UniqueUsernameValidator uniqueUsernameValidator;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Mock
    private static UserRepository userRepository;


    @BeforeEach
    public void setUp()
    {
        ConstraintValidatorFactory cvf = mock(ConstraintValidatorFactory.class);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @Order(1)
    void UniqueUsernameValidatorTest()
    {
        ConstraintValidatorFactory cvf = mock(ConstraintValidatorFactory.class);
        when(cvf.getInstance(UniqueUsernameValidator.class)).thenReturn(new UniqueUsernameValidator(userRepository));

        validator = Validation.buildDefaultValidatorFactory()
                .usingContext()
                .constraintValidatorFactory(cvf)
                .getValidator();

    }


    @Test
    @Order(2)
    void UniqueUsernameValidatorIsValidTest()
    {
        String name="anirudh";
        User user = new User();
        when(userRepository.findByName(name)).thenReturn(Optional.of(new User(name)));

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals(0,constraintViolations.size());

        UniqueUsernameValidator mock = mock(UniqueUsernameValidator.class);
        mock.isValid("ankush",constraintValidatorContext);
        when(mock.isValid("ankush",constraintValidatorContext)).thenReturn(true);
        verify(mock).isValid("ankush",constraintValidatorContext);
    }

    @Test
    @Order(3)
    void UniqueMailIDValidatorTest()
    {
        ConstraintValidatorFactory cvf = mock(ConstraintValidatorFactory.class);
        when(cvf.getInstance(UniqueMailIDValidator.class)).thenReturn(new UniqueMailIDValidator(userRepository));

        validator = Validation.buildDefaultValidatorFactory()
                .usingContext()
                .constraintValidatorFactory(cvf)
                .getValidator();
    }


    @Test
    @Order(4)
    void CheckMailIDValidatorTest()
    {
        ConstraintValidatorFactory cvf = mock(ConstraintValidatorFactory.class);
        when(cvf.getInstance(CheckMailIDValidator.class)).thenReturn(new CheckMailIDValidator(userRepository));

        validator = Validation.buildDefaultValidatorFactory()
                .usingContext()
                .constraintValidatorFactory(cvf)
                .getValidator();
    }

    @Test
    void CheckMailIDValidatorisValidTest()
    {
        CheckMailIDValidator mock = mock(CheckMailIDValidator.class);
        mock.isValid("Anirudh@gmail.com", constraintValidatorContext);
        when(mock.isValid("Anirudh@gmail.com",constraintValidatorContext)).thenReturn(true);
    }
}
