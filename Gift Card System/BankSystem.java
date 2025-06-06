import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class BankSystem{
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    List<Customer> customers = new ArrayList<>();

    public BankSystem(){
        customers.add(new Customer(1, "User@1"));
        customers.add(new Customer(2, "User@2"));
        customers.add(new Customer(3, "User@3"));
    }

    public void mainMenu(){
        while (true) {
            System.out.println("____________Main Menu_______");
            System.out.println("1.Login");
            System.out.println("2.SigUp");
            System.out.println("3.Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public void login(){
        System.out.println("Enter customer id :");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Password : ");
        String password= sc.nextLine();

        Customer loggedIn = null;

        for(Customer c: customers){
            if(c.id==id && c.encryptedPassWord.equals(Encryptor.encrypt(password))){
                loggedIn = c;
                break;
            }
        }

        if(loggedIn==null){
            System.out.println("Invalid creadencials");
        }else{
            System.out.println("Login successfull");

            accountMenu(loggedIn);
        }
    }

    public void signIn(){
        System.out.println("Enter customer id :");
        int newid = sc.nextInt();

        for(Customer c: customers){
            if(c.id==newid){
                System.out.println("Custoer id already exist");
                return;
            }
        }


        sc.nextLine();
        System.out.println("Enter Password : ");
        String password= sc.nextLine();


        customers.add(new Customer(newid, password));

        System.out.println("Singed in successfull,u can login now");
    }

    public void accountMenu(Customer customer){
        while (true) {
            System.out.println("___________Gift Card System__________");
            System.out.println("Customer id : " + customer.id);
            System.out.println("Main Balance : " + customer.mainBalance);
            System.out.println("1.Create Gift card");
            System.out.println("2.TopUp");
            System.out.println("3.Transaction History");
            System.out.println("4.Block");
            System.out.println("5.LogOut");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String cardNo = String.valueOf(10000 + rand.nextInt(90000));
                    String pin = String.valueOf(1000 + rand.nextInt(9000));
                    GiftCard newcard = new GiftCard(cardNo, pin);
                    customer.giftcards.add(newcard);
                    System.out.println("Gift Card Created .............card no : " + cardNo + " pin : " + pin);
                    break;
            
                case 2 :
                    System.out.println("Enter your card number :");
                    String gcno = sc.nextLine();
                    System.out.println("Enter your pin :");
                    String gcpin = sc.nextLine();

                    GiftCard newgc = customer.geGiftCard(gcno, gcpin);

                    if(newgc != null && !newgc.isBlocked ){
                        System.out.println("Enter amount to topup :");
                        double amount = sc.nextDouble();
                        sc.nextLine();

                        if(customer.mainBalance>=amount){
                            newgc.balance += amount;
                            customer.mainBalance -= amount;
                            newgc.addTransaction("TopUp" , amount);
                            System.out.println("Top Up successfull");
                        }else{
                            System.out.println("Insufficient Balnace");
                        }
                    }else{
                        System.out.println("Invalid or Blocked card");
                    }
                    break;
                case 3:
                    for(GiftCard g: customer.giftcards){
                        System.out.println("Gift card no: " + g.cardNo);
                        for(Transcation t: g.history){
                            System.out.println(" " + t);
                        }
                    }
                    break;
                
                case 4:
                    System.out.println("Enter your card number :");
                    String cno = sc.nextLine();
                    GiftCard block = null;
                    for(GiftCard g: customer.giftcards){
                        if(g.cardNo.equals(cno)){
                            block = g;
                            break;
                        }
                    }

                    if(block != null && !block.isBlocked){
                        block.isBlocked = true;
                        customer.mainBalance += block.balance;
                        block.addTransaction("Blocked & Refunded", block.balance);
                        block.balance =0;
                        System.out.println("Card Blocked");
                    }
                    else{
                        System.out.println("Crad Not found");
                    }

                case 5:
                    System.out.println("Loged Out Successfull");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

