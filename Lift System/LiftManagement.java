import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Lift{
    private int currentFloor;
    private Queue<Integer> requests;

    public Lift(){
        this.currentFloor = 0;
        this.requests = new LinkedList<>();
    }


    public void requestFloor(int floor){
        if(floor<0 || floor>10){
            System.out.println("Enter Valid Floor Number Between 0-10");
        }

        requests.add(floor);
        System.out.println("Floor Added to request queue");
    }

    public void MoveLift(){
        while(!requests.isEmpty()){
            int targetedFloor = requests.poll();
            System.out.println("Lift Moving From "+ currentFloor + " to " + targetedFloor);
            if(currentFloor<targetedFloor){
                for(int i=currentFloor+1;i<=targetedFloor;i++){
                    System.out.println("Lift Passing " + i + "th Floor");
                }
            }else if(currentFloor>targetedFloor){
                for(int i=currentFloor-1;i>=targetedFloor;i--){
                    System.out.println("Lift Passing " + i + "th Floor");
                }
            }

            currentFloor = targetedFloor;
            System.out.println("Lift Reached "+ targetedFloor);
        }

    }

    public int getliftFloor(){
        return currentFloor;
    }
}

public class LiftManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Lift lift = new Lift();

        int choice;

        do{
            System.out.println(".............Lift Management System...........");
            System.out.println("Lift Floor "+ lift.getliftFloor());
            System.out.println("1.Request Floor");
            System.out.println("2.move Lift");
            System.out.println("3.Exit From Lift");
            System.out.println("Enter Your Choice");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1: 
                    System.out.println("Enter the Floor number between 1 to 10");
                    int floor = sc.nextInt();
                    sc.nextLine();
                    lift.requestFloor(floor);
                    break;
                case 2:
                    lift.MoveLift();
                    break;
                case 3:
                    System.out.println("Existing from lift System......");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input...");
            }

        }while(choice!=3);

        sc.close();
    
    }
}