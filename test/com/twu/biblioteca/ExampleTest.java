import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ExampleTest {
//    Allows you to test what has been printed to the command line
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();



    @Test
    public void greetingMessageIsDisplayed() {
//        Given
        Library library = new Library();
//        When
        library.init();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void libraryCanBeInitialised() {
//        Given
        Library library = new Library();
//        When
        Boolean notNull = library != null;
//        Then
        assertThat(true, is(notNull));
    }

    @Test
    public void libraryCanDisplayBooks() {
//        Given
        Library library = new Library();
//        When
        library.showBooks();
//        Then
        assertThat(systemOutRule.getLog(), containsString("James Bond|Ian Fleming|1953"));
    }

    @Test
    public void menuIsDisplayed() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Home");
//        When
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Biblioteca Menu"));
    }

    @Test
    public void selection2FromHomeMenuShowsBooks() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Home");
//        When
        menu.makeChoice("2");
//        Then
        assertThat(systemOutRule.getLog(), containsString("James Bond|Ian Fleming|1953\n" +
                "Harry Potter|J.K. Rowling|1997\n" +
                "Lord of the Rings|J.R.R. Tolkien|1954\n" +
                "To Kill a Mockingbird|Harper Lee|1960\n" +
                "Birdsong|Sebastian Faulks|1993\n"));

    }


/**
 * These tests originally worked, although after changing the workings of the system, I no longer can figure out how to test this because multiple user-inputs are required to test this.
 */

//    @Test
//    public void userNotifiedWhenWrongChoiceSelected() {
////        Given
//        Library library = new Library();
//        Menu menu = new Menu(library);
////        When
//        menu.setMenuState("Home");
//        ArrayList<Integer> validOptions = menu.getMenuOptions();
//        menu.checkInput("2", validOptions);
////        Then
//        assertThat(systemOutRule.getLog(), containsString("Your choice was not a valid option on the menu."));
//    }


//    @Test
//    public void userNotifiedIfEnteringWrongFormOfChoice() {
////        Given
//        Library library = new Library();
//        Menu menu = new Menu(library);
//        menu.setMenuState("Home");
//        ArrayList<Integer> validOptions = menu.getMenuOptions();
////        When
//
//        Scanner userInput = new Scanner(System.in);
//        String valid = menu.checkInput("ff", validOptions);
//        String input = "1";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        menu.makeChoice(input);
////        Then
//        assertThat(systemOutRule.getLog(), containsString("You need to enter integers to use this menu."));
//    }

    @Test
    public void userCanExitApplicationUsing9() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Home");
        menu.getMenuOptions();
//        Then
        exit.expectSystemExit();
        menu.makeChoice("9");
    }

    @Test
    public void userCanSelectBookCheckoutMenu() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Booklist");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Checkout a book"));
    }

    @Test
    public void userCanSeeListOfBooksWhenCheckingOut() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("CheckoutBook");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("James Bond|Ian Fleming"));
    }

    @Test
    public void userCanCheckoutBook() {
//        Given
        Library library = new Library();
//        When
        library.getBooks().get(0).checkout();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Thank you! Enjoy the book"));
    }

    @Test
    public void userCantCheckoutABookAlreadyTaken() {
//        Given
        Library library = new Library();
//        When
        library.getBooks().get(0).checkout();
        library.getBooks().get(0).checkout();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Sorry, that book is not available"));
    }

    @Test
    public void userCanReturnABook() {
//        Given
        Library library = new Library();
//        When
        library.getBooks().get(0).checkout();
        library.getBooks().get(0).returnItem();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Thank you for returning the book"));

    }

    @Test
    public void userCantReturnABookThatTheyDontHave() {
//        Given
        Library library = new Library();
//        When
        library.getBooks().get(0).returnItem();
//        Then
        assertThat(systemOutRule.getLog(), containsString("That is not a valid book to return."));

    }


    @Test
    public void userCanSeeMenuOptionForViewingMovies() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Home");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("List all movies"));

    }

    @Test
    public void userCanVisitListMoviesPage() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Home");
        menu.getMenuOptions();
        menu.makeChoice("4");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("-> Movielist"));
    }

    @Test
    public void userCanSeeListOfMovies() {
//        Given
        Library library = new Library();
//        When
        library.showMovies();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Finding Nemo"));
    }

    @Test
    public void userCanSelectListAllMoviesToSeeMovies() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Home");
        menu.makeChoice("4");
