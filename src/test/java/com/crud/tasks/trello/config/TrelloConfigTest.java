package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void getTrelloApiEndpointTest() {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        assertEquals("https://api.trello.com/1", trelloApiEndpoint);
    }

    @Test
    void getTrelloUsernameTest() {
        //Given
        //When
        String trelloUsername = trelloConfig.getTrelloUsername();
        //Then
        assertEquals("marcinpaszenda", trelloUsername);
    }

    @Test
    void getTrelloAppKeyTest() {
        //Given
        //When
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        //Then
        assertEquals("369b351664caf530984a3318ef050610", trelloAppKey);
    }

    @Test
    void getTrelloTokenTest() {
        //Given
        //When
        String trelloToken = trelloConfig.getTrelloToken();
        //Then
        assertEquals("d757dd4909c8b6251374e6ef1040feb1f928b6435d56b599506a6ae8c109a560", trelloToken);
    }
}
