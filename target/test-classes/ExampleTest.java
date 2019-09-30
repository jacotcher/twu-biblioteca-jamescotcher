import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
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
        assertThat("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n", is(systemOutRule.getLog()));
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
        assertThat("James Bond|Ian Fleming|1953\n" +
                "Harry Potter|J.K. Rowling|1997\n" +
                "Lord of the Rings|J.R.R. Tolkien|1954\n" +
                "To Kill a Mockingbird|Harper Lee|1960\n" +
                "Birdsong|Sebastian Faulks|1993\n", is(systemOutRule.getLog()));
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
    public void selection1FromHomeMenuShowsBooks() {
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
        menu.setMenuState("Home");
        ArrayList<String> validOptions = menu.getMenuOptions();
        exit.expectSystemExit();
        menu.makeChoice("9");
    }

    @Test
    public void userCanSelectBookCheckoutMenu() {
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Booklist");
        ArrayList<String> validOptions = menu.getMenuOptions();
        assertThat(systemOutRule.getLog(), containsString("Checkout a book"));
    }

    @Test
    public void userCanSeeListOfBooksWhenCheckingOut() {
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Checkout");
        ArrayList<String> validOptions = menu.getMenuOptions();
        assertThat(systemOutRule.getLog(), containsString("James Bond|Ian Fleming"));
    }

    @Test
    public void userCanCheckoutBook() {
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Checkout");
        ArrayList<String> validOptions = menu.getMenuOptions();
        menu.makeChoice("a");
        assertThat(systemOutRule.getLog(), containsString("Thank you! Enjoy the book"));
    }

    @Test
    public void userCantCheckoutABookAlreadyTaken() {
        Library library = new Library();
        Menu menu = new Menu(library);
        menu.setMenuState("Checkout");
        ArrayList<String> validOptions = menu.getMenuOptions();
        library.getBooks().get(0).checkout();
        menu.makeChoice("a");
        assertThat(systemOutRule.getLog(), containsString("Sorry, that book is not available"));
    }


}
