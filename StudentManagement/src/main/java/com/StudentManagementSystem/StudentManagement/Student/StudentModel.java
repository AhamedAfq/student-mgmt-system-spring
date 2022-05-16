package com.StudentManagementSystem.StudentManagement.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity //This is for Hibernate
@Table  //This is for our Table
public class StudentModel {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private  String name;
    private String email;
    private LocalDate dob;

    @Transient // The use of annotation will remove the age column from database but Get API there will be age calculated from the getAge method
    private Integer age;

    public StudentModel() {
        System.out.println("Student Model constructor Is Invoked");
    }

    public StudentModel(Long id,
                        String name,
                        String email,
                        LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public StudentModel(String name,
                        String email,
                        LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        email.toString();
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
