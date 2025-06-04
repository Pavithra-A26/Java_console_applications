import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Passenger{
    String name;
    int age;
    String gender;
    String Berthprefference;
    String AllotedBerth;
    String TicketType;

    public Passenger(String name, int age , String gender, String Berthprefference, String AllotedBerth, String TicketType){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.Berthprefference = Berthprefference;
        this.AllotedBerth = AllotedBerth;
        this.TicketType = TicketType;
    }

    public String toString(){
        return name + " | Age : " + age + " | Gender : "+ gender + " | Alloed Berth: " + AllotedBerth + " | Ticket " + TicketType  ;
    }
}

public class RailwayReservation {

    static final int TOTAL_CONFIRMED = 63;
    static final int TOTAL_RAC = 18;
    static final int TOTAL_WAITLIST = 10;
    
    static int lower = 21, middle =21 , upper =21;

    static List<Passenger> confirmed = new ArrayList<>();
    static Queue<Passenger> rac = new LinkedList<>();
    static Queue<Passenger> waiting = new LinkedList<>();
    static List<Passenger> children = new LinkedList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(" _____________Railway Ticket Reservation _____________ ");
            System.out.println(" 1 . Book Ticket ");
            System.out.println(" 2 . Cancel Ticket ");
            System.out.println(" 3 . Print Booked Tickets ");
            System.out.println(" 4. Print Available Tickets");
            System.out.println(" 5 . Exit ");
            System.out.println(" choose option ");

            int choise = sc.nextInt();
            sc.nextLine();


            switch (choise) {
                case 1:
                    bookticket();
                    break;
            
                case 2 :
                    cancelticet();
                    break;

                case 3 :
                    printBookedTickets();
                    break;
                
                case 4 :
                    printAvailableTickets();
                    break;

                case 5:
                    System.exit(0);

                default:
                    break;
            }
        }
    }


    static void bookticket(){
        System.out.println("Enter your name :");
        String name = sc.nextLine();
        System.out.println("Enter your Age");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your gender (M/F) ");
        String gender = sc.nextLine();
        System.out.println("Enter Berth Preference (L/M/U)");
        String preff = sc.nextLine();

        if(age<=5){
            children.add(new Passenger(name, age, gender, preff, "-", "Child"));
            System.out.println("Added to children list,no ticket alloted");
            return;
        }
        String alloted = getBerth(age , gender,preff);

        if(confirmed.size()<TOTAL_CONFIRMED && alloted != null ){
            confirmed.add(new Passenger(name, age, gender, preff, alloted, "Confirmed"));
            reduceBerthCount(alloted);
            System.out.println("Ticket confirmed with " + alloted + " Berth");
        }
        else if(rac.size()<TOTAL_RAC){
            rac.add(new Passenger(name, age, gender, preff, "Side-lower ", "Rac"));
            System.out.println("Ticket Added to RAC");
        }
        else if(waiting.size()<TOTAL_WAITLIST){
            waiting.add(new Passenger(name, age, gender, preff, "-", "waiting"));
            System.out.println("Added to waiting list");
        }else{
            System.out.println("NO ticket Available");
        }
    }

    static String getBerth(int age , String gender , String preff){
        if(age>=60 ||  preff.equalsIgnoreCase("L") && gender.equalsIgnoreCase("F")){
            if(lower>0){
                return "L";
            }
        }

        if(preff.equalsIgnoreCase("L") && lower>0) return "L";
        if(preff.equalsIgnoreCase("M") && middle>0) return "M";
        if(preff.equalsIgnoreCase("U") && upper>0) return "U";

        if(lower>0){
            return "L";
        }

        if(middle>0) return "M";
        if(upper>0) return "U";

        return null;
    }

    static void reduceBerthCount(String Berth){
        switch (Berth) {
            case "L":
                lower--;
                break;
            case "M":
                middle--;
                break;
            case "U":
                upper--;
                break;
            default:
                break;
        }
    }

    static void increaseBerthCount(String Berth){
        switch (Berth) {
            case "L":
                lower++;
                break;
            case "M":
                middle++;
                break;
            case "U":
                upper++;
                break;
            default:
                break;
        }
    }


    static void cancelticet(){
        System.out.println("Enter Passenger name to cancel ticket");
        String name = sc.nextLine();

        Passenger cancelPassenger = null;

        for(Passenger p: confirmed){
            if(p.name.equalsIgnoreCase(name)){
                cancelPassenger = p;
            }
        }

        if(cancelPassenger != null){
            confirmed.remove(cancelPassenger);
            increaseBerthCount(cancelPassenger.AllotedBerth);
            System.out.println("Ticket Canceled");

            if(!rac.isEmpty()){
                Passenger newPassenger = rac.poll();
                String Berth = getBerth(newPassenger.age, newPassenger.gender, newPassenger.Berthprefference);
                newPassenger.AllotedBerth = Berth;
                newPassenger.TicketType = "Confirmed";
                confirmed.add(newPassenger);
                reduceBerthCount(Berth);
            }


            if(!waiting.isEmpty()){
                Passenger waitingPassenger = waiting.poll();
                String Berth = getBerth(waitingPassenger.age, waitingPassenger.gender, waitingPassenger.Berthprefference);
                waitingPassenger.AllotedBerth = Berth;
                waitingPassenger.TicketType = "RAC";
                rac.add(waitingPassenger);
            }
        }else{
            System.out.println("NO Passenger found");
        }
    }

    static void printBookedTickets(){
        if(confirmed.isEmpty() && rac.isEmpty()){
            System.out.println("No Tickets Booked");
        }else{
            for(Passenger p :confirmed){
                System.out.println(p);
            }

            for(Passenger p :rac){
                System.out.println(p);
            }

            System.out.println("Total Confimed tickets: " + confirmed.size() + "Total Rac Tickets : " + rac.size());
        }
    }

    static void printAvailableTickets(){
        System.out.println("___________Available Tickets____________");
        System.out.println("Total Confirmed Tickets : "+ confirmed.size());
        System.out.println("Total Rac Tickets : " + rac.size());
        System.out.println("Total waiting List :" + waiting.size());
        System.out.println("Lower : "+ lower + " Middle : " + middle + " Upper : "+ upper);
    }
}