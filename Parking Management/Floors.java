import java.util.ArrayList;
import java.util.List;

public class Floors {
    int number;
    List<Slot> slots = new ArrayList<>();

    public Floors(int number, int slotcount){
        this.number=number;
        for(int i=1;i<=slotcount;i++){
            if(i==1) slots.add(new Slot(i, VehicleType.TRUCK));
            else if(i==2 || i==3) slots.add(new Slot(i, VehicleType.BIKE));
            else slots.add(new Slot(i, VehicleType.CAR));
        }
    }

    public List<Slot> getSlotByVehicle(VehicleType gettype){
        List<Slot> result = new ArrayList<>();
        for(Slot s : slots){
            if(s.type== gettype){
                result.add(s);
            }
        }

        return result;
    }
}