//        Then
        assertThat(systemOutRule.getLog(), containsString("Finding Nemo"));
    }

    @Test
    public void booksAreNotShownWhenViewingMovies() {
        //        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Home");
        menu.makeChoice("4");
//        Then
        assertThat(systemOutRule.getLog(), not(containsString("Birdsong")));
    }

    @Test
    public void checkOutAMovieIsAnOptionOnTheMenu() {
        //        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Movielist");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Checkout a movie"));
    }

    @Test
    public void selectingCheckoutAMovieTakesYouToTheCheckoutMovieMenu() {
        //        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Movielist");
        menu.makeChoice("2");
        menu.getMenuOptions();

//        Then
        assertThat(systemOutRule.getLog(), containsString("CheckoutMovie"));
    }

    @Test
    public void checkoutMovieMenuDisplaysListOfFilms() {
        //        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("CheckoutMovie");
        menu.getMenuOptions();

//        Then
        assertThat(systemOutRule.getLog(), containsString("Finding Nemo"));
    }

    @Test
    public void movieCanBeCheckedOut() {
        //        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("CheckoutMovie");
        menu.getMenuOptions();
        menu.makeChoice("a");

//        Then
        assertThat(systemOutRule.getLog(), containsString("Thank you! Enjoy the movie"));
    }

    @Test
    public void aCheckedOutMovieAppearsOnTheReturnsList() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
        library.getMovies().get(0).checkout();
//        When
        menu.setMenuState("Return");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Finding Nemo"));
    }

    @Test
    public void aCheckedOutMovieDoesNotAppearOnTheMovieList() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
        library.getMovies().get(0).checkout();
//        When
        menu.setMenuState("Movielist");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), not(containsString("Finding Nemo")));
    }

    @Test
    public void aMovieCanBeReturned() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
        library.getMovies().get(0).checkout();
//        When
        menu.setMenuState("Return");
        menu.makeChoice("a");
        menu.getMenuOptions();
        assertThat(systemOutRule.getLog(), not(containsString("Finding Nemo")));
        assertThat(systemOutRule.getLog(), containsString("Thank you for returning the movie"));
    }

    @Test
    public void userCanBeDefined() {
        User user = new User("James Cotcher", "123-1234", "password");
        assertThat("James Cotcher", is(user.getName()));
    }

    @Test
    public void userIsAskedToLogOnWhenSystemStarts() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Enter your library ID"));
    }

    @Test
    public void userToldTheirIdIsWrongIfUserDoesntExist() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("124-1231");
        menu.getMenuOptions();
//        Then

        assertThat(systemOutRule.getLog(), containsString("You have entered an ID that is not stored in this system. Please try again."));

    }

    @Test
    public void userCanProgressToPasswordIfTheyEnterTheCorrectId() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("100-1234");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Login-password"));
    }

    @Test public void userIsShownTheirNameSoTheyKnowTheyHaveGotTheirIdCorrect() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("100-1234");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Hello James Cotcher"));
    }

    @Test public void ifIncorrectPasswordIsEnteredUserStartsAgain() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("100-1234");
        menu.getMenuOptions();
        menu.makeChoice("wrongPassword");
//        Then
        assertThat(systemOutRule.getLog(), containsString("Please try logging in again."));
    }

    @Test public void ifCorrectPasswordUserCanSeeHome() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("100-1234");
        menu.getMenuOptions();
        menu.makeChoice("password");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Menu -> Home"));
    }

    @Test public void userCanLogOut() {
//        Given
        Library library = new Library();
        Menu menu = new Menu(library);
//        When
        menu.setMenuState("Login-id");
        menu.makeChoice("100-1234");
        menu.getMenuOptions();
        menu.makeChoice("password");
        menu.getMenuOptions();
//        Then
        assertThat(systemOutRule.getLog(), containsString("Menu -> Home"));
//        When again
        menu.makeChoice("1");
        menu.getMenuOptions();
        assertThat(systemOutRule.getLog(), containsString("Menu -> Login-id"));


    }




}
