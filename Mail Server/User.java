import java.util.LinkedList;
import java.util.List;

public class User {
    String userName;
    String passWord;
    List<Mail> receivedMails = new LinkedList<>();
    List<Mail> sendedMails = new LinkedList<>();

    public User(String userName, String passWord){
        this.userName = userName;
        this.passWord = passWord;
    }

    public void addReceivedMail(Mail mail){
        receivedMails.add(mail);
    }

     public void addsendedMail(Mail mail){
        sendedMails.add(mail);
    }

    public void showReceived(){
        for(Mail mail : receivedMails){
            System.out.println("From : " + mail.from + "Subject : " + mail.subject + "Body : " + mail.body);
        }
    }

    public void showSended(){
        for(Mail mail : sendedMails){
            System.out.println("To : " + mail.to + "Subject : " + mail.subject + "Body : " + mail.body);
        }
    }
}
