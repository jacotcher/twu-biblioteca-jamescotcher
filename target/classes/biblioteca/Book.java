/**
 * The book class allows instances of itself to store information about a certain book.
 * It also holds information about whether a book has been checked out or not.
 */
public class Book {
    String name;
    String author;
    int year;
//    Book is not checked out by default
    Boolean checkedOut = false;

    /**
     * The constructor method only allows a book with name, author and year to be created.
     * @param name The name of the book
     * @param author The author of the book
     * @param year The year the book was published
     */
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    /**
     * Get method for the name of the book
     * @return name The name of the book
     */
    public String getName() {
        return name;
    }

    /**
     * Get method for the author of the book
     * @return author The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get method for the year the book was published
     * @return year The year the book was published
     */
    public int getYear() {
        return year;
    }

    /**
     * The method that checks out a book.
     * It doesn't allow a book to be checked out if it is already checked out.
     * It also displayed success/unsuccessful messages when checking out a book
     */
    public void checkout() {
        if(!this.checkedOut) {
            this.checkedOut = true;
            System.out.println("Thank you! Enjoy the book");
        } else {
            System.out.println("Sorry, that book is not available");
        }

    }

    /**
     * The method for returning a book.
     * It doesn't allow a book to be returned if it is not checked out.
     * It also displays success/unsuccessful messages when checking out a book
     */
    public void returnBook() {
        if(this.checkedOut) {
            this.checkedOut = false;
            System.out.println("Thank you for returning the book");

        } else {
            System.out.println("That is not a valid book to return.");

        }

    }

    /**
     * Get method for the checked in status of the book
     * @return checkoutOut a boolean value that say whether the books is checked out or not.
     */
    public Boolean getCheckedOut() {
        return checkedOut;
    }
}
