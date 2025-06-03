import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id,String name,String department,double salary){
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getid(){
        return this.id;
    }

    public void setid(int newid){
        this.id = newid;
    }

    public String getname(){
        return this.name;
    }

    public void setname(String newname){
        this.name = newname;
    }

    public String getdepartment(){
        return this.department;
    }

     public void setdep(String newdep){
        this.department = newdep;
    }

    public double getsalary(){
        return this.salary;
    }

    public void setsal(double newsal){
        this.salary = newsal;
    }

    public void display(){
        System.out.println("Id : "+ id);
        System.out.println("Name : "+ name);
        System.out.println("Department : "+ department);
        System.out.println("Salary : "+ salary);
    }
}

public class EmployeeManagement {

    private static Scanner sc = new Scanner(System.in);
    private static HashMap<Integer,Employee> map = new HashMap<>();

    public static void main(String[] args) {
        int choice ;
        
        do{
            System.out.println("............Employee Management.........");
            System.out.println("1.Add Employee");
            System.out.println("2.Display Employee Details");
            System.out.println("3.Update Employee Details");
            System.out.println("4.Search Employee by Id");
            System.out.println("5.Delete Employee by Id");
            System.out.println("6.Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAlldetails();
                    break;
                case 3:
                    updatedetails();
                    break;
                case 4:
                    displaydetails();
                    break;
                case 5:
                    deleteemployee();
                    break;
                case 6:
                    System.exit(0);
                default:
                    break;
            }
        }while (choice!=6) ;
    }

    public static void addEmployee(){
        System.out.println("Enter Employee id");
        int id = sc.nextInt();
        sc.nextLine();

        if(map.containsKey(id)){
            System.out.println("Employee id already exist");
        }

        System.out.println("Enter Employee Name");
        String name = sc.nextLine();
        System.out.println("Enter Employee Department");
        String dep = sc.nextLine();
        System.out.println("Enter Employee Salary");
        double salary = sc.nextInt();

        Employee emp = new Employee(id, name, dep, salary);

        map.put(id, emp);

        System.out.println("Employee created successfully!");
    }

    public static void displayAlldetails(){
        if(map.isEmpty()){
            System.out.println("No Employee found");
        }else{
            System.out.println("Employee Details.....");
            for(Employee emp : map.values()){
                emp.display();
            }
        }
    }

    public static void updatedetails(){
        System.out.println("Enter Employee id");
        int id = sc.nextInt();
        sc.nextLine();
        Employee emp = map.get(id);
        if(emp != null){
            System.out.println("What you want update?");
            System.out.println("7.Name ");
            System.out.println("8.Department");
            System.out.println("9.Salary");
            System.out.println("10.All");
            int selection = sc.nextInt();
            sc.nextLine();
            switch (selection) {
                case 7:
                    System.out.println("Enter Employee Name");
                    emp.setname(sc.nextLine());
                    break;
                case 8: 
                    System.out.println("Enter Employee Department");
                    String dep = sc.nextLine();
                    emp.setdep(dep);
                    break;
                case 9: 
                    System.out.println("Enter Employee Salary");
                    emp.setsal(sc.nextDouble());
                    break;
                case 10:
                    System.out.println("Enter Employee Name");
                    emp.setname(sc.nextLine());
                    System.out.println("Enter Employee Department");
                    emp.setdep(sc.nextLine());
                    System.out.println("Enter Employee Salary");
                    emp.setsal(sc.nextDouble());
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("No Employee Found...");
        }
    }

    public static void displaydetails(){
       System.out.println("Enter Employee id");
        int id = sc.nextInt();
        sc.nextLine();

        Employee emp = map.get(id);

        if(emp != null){
            emp.display();
        }else{
            System.out.println("Employee Not Found");
        }
    }

    public static void deleteemployee(){
        System.out.println("Enter Employee id");
        int id = sc.nextInt();
        sc.nextLine();

        Employee emp = map.get(id);

        if(map.remove(id)!=null){
            System.out.println("Employee deleted successfully");
        }else{
            System.out.println("Employee ID not found");
        }
    }
}
