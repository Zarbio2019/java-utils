package com.appsdeveloperblog.tutorials.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // disable test
@SpringBootTest
class UsersServiceSpringBootApplicationTest {
    // to test the @SpringBootApplication (UsersServiceSpringBootApplication.java)
    @Test
    void contextLoads() {
    }
}