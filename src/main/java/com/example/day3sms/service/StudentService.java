package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepositiory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepositiory repository;

    public StudentService(StudentRepositiory repository) {
        this.repository = repository;
    }

    // create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

    // display
    public List<StudentModel> getStudents(){
        return repository.findAll();
    }

    // delete
    public void deleteStudent(String id){
        repository.deleteById(id);
    }

    // update
    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }
}
