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

    public boolean checkout() {
        if(!this.checkedOut) {
            this.checkedOut = true;
            return true;
        } else {
            return false;
        }

    }
}
