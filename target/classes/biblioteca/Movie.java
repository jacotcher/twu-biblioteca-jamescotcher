public class Movie extends LibraryItem implements Checkoutable {
    String director;
    Boolean checkedOut = false;
    int rating;


    public Movie(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating >= 1 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("Sorry, the rating needs to be between 1-10");
        }
    }

    public void checkout() {
        if(!this.checkedOut) {
            this.checkedOut = true;
            System.out.println("Thank you! Enjoy the movie");
        } else {
            System.out.println("Sorry, that movie is not available");
        }

    }


    public void returnBook() {
        if(this.checkedOut) {
            this.checkedOut = false;
            System.out.println("Thank you for returning the movie");

        } else {
            System.out.println("That is not a valid movie to return.");

        }

    }


    public Boolean getCheckedOut() {
        return checkedOut;
    }
}
