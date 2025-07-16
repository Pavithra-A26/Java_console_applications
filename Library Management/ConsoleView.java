public class ConsoleView {
    public void viewmsg(String msg){
        System.out.println(msg);
    }

    public void displayBook(Book book){
        System.out.println("Book id : " + book.getBookId() + "," + "Book Name : " + book.getName());
    }
}
