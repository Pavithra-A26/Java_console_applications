import java.security.KeyStore.Entry;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        BookViewModel vm = new BookViewModel();
        Scanner sc = new Scanner(System.in);
       
        while (true) {
            view.viewmsg("1.Add Book \n3.View Book \n 5.exit");
                int choice = sc.nextInt();
                sc.nextLine();

            switch (choice) {
                case 1:
                    view.viewmsg("Enter Id");
                    int id = sc.nextInt();
                    sc.nextLine();
                    view.viewmsg("Enter name");
                    String name = sc.nextLine();
                    vm.addBook(id, name);
                    view.viewmsg("Book Addded");
                    break;

                case 2:
                   Map<Integer,Book> books = vm.getAllBooks();

                   view.viewmsg("Books List");
                   for(Map.Entry<Integer,Book> entry : books.entrySet()){
                        view.displayBook(entry.getValue());
                   }
                case 5:
                    System.exit(0);
            
                default:
                    break;
            }
        }
    }
}
