package com.cygnet.userservice;

import com.cygnet.userservice.dto.JwtRequest;
import com.cygnet.userservice.controllers.JwtController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.cygnet.userservice")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {JwtControllerMockitoTest.class})
class JwtControllerMockitoTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    @InjectMocks
    private JwtController jwtController;


    @BeforeEach
    void setUp()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @WithMockUser("/login")
    @Test
    @Order(1)
    void testgenerateToken() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("Roman", "Roman@123");

        ObjectMapper mapper = new ObjectMapper();

        String jsonbody = mapper.writeValueAsString(jwtRequest);

      MvcResult result = mockMvc
                .perform(post("/token").content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());



 this.mockMvc.perform(post("/token")
                .content(jsonbody)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }



    @WithMockUser("/login")
    @Test
    @Order(2)
    void testgenerateToken1() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("112Roman", "Roman@123");

        ObjectMapper mapper = new ObjectMapper();

        String jsonbody = mapper.writeValueAsString(jwtRequest);

        MvcResult result = mockMvc
                .perform(post("/token").content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
        assertEquals(401, result.getResponse().getStatus());



        this.mockMvc.perform(post("/token")
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }


}
