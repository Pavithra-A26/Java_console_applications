import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParKingLot {
    int number;
    List<Floors> floors = new ArrayList<>();
    Map<String,Slot> ticketSlotMap = new HashMap<>();

    public ParKingLot(int number,int floor , int slots){
        this.number = number;
        for(int i=1;i<=floor;i++) floors.add(new Floors(i, slots));
        System.out.println("Creating parking lot with " + floor + "floors and " + slots + "per floor");
    }

    public void parkVehicle(Vehicle vehicle){
        for(Floors floor : floors){
            for(Slot slot: floor.getSlotByVehicle(vehicle.type)){
                if (slot.isFree()) {
                    slot.park(vehicle);
                    String ticket = number+ "_" + floor.number +"_" + slot.number;
                    ticketSlotMap.put(ticket, slot);
                    System.out.println("Vehicle Parked and Ticket id : " + ticket);
                    
                }
            }
        }

        System.out.println("Vehicle not parked");
    }

    public void unParkVehicle(String ticket){
        Slot slot = ticketSlotMap.get(ticket);

        if(slot == null){
            System.out.println("Invalid Ticket");
        }

        Vehicle v= slot.vehicle;
        slot.unPark(v);
        ticketSlotMap.remove(ticket);
        System.out.println("Vehicle unparked with  Reg no: " + v.regNo + " color : " + v.color + " type of " + v.type);
    }

    public void display(String displayType , VehicleType type){
        int count =0;
        for(Floors floor: floors){
            List<Slot> typesolts = floor.getSlotByVehicle(type);
            if(displayType.equals("free_count")){
                
                for(Slot s: typesolts){
                    if(s.isFree()){
                        count++;
                    }
                }

                System.out.println("Total free count:" + count);
            }
            if(displayType.equals("not_free_count")){
                
                for(Slot s: typesolts){
                    if(!s.isFree()){
                        count++;
                    }
                }

                System.out.println("Total not free count:" + count);
            }
        }
    }
}
