package com.cygnet.userservice;

import com.cygnet.userservice.dto.AddressDto;
import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.entity.Address;
import com.cygnet.userservice.entity.User;
import com.cygnet.userservice.mapper.UserMapper;
import com.cygnet.userservice.repositorys.UserRepository;
import com.cygnet.userservice.services.UserServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMakitoTest.class})
class ServiceMakitoTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @Order(1)
    void getAllUsersTest()
    {

        List<UserDto> dtoList = new ArrayList<UserDto>();

        AddressDto addressDto = new AddressDto();
        addressDto.setId(1);
        addressDto.setStreet("jghf");
        addressDto.setArea("gytyr");
        addressDto.setPostalcode(445305);
        addressDto.setCity("ytre");
        addressDto.setState("iuytd");
        addressDto.setCountry("uytrs");

        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("anirudh");
        userDto.setEmail("Anirudh@gmail.com");
        userDto.setPassword("anirudh@123");
        userDto.setMobile("9158653002");
        userDto.setAddress(userDto.getAddress());

        dtoList.add(userDto);

        when(userMapper.userModelsToDtos(userRepository.findAll())).thenReturn(dtoList);
        assertEquals(1,userService.getAllUsers().size());
    }

    @Test
    @Order(2)
    void addUserTest()
    {
        AddressDto addressDto = new AddressDto(1, "mainrode", "ravinagar", 445305, "amravti", "maharashtra", "india");
        UserDto userDto = new UserDto(1,"anirudh","Anirudh@gmail.com","Anirudh@123","9158652001",addressDto);

        User user1 = null;
        Address address = new Address(1,"main" ,"nagar",445305,"yavatmal","mh","india",user1);
        User user = new User(1,"ankush","Anirudh@gmail.com","Ankush@123","9158652001",address);

        when(userRepository.save(userMapper.userDtoToMobel(userDto))).thenReturn(user);
        assertEquals(user,userService.addUser(userDto));

    }


    @Test
    @Order(3)
    void findUserByIdTest()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        long id=1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(userMapper.userMmodelToDto(Optional.of(user)),userService.findUserById(id));
    }


    @Test
    @Order(4)
    void findUserByNameTest()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        String name="ankush";

        when(userRepository.findByName(name)).thenReturn(Optional.of(user));
        assertEquals(userMapper.userMmodelToDto(Optional.of(user)),userService.findUserByName(name));

    }

    @Test
    @Order(5)
    void findByEmailTest()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        String email = "Anirudh@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        assertEquals(userMapper.userMmodelToDto(Optional.of(user)),userService.findByEmail(email));

    }


    @Test
    @Order(6)
    void findUserByIdTest2()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        long id=1235L;


        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertEquals(userMapper.userMmodelToDto((User)  null),userService.findUserById(id));
    }


    @Test
    @Order(7)
    void findUserByNameTest1()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        String name="ankushajuhduhyd";

        when(userRepository.findByName(name)).thenReturn(Optional.empty());
        assertEquals(userMapper.userMmodelToDto((User) null),userService.findUserByName(name));

    }


    @Test
    @Order(8)
    void findByEmailTest1()
    {
        User user1 = null;
        Address address = new Address(1, "main", "nagar", 445305, "yavatmal", "mh", "india", user1);
        User user = new User(1, "ankush","Anirudh@gmail.com", "Ankush@123", "9158652001", address);
        String email = "Anirudhihuygf@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertEquals(userMapper.userMmodelToDto((User) null),userService.findByEmail(email));

    }


}
