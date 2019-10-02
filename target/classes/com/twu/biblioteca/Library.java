import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * This is the Library class. It allows instances of it to store various books and for it's books to be displayed
 */
public class Library {


    /**
     * The variable 'libraryitems' stores an ArrayList of all the current libraryitems held by the library.
     */
    private ArrayList<LibraryItem> libraryItems = new ArrayList<LibraryItem>();
    /**
     * The variable 'books' stores an ArrayList of all the current books held by the library.
     */
    private ArrayList<Book> books = new ArrayList<Book>();
    /**
     * The variable 'movies' stores an ArrayList of all the current movie held by the library.
     */
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    /**
     * The variable 'users' stores an ArrayList of all the users that can use the library.
     */
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * The constructor method. Currently the 5 books are manually added as per the user stories
     */
    public Library() {

        sourceUsers();
        sourceItems();
        fillBooks();
        fillMovies();

    }

    /**
     * The initialisation of the library. Currently only displays a welcome message
     */
    public void init() {
        System.out.println("__________________________");
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
     * This method fills the libraryitems arraylist that is used as a DB.
     */
    private void sourceItems() {
        libraryItems.add(new Book("James Bond", "Ian Fleming", 1953));
        libraryItems.add(new Book("Harry Potter", "J.K. Rowling", 1997));
        libraryItems.add(new Book("Lord of the Rings", "J.R.R. Tolkien", 1954));
        libraryItems.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        libraryItems.add(new Book("Birdsong", "Sebastian Faulks", 1993));

        libraryItems.add(new Movie("Finding Nemo", "Andrew Stanton", 2003));
        libraryItems.add(new Movie("The Emperors New Groove", "Mark Dindal", 2001));
        libraryItems.add(new Movie("The Wailing", "Na Hong-jin", 2016));
        libraryItems.add(new Movie("The Shawshank Redemption", "Frank Darabont", 1995));
        libraryItems.add(new Movie("Pulp Fiction", "Quentin Tarantino", 1994));
    }

    /**
     * This method fills the users arraylist that is used as a DB.
     */
    private void sourceUsers() {
        users.add(new User("James Cotcher", "100-1234", "password", "jamescotcher@email.com", "07514537807"));
        users.add(new User("Librarian", "999-9999", "password", true));
        users.add(new User("Chris Callaghan", "123-1234", "password1", "chriscallaghan@email.com", "07591847385"));
        users.add(new User("Frankie Fowell", "502-1997", "password2", "frankiefowell@email.com", "07491829481"));
    }

    /**
     * This method prints out a list of all the movies that the library owns, whether checked out or not.
     */
    public void showMovies() {
        for (int i = 0; i < this.movies.size(); i++) {
            Movie current_movie = this.movies.get(i);
            System.out.print(current_movie.getName() +"|" + current_movie.getDirector() +"|" + current_movie.getYear());
            System.out.println();
        }

    }

    /**
     * This method fills the book arraylist that is being used as a DB
     */
    private void fillBooks() {
        for (int i = 0; i < this.libraryItems.size(); i++) {
            if(Book.class.isInstance(libraryItems.get(i))) {
                Book book = (Book) libraryItems.get(i);
                this.books.add(book);

            }
        }
    }

    /**
     * This method fills the movies arraylist that is being used as a DB
     */
    private void fillMovies() {
        for (int i = 0; i < this.libraryItems.size(); i++) {
            if(Movie.class.isInstance(libraryItems.get(i))) {
                Movie movie = (Movie) libraryItems.get(i);
                this.movies.add(movie);

            }
        }
    }

    /**
     * The get method for returning the books that the library owns
     * @return books The ArrayList of books that the library owns
     */
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    /**
     * The get method for returning the movies that the library owns
     * @return movies The ArrayList of movies that the library owns
     */
    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    /**
     * The get method for returning the library items that the library owns
     * @return an arraylist of all library items
     */
    public ArrayList<LibraryItem> getLibraryItems() {
        return libraryItems;
    }

    /**
     * The get method for returning the items that have been checked out
     * @return an arraylist of items that have been checked out by anyone.
     */
    public ArrayList<LibraryItem> getCheckedOutItems() {
        ArrayList<LibraryItem> checkedOut = new ArrayList<LibraryItem>();
        for(int i = 0; i< libraryItems.size(); i++) {
            if(Book.class.isInstance(libraryItems.get(i))) {
                Book book = (Book) libraryItems.get(i);
                if(book.getCheckedOut()) {
                    checkedOut.add(book);
                }
            }
            else if(Movie.class.isInstance(libraryItems.get(i))) {
                Movie movie = (Movie) libraryItems.get(i);
                if(movie.getCheckedOut()) {
                    checkedOut.add(movie);
                }
            }
        }
        return checkedOut;
    }

    /**
     * This method returns the items that have been checked out by the current user
     * @param user The current user of the system
     * @return An arraylist of items checked out by this user
     */
    public ArrayList<LibraryItem> getUserCheckedOutItems(User user) {

        ArrayList<LibraryItem> checkedOut = getCheckedOutItems();
        ArrayList<LibraryItem> userCheckedOut = new ArrayList<LibraryItem>();

        for(int i = 0; i< checkedOut.size(); i++) {
            if(Book.class.isInstance(checkedOut.get(i))) {
                Book book = (Book) checkedOut.get(i);
                if(book.getCheckedOut() && book.getCheckedOutBy().equals(user)) {
                    userCheckedOut.add(book);
                }
            }
            else if(Movie.class.isInstance(checkedOut.get(i))) {
                Movie movie = (Movie) checkedOut.get(i);
                if(movie.getCheckedOut() && movie.getCheckedOutBy().equals(user)) {
                    userCheckedOut.add(movie);
                }
            }
        }
        return userCheckedOut;
    }

    /**
     * This method checks whether or not a user exists in the arraylist being used as a DB.
     * @param id The ID that is being checked against the 'db'
     * @return True if the user exists, and false if it doesn't.
     */
    public boolean doesUserExist(String id) {
        boolean userFound = false;
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)) {
                userFound = true;
            }
        }
        return userFound;
    }

    /**
     * This method finds a user by ID and returns the user. It is often preceeded by "doesUserExist" to avoid exceptions/null pointers
     * @param id The ID of a user
     * @return The User variable referencing a user
     */
    public User findUserById(String id) {
        User user = null;
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)) {
                user = users.get(i);
            }
        }
        return user;
    }
}
