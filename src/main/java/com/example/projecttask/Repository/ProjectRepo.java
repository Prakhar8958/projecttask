package com.example.projecttask.Repository;

import com.example.projecttask.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project,String> {
    List<Project> findByName(String name);
}
