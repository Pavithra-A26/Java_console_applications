import java.util.HashMap;

public class BookDb {
    HashMap<Integer , Book> books = new HashMap<>();

    private static BookDb instance ;

    private BookDb(){};

    public static BookDb getInstance() {
        if(instance == null){
            instance = new BookDb();
        }
        return instance;
    }

    public void AddBook(int id , String name){
        if(books.containsKey(id)){
            System.out.println("Id Already exist");
            return;
        }
        
        books.put(id,new Book(id, name));
    }

    public HashMap<Integer,Book> getBooks(){
        return books;
    }
}
