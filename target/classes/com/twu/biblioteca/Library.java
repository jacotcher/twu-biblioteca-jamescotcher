import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;


public class Library {

    private ArrayList<Book> books;


    public Library() {
        this.books = new ArrayList<Book>();
        books.add(new Book("James Bond", "Ian Fleming", 1953));
        books.add(new Book("Harry Potter", "J.K. Rowling", 1997));
        books.add(new Book("Lord of the Rings", "J.R.R. Tolkien", 1954));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("Birdsong", "Sebastian Faulks", 1993));

    }

    public void init() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showBooks() {
        for (int i = 0; i < this.books.size(); i++) {
            Book current_book = this.books.get(i);
            System.out.print(current_book.getName() +"|" + current_book.getAuthor() +"|" + current_book.getYear());
            System.out.println();
        }

    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
