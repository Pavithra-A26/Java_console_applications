import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaxiBookingSystem s = new TaxiBookingSystem(4);

        while (true) {
            System.out.println("___________Call Taxi Booking System_____________");
            System.out.println("1.Book taxi");
            System.out.println("2.Print details");
            System.out.println("3.Exit");

            int choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {
                case 1:
                    System.out.println("Enter customer id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Picking spot(A-F)");
                    char from = sc.nextLine().toUpperCase().charAt(0);
                    System.out.println("Enter drop point (A-F)");
                    char to = sc.nextLine().toUpperCase().charAt(0);
                    System.out.println("Enetr pickup time(0-23)");
                    int pickupTime = sc.nextInt();
                    
                    s.bookTaxi(id, from, to, pickupTime);
                    break;

                case 2:
                    s.displayDetails();
                    break;

                case 3:
                    System.exit(0);
            
                default:
                    break;
            }
        }
    }
}
