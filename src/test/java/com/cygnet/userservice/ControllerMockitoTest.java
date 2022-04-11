package com.cygnet.userservice;

import com.cygnet.userservice.dto.AddressDto;
import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.controllers.MyController;
import com.cygnet.userservice.mapper.UserMapper;
import com.cygnet.userservice.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.cygnet.userservice")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockitoTest.class})
class ControllerMockitoTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @InjectMocks
    private MyController myController;

    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }


    @Test
    @Order(1)
    void test_findAllUser() throws Exception
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

        when(userService.getAllUsers()).thenReturn(dtoList);

        this.mockMvc.perform(get("/allusers"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Order(2)
    void test_saveuser() throws Exception
    {
       // for test this we have to remove @Valid in the controller

        AddressDto addressDto = new AddressDto(1, "mainrode", "ravinagar", 445305, "amravti", "maharashtra", "india");
        UserDto userDto = new UserDto(1,"ajay","Anirudh@gmail.com","Anirudh@123","9158652001",addressDto);
        when(userService.addUser(userDto)).thenReturn(userMapper.userDtoToMobel(userDto));
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody =  mapper.writeValueAsString(userDto);

        this.mockMvc.perform(post("/adduser")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }


    @Test
    @Order(3)
    void test_getById() throws Exception
    {
        AddressDto addressDto = new AddressDto(1, "mainrode", "ravinagar", 445305, "amravti", "maharashtra", "india");
        UserDto userDto = new UserDto(1,"anirudh","Anirudh@gmail.com","Anirudh@123","9158652001",addressDto);
        long id = 1;
        when(userService.findUserById(id)).thenReturn(userDto);
        this.mockMvc.perform(get("/userbyid/{id}",id))
                .andExpect(status().isOk())
                        .andDo(print());

    }


    @Test
    @Order(4)
    void test_getByName() throws Exception
    {
        AddressDto addressDto = new AddressDto(1, "mainrode", "ravinagar", 445305, "amravti", "maharashtra", "india");
        UserDto userDto = new UserDto(1,"anirudh","Anirudh@gmail.com","Anirudh@123","9158652001",addressDto);
        String name="anirudh";
        when(userService.findUserByName(name)).thenReturn(userDto);

        this.mockMvc.perform(get("/userbyname/{name}",name))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @Order(5)
    void test_findByEmail() throws Exception {
        AddressDto addressDto = new AddressDto(1, "mainrode", "ravinagar", 445305, "amravti", "maharashtra", "india");
        UserDto userDto = new UserDto(1,"anirudh","anirudh@gmail.com","Anirudh@123","9158652001",addressDto);
        String email = "anirudh@gmail.com";

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody =  mapper.writeValueAsString(userDto);

        when(userService.findByEmail(email)).thenReturn(userDto);

        this.mockMvc.perform(post("/forgot-password")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

}
