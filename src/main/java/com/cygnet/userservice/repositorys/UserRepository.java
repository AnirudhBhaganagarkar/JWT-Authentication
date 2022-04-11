package com.cygnet.userservice.repositorys;


import com.cygnet.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findUserByName(String username);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findUserByEmail(String email);

  //  Optional<User> resetPassword(String email, String password);

}
