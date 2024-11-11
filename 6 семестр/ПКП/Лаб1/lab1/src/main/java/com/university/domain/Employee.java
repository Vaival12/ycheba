package com.university.domain;

public class Employee {
    private int id;
    private String name;
    private int idDepartment;
    private String age;
    private String post;

    public Employee() {
    }

    public Employee(int id, String name, int idDepartment, String age, String post) {
        this.id = id;
        this.name = name;
        this.idDepartment = idDepartment;
        this.age = age;
        this.post = post;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDepartment() {
        return this.idDepartment;
    }

    public void setIdDepartment(int id) {
        this.idDepartment = id;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String toString() {
        return String.format("%-3d|%-15s|%-3d|%8s|%-10s", this.id, this.name, this.idDepartment, this.age, this.post);
    }
}
