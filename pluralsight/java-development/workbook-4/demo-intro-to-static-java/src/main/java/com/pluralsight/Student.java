package com.pluralsight;

public class Student {
    private static String schoolName = "Greenwood High";

    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void printDetails() {
        System.out.println(name + " | grade " + grade + " | " + schoolName);
    }

    public static void changeSchool(String newName) {
        schoolName = newName;
    }


}
