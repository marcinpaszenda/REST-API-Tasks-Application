package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    void getAllTasksTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1L, "Task1", "This is task 1");
        Task task2 = new Task(2L, "Task2", "This is task 2");
        taskList.add(task1);
        taskList.add(task2);
        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(2, resultList.size());
    }

    @Test
    void saveTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Task no 1", "This is task no 1");
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Task updatedTask = dbService.getTask(1L);
        updatedTask.setTitle("Updated Task no 1");
        dbService.saveTask(updatedTask);
        //Then
        assertEquals("Updated Task no 1", updatedTask.getTitle());
    }

    @Test
    void  getTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Task no 1", "This is task no 1");
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Task result = dbService.getTask(1L);
        //Then
        assertEquals(task, result);
    }
}
