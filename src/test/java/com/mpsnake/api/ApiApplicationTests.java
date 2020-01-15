package com.mpsnake.api;

import com.mpsnake.api.controller.PlayerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private PlayerController playerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
    }
}
