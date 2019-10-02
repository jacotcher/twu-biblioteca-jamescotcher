/**
 * The movie class allows instances of itself to store information about a certain movie.
 * It also holds information about whether a movie has been checked out or not.
 */
public class Movie extends LibraryItem implements Checkoutable {
    String director;
    Boolean checkedOut = false;
    // Stores the user that checked the movie out
    User checkedOutBy = null;
    int rating;

    /**
     * Constructor method for movie
     * @param name Name of the movie
     * @param director Author of the movie
     * @param year Year the movie was created
     */
    public Movie(String name, String director, int year) {
        this.name = name;
        this.director = director;
        this.year = year;
    }

    /**
     * get method for the director of the movie
     * @return the director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * get method for the rating of the movie
     * @return rating of the movie
     */
    public int getRating() {
        return rating;
    }

    /**
     * set method for the rating of the movie. It only sets the rating if it is between 1 and 10;
     * @param rating the rating of the movie
     */
    public void setRating(int rating) {
        if(rating >= 1 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("Sorry, the rating needs to be between 1-10");
        }
    }

    /**
     * This method marks the movie as checked out. It also stored the user that is passed in as the "checkedOutBy" variable.
     * @param user the user who has checked out the movie
     */
    public void checkout(User user) {
        if(!this.checkedOut) {
            this.checkedOut = true;
            System.out.println("Thank you! Enjoy the movie");
            checkedOutBy = user;
        } else {
            System.out.println("Sorry, that movie is not available");
        }

    }

    /**
     * This method marks the movie as returned. It also removed the user associated with checking it out.
     */
    public void returnItem() {
        if(this.checkedOut) {
            this.checkedOut = false;
            this.checkedOutBy = null;
            System.out.println("Thank you for returning the movie");

        } else {
            System.out.println("That is not a valid movie to return.");

        }

    }

    /**
     * get method for seeing if the movie is checked out
     * @return True if the movie has been checked out, false if it hasn't.
     */
    public Boolean getCheckedOut() {
        return checkedOut;
    }

    /**
     * get method for checking who the user is that checked the movie out
     * @return The user that has checked out the movie.
     */
    public User getCheckedOutBy() {
        return checkedOutBy;
    }

}
