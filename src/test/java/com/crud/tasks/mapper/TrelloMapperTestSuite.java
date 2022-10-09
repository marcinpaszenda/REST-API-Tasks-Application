package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper;
    private  TrelloBoard trelloBoard;
    private TrelloBoardDto trelloBoardDto;
    private TrelloList trelloList;
    private TrelloListDto trelloListDto;
    private TrelloCard trelloCard;
    private TrelloCardDto trelloCardDto;
    private List<TrelloBoard> trelloBoardList;
    private List<TrelloBoardDto> trelloBoardDtoList;
    private List<TrelloList> trelloListList;
    private List<TrelloListDto> trelloListDtoList;

    @BeforeEach
    void beforeEachTest() {

        trelloMapper = new TrelloMapper();
        trelloBoardList = new ArrayList<>();
        trelloBoardDtoList = new ArrayList<>();
        trelloListList = new ArrayList<>();
        trelloListDtoList = new ArrayList<>();

        trelloBoard = new TrelloBoard("2", "testTrelloBoard", trelloListList);
        trelloBoardDto = new TrelloBoardDto("5", "testTrelloBoardDto", trelloListDtoList);

        trelloListDto = new TrelloListDto("55", "testTrelloListDto", true);
        trelloList = new TrelloList("22", "testTrelloList", false);

        trelloCard = new TrelloCard("testTrelloCard", "test description", "test pos", "test listId");
        trelloCardDto = new TrelloCardDto("testTrelloCardDto", "description test", "pos test", "listId test");
    }

    @Test
    void mapToBoardsTest() {
        //Given
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals("5", trelloBoardList.get(0).getId());
        assertEquals("testTrelloBoardDto", trelloBoardList.get(0).getName());
        assertEquals(1, trelloBoardList.size());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        trelloBoardList.add(trelloBoard);
        //When
        trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals("2", trelloBoardDtoList.get(0).getId());
        assertEquals("testTrelloBoard", trelloBoardDtoList.get(0).getName());
        assertEquals(1, trelloBoardDtoList.size());
    }

    @Test
    void mapToListTest() {
        //Given
        trelloListDtoList.add(trelloListDto);
        //When
        trelloListList = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals("55", trelloListList.get(0).getId());
        assertEquals("testTrelloListDto", trelloListList.get(0).getName());
        assertTrue(trelloListList.get(0).isClosed());
        assertEquals(1, trelloListList.size());
    }

    @Test
    void mapToListDto() {
        //Given
        trelloListList.add(trelloList);
        //When
        trelloListDtoList = trelloMapper.mapToListDto(trelloListList);
        //Then
        assertEquals("22", trelloListDtoList.get(0).getId());
        assertEquals(1, trelloListDtoList.size());
        assertFalse(trelloListDtoList.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        //When
        trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("testTrelloCard", trelloCardDto.getName());
        assertEquals("test description", trelloCardDto.getDescription());

    }

    @Test
    void mapToCardTest() {
        //Given
        //When
        trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("testTrelloCardDto", trelloCard.getName());
        assertEquals("pos test", trelloCard.getPos());
    }
}
