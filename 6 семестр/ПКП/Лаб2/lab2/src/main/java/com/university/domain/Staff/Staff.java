package com.university.domain.Staff;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "department")
    private String department;
    @Column(name = "birthDate")
    private String birthDate;
    @Column(name = "post")
    private String post;

    public void setDepartments(String department) {
        this.department = department;
    }

    public String getRooms() {
        return department;
    }

    public void setRooms(String rooms) {this.department=department;}

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPost() {
        return post;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
