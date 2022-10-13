package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    void shouldGetEmptyAllTasks() throws Exception {
        //Given
        when(dbService.getAllTasks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L, "New Task", "Task Content"));
        List<Task> taskList = List.of(new Task(1L, "New Task", "Task Content"));

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("New Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Task Content")));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(10L, "Task Test", "Task Content Test");
        TaskDto taskDto = new TaskDto(10L, "Task Test", "Task Content Test");

        when(dbService.getTask(10L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Task Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Task Content Test")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(10L, "Task Test", "Task Content Test");
        TaskDto taskDto = new TaskDto(10L, "Task Test", "Task Content Test");
        Task updatedTask = new Task(2L, "Updated Task", "Updated Content");
        TaskDto updatedTaskDto = new TaskDto(2L, "Updated Task", "Updated Content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(updatedTask);
        when(taskMapper.mapToTaskDto(any())).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTaskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Updated Task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Updated Content")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(10L, "Task Test", "Task Content Test");
        TaskDto taskDto = new TaskDto(10L, "Task Test", "Task Content Test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
