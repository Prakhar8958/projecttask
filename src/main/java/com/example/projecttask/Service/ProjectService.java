package com.example.projecttask.Service;


import com.example.projecttask.Model.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {

    ResponseEntity<String> createProject(Project pojo);

    ResponseEntity<List<Project>> getAllProjects();

    ResponseEntity<List<Project>> getProjectsByIdOrName(String id,String name);

    ResponseEntity<String> deleteProject(String id, String name);

    ResponseEntity<String> updateProject(String id, Project updateData);



}
