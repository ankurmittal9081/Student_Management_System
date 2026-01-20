package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepositiory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private  StudentRepositiory repositiory;

    //create

    public StudentService(StudentRepositiory repositiory) {
        this.repositiory = repositiory;
    }

    public StudentModel addStudent(StudentModel student){
        return repositiory.save(student);
    }

}
