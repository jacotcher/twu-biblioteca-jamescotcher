public class Book {
    String name;
    String author;
    int year;
    Boolean checkedOut = false;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
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
