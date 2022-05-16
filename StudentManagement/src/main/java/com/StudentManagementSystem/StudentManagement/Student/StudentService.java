package com.StudentManagementSystem.StudentManagement.Student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(StudentModel studentModel) {

        Optional<StudentModel> studentModelOptional = studentRepository
                .findStudentModelByEmail(studentModel.getEmail());
        if(studentModelOptional.isPresent()){
            throw new IllegalArgumentException("Email already exist");
        }
        studentRepository.save(studentModel);
        System.out.println(studentModel);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with {"+studentId+"} doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        StudentModel studentModel = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalArgumentException("Student with ID {"+studentId+"} doesn't exists"));

        if(name!=null &&
                name.length()>0 &&
                    !Objects.equals(name,studentModel.getName())){
            studentModel.setName(name);
        }

        if(email!=null &&
                email.length()>0 &&
                !Objects.equals(email,studentModel.getEmail())){

            Optional<StudentModel> studentModelOptional = studentRepository.findStudentModelByEmail(email);
            if(studentModelOptional.isPresent()){
                throw new IllegalStateException("Email {"+email+"} already exists");
            }
            else{
                studentModel.setEmail(email);
            }
        }
    }
}
