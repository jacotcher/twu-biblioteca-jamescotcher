/**
 * The book class allows instances of itself to store information about a certain book.
 * It also holds information about whether a book has been checked out or not.
 */
public class Book extends LibraryItem implements Checkoutable {

    String author;
    Boolean checkedOut = false;
    // Stores the user that checked the book out
    User checkedOutBy = null;

    /**
     * Constructor method for book
     * @param name Name of the book
     * @param author Author of the book
     * @param year Year the book was published
     */
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    /**
     * get method for the author of the book
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method marks the book as checked out. It also stored the user that is passed in as the "checkedOutBy" variable.
     * @param user the user that has checked out the book
     */
    public void checkout(User user) {
        if(!this.checkedOut) {
            this.checkedOut = true;
            System.out.println("Thank you! Enjoy the book");
            checkedOutBy = user;
        } else {
            System.out.println("Sorry, that book is not available");
        }

    }

    /**
     * This method marks the book as returned. It also removed the user associated with checking it out.
     */
    public void returnItem() {
        if(this.checkedOut) {
            this.checkedOut = false;
            this.checkedOutBy = null;
            System.out.println("Thank you for returning the book");

        } else {
            System.out.println("That is not a valid book to return.");

        }

    }

    /**
     * get method for seeing if the book is checked out
     * @return True if the book has been checked out, false if it hasn't.
     */
    public Boolean getCheckedOut() {
        return checkedOut;
    }

    /**
     * get method for checking who the user is that checked the book out
     * @return The user that has checked out the book.
     */
    public User getCheckedOutBy() {
        return checkedOutBy;
    }
}
