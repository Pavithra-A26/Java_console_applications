import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,User> users = new HashMap<>();
    static User LoggedIn = null;
    public static void main(String[] args) {
        

        while (true) {
            System.out.println("_____________MAIL SERVER___________");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Send Mail");
            System.out.println("4.view inbox");
            System.out.println("5.view outbox");
            System.out.println("6.Logout");
            System.out.println("7.Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
            
                case 2:
                    loginUser();
                    break;

                case 3:
                    sendMail();
                    break;

                case 4:
                    viewInbox();
                    break;

                case 5:
                    viewOutbox();
                    break;

                case 6 :
                    System.out.println("Logout successfull");
                    LoggedIn = null;
                    
                case 7 :
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public static void registerUser(){
        System.out.println("Enter User name");
        String name = sc.nextLine();

        if(users.containsKey(name)){
            System.out.println("User already exist");
        }

        System.out.println("Enter your passward");
        String password = sc.nextLine();

        User newUser = new User(name, password);
        users.put(name, newUser);
        System.out.println("Register successfull");
    }

    public static void loginUser(){
        System.out.println("Enter User name");
        String name = sc.nextLine();

        if(!users.containsKey(name)){
            System.out.println("User not exist");
        }

        User logeduser = null;

        if(users.containsKey(name)){
            logeduser = users.get(name);
        }

        System.out.println("Enter your passward");
        String password = sc.nextLine();

        if(password.equals(logeduser.passWord)){
            System.out.println("Login successfull");
            LoggedIn = logeduser;
        }
    }

    public static void sendMail(){
        if(LoggedIn == null){
            System.out.println("Login first");
        }

        String from = LoggedIn.userName;
        System.out.println("Enter to mail");
        String to = sc.nextLine();
        System.out.println("Enter subject");
        String subject = sc.nextLine();
        System.out.println("Enter body of the mail");
        String body = sc.nextLine();

        Mail newMail = new Mail(from, to, subject, body);

        User receipient = users.get(newMail.to);
        receipient.addReceivedMail(newMail);

        LoggedIn.addsendedMail(newMail);

        System.out.println("Mail sended successfully");
    }

    public static void viewInbox(){
        LoggedIn.showReceived();
    }

    public static void viewOutbox(){
        LoggedIn.showSended();
    }
}
