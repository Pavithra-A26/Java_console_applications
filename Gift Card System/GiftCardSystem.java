// import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Encryptor{
    public static String encrypt(String password){
        StringBuilder result = new StringBuilder();

        for(char ch: password.toCharArray()){
            if(Character.isLetter(ch)){
                result.append(ch+1);
            }else if(Character.isDigit(ch)){
                result.append(ch+1);
            }else{
                result.append(ch);
            }
        }

        return result.toString();
    }
}

class Customer{
    int id;
    String encryptedPassWord;
    double mainBalance;

    List<GiftCard> giftcards = new ArrayList<>();


    public  Customer(int id, String passWord){
        this.id = id;
        this.encryptedPassWord = Encryptor.encrypt(passWord);
        this.mainBalance = 10000;
    }

    public GiftCard geGiftCard(String cardno , String pin){
        for(GiftCard g : giftcards){
            if(g.cardNo.equals(cardno) && g.pin.equals(pin)){
                return g;
            }
        }

        return null;
    }
}

class Transcation{
    String type ;
    Double amount;
    // Date date;

    public Transcation(String type, Double amount){
        this.type = type;
        this.amount = amount;
        // this.date = new Date();
    }

    public String toString(){
        return type +  "|" + amount ;
    }

}


class GiftCard{
    String cardNo;
    String pin;
    double balance;
    boolean isBlocked;
    int rewardPoints;

    List<Transcation> history = new ArrayList<>();

    public GiftCard(String cardNo,String pin){
        this.cardNo = cardNo;
        this.pin = pin;
        this.balance = 0.0;
        this.isBlocked = false;
        this.rewardPoints = 0;
    }

    public void addTransaction(String type , double balance){
        history.add(new Transcation(type, balance));
    }
}




public class GiftCardSystem {
    public static void main(String[] args) {
        BankSystem bs = new BankSystem();
        bs.mainMenu();
    }
}
