package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepositiory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepositiory repository;

    public StudentService(StudentRepositiory repository) {
        this.repository = repository;
    }
    public StudentResponseDto addStudent(StudentRequestDto dto){

        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }
}
