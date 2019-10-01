/**
 * The book class allows instances of itself to store information about a certain book.
 * It also holds information about whether a book has been checked out or not.
 */
public class Book extends LibraryItem implements Checkoutable {

    String author;
    Boolean checkedOut = false;


    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void checkout() {
        if(!this.checkedOut) {
            this.checkedOut = true;
            System.out.println("Thank you! Enjoy the book");
        } else {
            System.out.println("Sorry, that book is not available");
        }

    }


    public void returnBook() {
        if(this.checkedOut) {
            this.checkedOut = false;
            System.out.println("Thank you for returning the book");

        } else {
            System.out.println("That is not a valid book to return.");

        }

    }


    public Boolean getCheckedOut() {
        return checkedOut;
    }

}
