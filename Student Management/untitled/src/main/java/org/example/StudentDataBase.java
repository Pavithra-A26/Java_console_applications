package org.example;

import java.util.ArrayList;
import java.util.List;


public class StudentDataBase {
    private static StudentDataBase instance;
    private List<Student> students = new ArrayList<>();

    private StudentDataBase(){}

    public  static StudentDataBase getInstance(){
        if(instance == null){
            instance = new StudentDataBase();
        }

        return instance;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public List<Student> getStudents(){
        return students;
    }

    public boolean deleteStudent(int id){
        for(Student std :students){
            if(std.getId() == id){
                students.remove(std);
                return  true;
            }
        }
        return  false;
    }

    public Student getStudentById(int id){
        Student student = null;
        for(Student std :students){
            if(std.getId() == id){
                student= std;
            }
        }

        return student;
    }
}
