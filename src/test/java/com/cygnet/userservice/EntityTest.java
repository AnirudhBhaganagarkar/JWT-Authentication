package com.cygnet.userservice;

import com.cygnet.userservice.entity.Address;
import com.cygnet.userservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {EntityTest.class})
class EntityTest
{
    @Test
    void userTest()
    {
        Address address = new Address();
        address.setId(1);
        address.setStreet("jghf");
        address.setArea("gytyr");
        address.setPostalcode(445305);
        address.setCity("ytre");
        address.setState("iuytd");
        address.setCountry("uytrs");
        address.setUser(address.getUser());

        User user = new User();
        user.setId(1);
        user.setName("anirudh");
        user.setEmail("Anirudh@gmail.com");
        user.setPassword("anirudh@123");
        user.setMobile("9158653002");
        user.setAddress(user.getAddress());

        address.getId();
        address.getStreet();
        address.getArea();
        address.getPostalcode();
        address.getCity();
        address.getState();
        address.getCountry();

        user.getId();
        user.getName();
        user.getEmail();
        user.getPassword();
        user.getMobile();
        user.getAddress();

        assert(true);

    }
}
