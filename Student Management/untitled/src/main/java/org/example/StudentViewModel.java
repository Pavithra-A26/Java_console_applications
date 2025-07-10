package org.example;

import java.util.List;
//import java.util.Map;

public class StudentViewModel {
    private StudentDataBase db = StudentDataBase.getInstance();

    public List<Student> getAllStudent(){
        return db.getStudents();
    }

    public void addStudent(String name , int id){
        db.addStudent(new Student(name , id));
    }

    public boolean deleteStudent
}
