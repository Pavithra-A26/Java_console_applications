import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bombaredman{

    static Map<String , String> currentdb = new HashMap<>();
    static Deque<Map<String,String>> transaction = new ArrayDeque<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        while(true){
            System.out.println("______________BOMBERMAN GAME__________");
            System.out.println("1.Set");
            System.out.println("2.get");
            System.out.println("3.UnSet");
            System.out.println("4.Count");
            System.out.println("5.Begin");
            System.out.println("6.Roll Back");
            System.out.println("7.Commit");
            System.out.println("8.exit");
                    

            int choise = sc.nextInt();
            sc.nextLine();

            switch(choise){
                case 1:
                    System.out.println("Enter Variable :");
                    String svar = sc.nextLine();
                    System.out.println("Enter Value");
                    String sval = sc.nextLine();
                    setvalue(svar,sval);
                    break;

                case 2:
                    System.out.println("Enter Variable :");
                    String gvar = sc.nextLine();
                    getvalue(gvar);
                    break;

                case 3:
                    System.out.println("Enter Variable :");
                    String uvar = sc.nextLine();
                    unsetvalue(uvar);
                    break;
                
                case 4:
                    System.out.println("Enter Value :");
                    String cval = sc.nextLine();
                    countvalue(cval);
                    break;

                case 5:
                    begin();
                    break;

                case 6:
                    rollback();
                    break;

                case 7:
                    commit();
                    break;

                case 8:
                    System.exit(0);
            }
        }

        
    }

    static void setvalue(String variable ,String value){
        if(!transaction.isEmpty()){
            transaction.peek().put(variable, value);
        }
        else{
            currentdb.put(variable, value);
        }
    }

    static void getvalue(String variable){
        for(Map<String ,String> m :transaction){
            if(m.containsKey(variable)){
                System.out.println(m.get(variable) != "null" ? m.get(variable): "null");
            }
        }

        System.out.println(currentdb.getOrDefault(variable, "null"));
    }

    static void unsetvalue(String variable){
        if(!transaction.isEmpty()){
            transaction.peek().put(variable, "null");
        }
        else {
            currentdb.remove(variable);
        }
    }

    static void countvalue(String value){
        HashSet<String> set = new HashSet<>();
        int count =0;
        // for(int i=0;i<transaction.size();i++){
        //     Map<String,String> mt = ((ArrayDeque<Map<String,String>>) transaction).toArray(new Map[0])[i];
        List<Map<String, String>> txns = new ArrayList<>(transaction);
        for (int i = txns.size() - 1; i >= 0; i--) {
            Map<String, String> mt = txns.get(i);
            for(String key: mt.keySet()){
                if(!set.contains(key)){
                    set.add(key);
                    if(value.equals(mt.get(key))){
                        count++;
                    }
                }
            }
        }

        for(Map.Entry<String,String> entry : currentdb.entrySet()){
                if(!set.contains(entry.getKey()) && value.equals(entry.getValue())){
                    count++;
                }
            }

        System.out.println(count>0 ? count : "Null");

    }

    static void begin(){
        transaction.push(new HashMap<>());
        System.out.println("Transaction Started");
    }

    static void rollback(){
        if(!transaction.isEmpty()){
            transaction.pop();
            System.out.println("Rolled Back");
        }else{
            System.out.println("No transaction");
        }
    }

    static void commit(){
        if(transaction.isEmpty()){
            System.out.println("No Transaction");
            return;
        }

        while (!transaction.isEmpty()) {
            Map<String,String> changes = transaction.removeLast();
            for(Map.Entry<String,String> entry : changes.entrySet()){
                if(entry.getValue()=="null"){
                    currentdb.remove(entry.getKey());
                }else{
                    currentdb.put(entry.getKey(), entry.getValue());
                }
            }
        }

        System.out.println("Transaction commited");
    }
}