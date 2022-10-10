package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloValidatorTestSuite {

    @Test
    void validateTrelloBoardsTest() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "TrelloBoardName1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("2", "TrelloBoardName2", new ArrayList<>()));
        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        assertEquals(2, result.size());
        assertEquals("TrelloBoardName1", result.get(0).getName());
    }
}
