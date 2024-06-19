package com.example.projecttask.controller;

import com.example.projecttask.Controller.ProjectController;
import com.example.projecttask.Model.Project;
import com.example.projecttask.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProjects_whenServiceThrowsException_returnsEmptyList() {
        // Arrange
        when(projectService.getAllProjects()).thenThrow(new RuntimeException("Service Exception"));

        // Act
        ResponseEntity<List<Project>> responseEntity = projectController.getAllProjects();

        // Assert
        assertNull(responseEntity);
    }

    @Test
    public void testGetAllProjects_whenServiceReturnsProjects_returnsProjects() {
        // Arrange
        List<Project> projects = List.of(new Project("akku","Java17"), new Project("pks", "Java8"));
        when(projectService.getAllProjects()).thenReturn(ResponseEntity.ok(projects));

        // Act
        ResponseEntity<List<Project>> responseEntity = projectController.getAllProjects();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(projects, responseEntity.getBody());
    }

}