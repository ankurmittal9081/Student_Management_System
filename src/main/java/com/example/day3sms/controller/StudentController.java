package com.example.day3sms.controller;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.service.StudentService;
import com.example.day3sms.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    /** ✅ Token validation */
    private void checkToken(String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing token");
        }

        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    /** ✅ Add student */
    @PostMapping("/add-student")
    public StudentResponseDto addStudent(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody StudentRequestDto student) {

        checkToken(authHeader);
        return service.addStudent(student);
    }

    /** ✅ Get all students */
    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents(
            @RequestHeader("Authorization") String authHeader) {

        checkToken(authHeader);
        return service.getAllStudents();
    }

    /** ✅ Delete student */
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id) {

        checkToken(authHeader);
        service.deleteStudent(id);
        return "Student Deleted Successfully";
    }

    /** ✅ Update student */
    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequestDto student) {
        return service.updateStudent(id, student);
    }
}
