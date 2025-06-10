import java.util.Scanner;

enum VehicleType{
        CAR , BIKE , TRUCK;
}

public class Main {
    static  ParKingLot plot ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       

        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ");
            switch (parts[0]) {
                case "create_Parking_lot":
                    plot = new ParKingLot(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    break;
            
                case "park_Vehicle":
                    Vehicle vehicle = new Vehicle(VehicleType.valueOf(parts[1]), parts[2], parts[3]);
                    plot.parkVehicle(vehicle);
                    return;
                default:
                    break;
            }
        }
    }
}
