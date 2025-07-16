public class Book{
    int id ;
    String name;

    public Book(int id , String name){
        this.id = id;
        this.name = name;
    }

    public int getBookId(){
        return id;
    }

    public String getName(){
        return name;
    }
}