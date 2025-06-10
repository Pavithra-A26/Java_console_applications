import java.util.ArrayList;
import java.util.List;

class Booking{
    int customerId , bookingId;
    char from;
    char to;
    int pickupTime , dropTime;
    int amount;

    public Booking(int bookingId ,int customerId,char from ,char to , int pickupTime,int dropTime,int amount){
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.from = from;
        this.to = to;
        this.pickupTime= pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }
}

class Taxi{
    int id;
    char currentSpot;
    int freeTime;
    int earnings;
    List<Booking> bookings;

    public Taxi(int id){
        this.id=id;
        this.currentSpot = 'A';
        this.freeTime =0;
        this.earnings = 0;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking , char dropLocation, int dropTime, int amount){
        bookings.add(booking);
        this.currentSpot = dropLocation;
        this.freeTime = dropTime;
        this.earnings += amount;
    }
}


class TaxiBookingSystem {
    List<Taxi> taxis;
    int bookingCounter =1;
    
    public TaxiBookingSystem(int num){
        this.taxis= new ArrayList<>();

        for(int i=1;i<=num;i++){
            taxis.add(new Taxi(i));
        }
    }


    private int getDistance(char from , char to){
        return Math.abs(from-to) * 15;
    }

    private int calculateFare(int distance){
        if(distance<=5){
            return 100;
        }

        return 100 + (distance - 5) * 10;
    }


    public void bookTaxi(int customerId, char from , char to, int pickupTime){
        Taxi taxi = findAvailableTaxi(from  , pickupTime);

        if(taxi == null){
            System.out.println("No Taxi Available at this movement");
            return;
        }

        int distance = getDistance(from, to);
        int fare = calculateFare(distance);
        int dropTime = pickupTime + (distance/15);

        Booking booking = new Booking(bookingCounter++, customerId, from, to, pickupTime, dropTime, fare);

        taxi.addBooking(booking, to, dropTime, fare);

        System.out.println("Taxi can be alloted");

        System.out.println("Taxi -" + taxi.id + "is Alloted");


    }

    private Taxi findAvailableTaxi(char from , int pickupTime){
        Taxi selectedtaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for(Taxi taxi: taxis){
            if(taxi.freeTime<= pickupTime){
                int dist = Math.abs(taxi.currentSpot - from);
                if(dist<minDistance){
                    minDistance = dist;
                    selectedtaxi = taxi;
                }else if(dist == minDistance){
                    if(selectedtaxi == null || taxi.earnings < selectedtaxi.earnings){
                        selectedtaxi = taxi;
                    }
                }
            }
        }

        return selectedtaxi;
    }

    public void displayDetails(){
        for(Taxi taxi : taxis){
            System.out.println("\n Taxi-" + taxi.id + "\t Total Earnings:" +taxi.earnings);
            for(Booking b : taxi.bookings){
                System.out.println(b.bookingId + "\t\t" + b.customerId + "\t\t" + b.from + "\t\t" + b.to + "\t\t" + b.pickupTime + "\t\t" + b.dropTime + "\t\t" + b.amount);
            }
        }
    }
}