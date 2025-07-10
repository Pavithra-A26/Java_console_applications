package org.example;

public class ConsoleView {

    public  void showMsg(String msg){
        System.out.println(msg);
    }

    public void displayStudents(Student std){
        System.out.println("Studen id: "+ std.getId() + "|" + "Student name: " + std.getName());
    }
}
