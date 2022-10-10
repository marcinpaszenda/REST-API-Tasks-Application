package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskMapperTestSuite {

    @Test
    void mapToTaskTest() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "TaskTitle", "TaskContent" );
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(task.getId());
        assertEquals("TaskTitle", task.getTitle());
        assertEquals("TaskContent", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "TaskTitle2", "TaskContent2");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDto.getId());
        assertEquals("TaskTitle2", taskDto.getTitle());
        assertEquals("TaskContent2", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "TaskTitle3", "TaskContent3");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskDtoList.get(0).getId());
        assertEquals(1, taskDtoList.size());
        assertEquals("TaskTitle3", taskDtoList.get(0).getTitle());
        assertEquals("TaskContent3", taskDtoList.get(0).getContent());
    }
}
