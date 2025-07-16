import java.util.*;

public class BookViewModel {
    private static BookDb db = BookDb.getInstance();

    public void addBook(int id , String name){
        db.AddBook(id, name);
    }

    public HashMap<Integer,Book> getAllBooks(){
        return db.getBooks();
    }
}
