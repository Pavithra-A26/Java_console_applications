package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ConsoleView view = new ConsoleView();
        StudentViewModel vm = new StudentViewModel();
        Scanner sc = new Scanner(System.in);

        while(true){
            view.showMsg("1.add student \n 2.display student \n 3.Show Student by id  \n 4.Delete Student \n 5.1exit");
            int choice = sc.nextInt();
            sc.nextLine();


            switch(choice){
                case 1:
                    view.showMsg("Enter Student name");
                    String name = sc.nextLine();
                    view.showMsg("Enter student id");
                    int id = sc.nextInt();

                    vm.addStudent(name,id);
                    view.showMsg("Student Added");
                    break;

                case 2:
                    for(Student std: vm.getAllStudent()){
                        view.displayStudents(std);
                    }
                    break;

                case 3:
                    view.showMsg("Enter the student id to delete");
                    int sid = sc.nextInt();
                    view.displayStudents(vm.searchStudent(sid));
                    break;

                case 4:
                    view.showMsg("Enter the student id to delete");
                    int did = sc.nextInt();
                    vm.deleteStudent(did);
                    view.showMsg("Student deleted");
                    break;

                case 5:
                    System.exit(0);
            }
        }


    }
}