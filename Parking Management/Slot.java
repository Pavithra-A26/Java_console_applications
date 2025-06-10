public class Slot {
    int number;
    VehicleType type;
    Vehicle vehicle;

    public Slot(int number , VehicleType type ){
       this.number = number;
       this.type = type;
    }

    public boolean isFree(){
        return vehicle==null ;
    }

    public void park(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void unPark(Vehicle vehicle){
        this.vehicle=null;
    }

    
}
