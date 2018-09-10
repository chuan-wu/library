/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.repository;

import chuan.wu.library.LibraryApplicationTests;
import chuan.wu.library.enums.UserStatus;
import chuan.wu.library.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryTest extends LibraryApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setStatus(UserStatus.ACTIVE.getStatus());
        user.setUsername("John");

        userRepository.save(user);
        Optional<User> userOptional = userRepository.findById(user.getId());
        Assert.assertTrue(userOptional.isPresent());
    }

}