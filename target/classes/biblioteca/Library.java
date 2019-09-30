import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * This is the Library class. It allows instances of it to store various books and for it's books to be displayed
 */
public class Library {

    /**
     * The variable 'books' stores an ArrayList of all the current books held by the library.
     */
    private ArrayList<Book> books;

    /**
     * The constructor method. Currently the 5 books are manually added as per the user stories
     */
    public Library() {
        this.books = new ArrayList<Book>();
        books.add(new Book("James Bond", "Ian Fleming", 1953));
        books.add(new Book("Harry Potter", "J.K. Rowling", 1997));
        books.add(new Book("Lord of the Rings", "J.R.R. Tolkien", 1954));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        books.add(new Book("Birdsong", "Sebastian Faulks", 1993));

    }

    /**
     * The initialisation of the library. Currently only displays a welcome message
     */
    public void init() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    /**
     * This methods prints out a list of all the books that the library owns (whether checked out or not)
     */
    public void showBooks() {
        for (int i = 0; i < this.books.size(); i++) {
            Book current_book = this.books.get(i);
            System.out.print(current_book.getName() +"|" + current_book.getAuthor() +"|" + current_book.getYear());
            System.out.println();
        }

    }

    /**
     * The get method for returning the books that the library owns
     * @return books The ArrayList of books that the library owns
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
}
