import java.util.*;

class BankAccount {
    private int accountNo;
    private String holdername;
    private int balance;

    public BankAccount(int accountNo,String holdername ,int balance){
        this.accountNo = accountNo;
        this.holdername = holdername;
        this.balance = balance;
    }

    public int getacountNo(){
        return accountNo;
    }

    public String getname(){
        return holdername;
    }

    public int balance(){
        return balance;
    }

    public void deposit(int amount){
        if(amount>0){
            balance += amount;
            System.out.println("Amount deposited successfully ! Your New Balance is : " + balance);
        }else{
            System.out.println("Invalid amount !");
        }
    }

    public void withdraw(int amount){
        if(amount>0 && amount<=balance){
            balance -= amount;
            System.out.println("Amount deposited successfully ! Your New Balance is : " + balance);
        }else{
            System.out.println("Invalid amount !");
        }
    }

    public void displaydetails(){
        System.out.println("Account Number : " + accountNo);
        System.out.println("Holder Name : " + holdername);
        System.out.println("Balance : "+ balance);
    }
}

public class BankManagement{
    private static HashMap<Integer,BankAccount> acoounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;

        do{
            System.out.println("Bank Management");
            System.out.println("1.Create Account");
            System.out.println("2.Account Details");
            System.out.println("3.Amount Deposit");
            System.out.println("4.Amount Widraw");
            System.out.println("5.Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    getdetails();
                    break;
                case 3:
                    depositamount();
                    break;
                case 4:
                    withdrawamount();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
        while (choice!=5) ;

    }

    public static void createAccount(){
        System.out.println("Enter Account No");
        int accountno = sc.nextInt();
        sc.nextLine();
        if(acoounts.containsKey(accountno)){
            System.out.println("Account number already exist.....");
            return;
        }
        System.out.println("Enter Account holder name");
        String name = sc.nextLine();
        System.out.println("enter amount to deposit");
        int balance = sc.nextInt();
        sc.nextLine();

        BankAccount account = new BankAccount(accountno, name, balance);
        acoounts.put(accountno,account);
        System.out.println("Account created successfully!");
    }

    public static void getdetails(){
        System.out.println("Enter Account No");
        int accountno = sc.nextInt();
        sc.nextLine();
        BankAccount account = acoounts.get(accountno);
        if(account != null){
            account.displaydetails();
        }else{
            System.out.println("No account found");
        }
    }

    public static void depositamount(){
        System.out.println("Enter Account No");
        int accountno = sc.nextInt();
        sc.nextLine();
        BankAccount account = acoounts.get(accountno);
        if(account != null){
            System.out.println("Enter amount to deposit");
            int amount = sc.nextInt();
            account.deposit(amount);
        }else{
            System.out.println("No account found");
        }
    }

    public static void withdrawamount(){
        System.out.println("Enter Account No");
        int accountno = sc.nextInt();
        sc.nextLine();
        BankAccount account = acoounts.get(accountno);
        if(account != null){
            System.out.println("Enter amount to deposit");
            int amount = sc.nextInt();
            account.withdraw(amount);
        }else{
            System.out.println("No account found");
        }
    }
}