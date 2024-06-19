package com.example.projecttask.Controller;

import com.example.projecttask.Model.Project;
import com.example.projecttask.Service.ProjectService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
       response.sendRedirect("swagger-ui.html/");
    }

    @PostMapping("/addProject")
    @ResponseBody
    public ResponseEntity<String> createproject(@org.springframework.web.bind.annotation.RequestBody Project project){
        return projectService.createProject(project);
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/getProjectByIdOrName")
    public ResponseEntity<List<Project>> getProjectsByIdOrName(@RequestParam(required = false) String id,@RequestParam(required = false) String name){
        return projectService.getProjectsByIdOrName(id,name);
    }

    @PostMapping("/updateProject/{id}")
    public ResponseEntity<String> updateProject(@PathVariable String id,@RequestBody Project project){
        return projectService.updateProject(id,project);
    }

    @PostMapping("/deleteProject")
    public ResponseEntity<String> deleteProject(@RequestParam(required = false) String id,@RequestParam(required = false) String name){
        return projectService.deleteProject(id,name);
    }

}
