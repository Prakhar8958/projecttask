package com.example.projecttask.Service;

import com.example.projecttask.Model.Project;
import com.example.projecttask.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectrepo;

    @Override
    public ResponseEntity<String> createProject(Project pojo) {

        Project newproject = new Project();
        newproject.setId(UUID.randomUUID().toString());
        newproject.setName(pojo.getName());
        newproject.setDescription(pojo.getDescription());
        projectrepo.save(newproject);

        try {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Try Again", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<>(projectrepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Project>> getProjectsByIdOrName(String id, String name) {

        if (id != null) {
            return new ResponseEntity<>(List.of(projectrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Project not found"))), HttpStatus.OK);
        } else if (name != null) {
            return new ResponseEntity<>(projectrepo.findByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> deleteProject(String id, String name) {
        if (id != null) {
            //Optional<Project> project=projectrepo.findById(id);
            projectrepo.deleteById(id);
        } else if (name != null) {
            Project project = (Project) projectrepo.findByName(name);
            projectrepo.delete(project);
        } else {
            throw new IllegalArgumentException("Project ID or Name must be provided.");
        }

        try {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Try Again", HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<String> updateProject(String id, Project updateData) {

        //Optional<Project> oldData = projectrepo.findById(id);
            Project newProject = Project.builder().id(id).name(updateData.getName()).description(updateData.getDescription()).build();
            projectrepo.save(newProject);
            return new ResponseEntity<>("Updated", HttpStatus.OK);


        //return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
    }
}
