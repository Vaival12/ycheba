package com.university.domain;

public class Department {
    private int id;
    private String name;
    private int countEmployees;
    private int countRooms;

    public Department() {
    }

    public Department(int id, String name, int countEmployees, int countRooms) {
        this.id = id;
        this.name = name;
        this.countEmployees = countEmployees;
        this.countRooms = countRooms;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCountEmployees() {
        return this.countEmployees;
    }

    public int getCountRooms() {
        return this.countRooms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountEmployees(int count) {
        this.countEmployees = count;
    }

    public void setCountRooms(int count) {
        this.countRooms = count;
    }

    public String toString() {
        return String.format("%-3d|%-10s|%-3d|%-3d", this.id, this.name, this.countEmployees, this.countRooms);
    }
}
