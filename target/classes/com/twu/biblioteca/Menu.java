import java.util.*;
import java.util.regex.*;

/**
 * Menu is the class that handles all user interaction with the books and library.
 * It contains all methods for user interaction and displaying results/menus.
 *
 */
public class Menu {
    private String menuState = "Login-id";
    private Library library;
    private  Boolean systemActive = true;
    private Hashtable<String, String> lastMenuOptions;
    private User currentUser;

    /**
     * The constructor class. Required an instance of library
     * @param library The library that the menu is serving
     */
    public Menu(Library library) {
        this.library = library;
    }

    /**
     * Returns the current state of the menu
     * @return menuState Shows the state of the menu. For example it could be equal to "Home", so that the menu knows which set of instructions to display to the user.
     */
    public String getMenuState() {
        return menuState;
    }

    /**
     * This method calculates the options that should be displayed to the user and prints them, it also returns an ArrayList of possible values that the user can enter.
     * @return keys The valid list of responses that a user can enter on the menu that is printed for them. Used to validate the user's responses.
     */
    public ArrayList<String> getMenuOptions() {

//        First the method checks the current menu state
        String menuState = this.getMenuState();
//        Then it creates a Hashtable to store the key-value pairs of possible responses
        Hashtable<String, String> menuOptions = new Hashtable<String, String>();

//        This casts the user options to keys for the system to process
        switch(menuState) {
            case "Home":
                menuOptions.putAll(appendHomeOptions(menuOptions));
                break;
            case "Booklist":
                menuOptions.putAll(appendBooklistOptions(menuOptions));
                break;
            case "CheckoutBook":
                menuOptions.putAll(appendCheckoutBookOptions(menuOptions));
                break;
            case "CheckoutMovie":
                menuOptions.putAll(appendCheckoutMovieOptions(menuOptions));
                break;
            case "Return":
                menuOptions.putAll(appendReturnOptions(menuOptions));
                break;
            case "Movielist":
                menuOptions.putAll(appendMovielistOptions(menuOptions));
                break;
            case "Login-id":
                menuOptions.putAll(appendLoginIdOptions(menuOptions));

        }


//        This stores the current menu configuration in case it needs to be re-printed
        setLastMenuOptions(menuOptions);
//        Prints the menu to the console for the user to see
        printMenu(menuOptions);

//        This creates a list of keys that are valid input options to be returned to validate the user input.
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        return keys;
    }

    private Map<? extends String,? extends String> appendLoginIdOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("*", "Enter your library ID");
        return menuOptions;

    }

    private Hashtable<String, String> appendHomeOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("2", "List all books");
        menuOptions.put("3", "Return items");
        menuOptions.put("4", "List all movies");
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    private Hashtable<String, String> appendBooklistOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("2", "Checkout a book");
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    private Hashtable<String, String> appendCheckoutBookOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        ArrayList<Book> books = this.library.getBooks();
        int numberOfBooks = books.size();
        for(int i = 0; i< numberOfBooks; i++) {
            if(!books.get(i).getCheckedOut()) {
                menuOptions.put((String.valueOf((char) (i+97))), books.get(i).getName() +"|" + books.get(i).getAuthor() +"|" + books.get(i).getYear());
            }
        }
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    private Hashtable<String, String> appendCheckoutMovieOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        ArrayList<Movie> movies = this.library.getMovies();
        int numberOfMovies = movies.size();
        ArrayList<Book> books = this.library.getBooks();
        for(int i = 0; i< numberOfMovies; i++) {
            if(!movies.get(i).getCheckedOut()) {
                menuOptions.put((String.valueOf((char) (i+97))), movies.get(i).getName() +"|" + movies.get(i).getDirector() +"|" + movies.get(i).getYear());
            }
        }
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    private Hashtable<String, String> appendReturnOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        ArrayList<LibraryItem> checkedOut = library.getCheckedOutItems();
        for(int i = 0; i < checkedOut.size(); i++) {
            if(Book.class.isInstance(checkedOut.get(i))) {
                Book book = (Book) checkedOut.get(i);
                menuOptions.put((String.valueOf((char) (i + 97))), "BOOK: " + book.getName() + "|" + book.getAuthor() + "|" + book.getYear());
            }
            else if(Movie.class.isInstance(checkedOut.get(i))) {
                Movie movie = (Movie) checkedOut.get(i);
                menuOptions.put((String.valueOf((char) (i + 97))), "MOVIE: " + movie.getName() + "|" + movie.getDirector() + "|" + movie.getYear());
            }
        }
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    private Hashtable<String, String> appendMovielistOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("2", "Checkout a movie");
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }




    /**
     * This method is invoked once user input has been validated/checked. Once the user input corresponds to one of the options, this method is invoked.
     * It uses the user input and calculates the changes that need to happen in the system.
     * As a minimum it will change the menu state, but it also handles the printing of books to be checked out/returned.
     * @param input the validated user input that corresponds to one of the menu options.
     */
    public void makeChoice(String input) {
        if(menuState.equals("Login-id")) {
            if(library.doesUserExist(input)) {
                currentUser = library.findUserById(input);
                setMenuState("Login-password");
            } else {
                System.out.println("You have entered an ID that is not stored in this system. Please try again.");
            }


        } else {
            switch (input) {
//            It goes case by case on the user input and then makes changes to the system depending on the current menu state.
                case "1":
                    if (this.menuState.equals("Home")) {
                        setMenuState("Home");
                    } else if (this.menuState.equals("Booklist")) {
                        setMenuState("Home");
                    } else if (this.menuState.equals("CheckoutBook")) {
                        setMenuState("Booklist");
                    } else if (this.menuState.equals("Return")) {
                        setMenuState("Home");
                    }
                    break;

                case "2":
                    if (this.menuState.equals("Home")) {
                        library.showBooks();
                        setMenuState("Booklist");
                    } else if (this.menuState.equals("Booklist")) {
                        setMenuState("CheckoutBook");
                    } else if (this.menuState.equals("Movielist")) {
                        setMenuState("CheckoutMovie");
                    }
                    break;

                case "3":
                    if (this.menuState.equals("Home")) {
                        setMenuState("Return");
                    }
                    break;

                case "4":
                    if (this.menuState.equals("Home")) {
                        library.showMovies();
                        setMenuState("Movielist");
                    }
                    break;

                case "8":
                    setMenuState("Home");
                    break;

                case "9":
                    System.out.println("System exiting...");
                    this.systemActive = false;
                    System.exit(1);
                    break;

//          The default handles the current non-numerical inputs.
//          It is currently only used to display books when the checkout or return menu is selected.
//          The book selection interface is prefaced by letters instead of numbers, which is why the (input.charAt(0) - 97 exists. It starts at a and goes to b, c etc
                default:
                    if (this.menuState.equals("CheckoutBook")) {
                        ArrayList<Book> books = library.getBooks();
                        Book book = books.get((input.charAt(0) - 97));
                        book.checkout();
                    } else if (this.menuState.equals("CheckoutMovie")) {
                        ArrayList<Movie> movies = library.getMovies();
                        Movie movie = movies.get((input.charAt(0) - 97));
                        movie.checkout();
                    } else if (this.menuState.equals("Return")) {
                        ArrayList<LibraryItem> checkedOutItems = library.getCheckedOutItems();
                        if (Book.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                            Book book = (Book) checkedOutItems.get((input.charAt(0) - 97));
                            book.returnItem();
                        } else if (Movie.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                            Movie movie = (Movie) checkedOutItems.get((input.charAt(0) - 97));
                            movie.returnItem();
                        }
                    }
                    break;
            }
        }
    }


    /**
     * Set methods for menuState. Only really used for testing purposes.
     * @param menuState the state of the menu "Home", "Return", "CheckoutBook" etc...
     */
    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    /**
     * Get method for systemActive. Once the system is quit, the value will be false, and the program will be exit.
     * @return systemActive a boolean value that shows whether the system should be on or off.
     */
    public Boolean getSystemActive() {
        return systemActive;
    }

    /**
     * This method saves the menu configuration from the last time it was printed. This was precautionary in case the menu needed to be reprinted at any point.
     * @param lastMenuOptions the menu configuration and options
     */
    public void setLastMenuOptions(Hashtable<String, String> lastMenuOptions) {
        this.lastMenuOptions = lastMenuOptions;
    }

    /**
     * Get method for the last menu options that were printed.
     * @return lastMenuOptions which is the menu configuration and options from the last time the menu was printed.
     */
    public Hashtable<String, String> getLastMenuOptions() {
        return lastMenuOptions;
    }

    /**
     * This method prints the menu after being given the hash value-pairs of the possible user choices
     * @param menuOptions the pre-calculated options that the user can choose from.
     */
    private void printMenu(Hashtable<String, String> menuOptions) {
//        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
        System.out.println("Biblioteca Menu -> " + menuState);
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+menuOptions.get(key));
        }
//        System.out.println("__________________________");
//        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
//        System.out.println("__________________________");
    }

    /**
     * This method prints the menu from the same parameters as the last time the menu was printed. This would only be used if the menu needed to be reprinted.
     * It was included so that the menu could be re-printed if it needed to be.
     */
    public void printMenu() {
//        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
        System.out.println("Biblioteca Menu -> " + menuState);
        Hashtable<String, String> menuOptions = getLastMenuOptions();
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+menuOptions.get(key));
        }
//        System.out.println("__________________________");
//        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
//        System.out.println("__________________________");

    }

    /**
     * This method checks the user input against the possible options of the menu that they were using.
     * It will not allow them to enter an incorrect option, and will instead keep prompting them until they enter a valid option.
     * @param input The user input that they attempt the first time
     * @param validOptions The ArrayList of valid options that the user is allowed to choose from in this menu state
     * @return
     */
    public String checkInput(String input, ArrayList<String> validOptions) {
        Scanner in = new Scanner(System.in);
        boolean validInput;
        if (menuState.equals("Login-id")) {
            validInput = true;
        } else { validInput = false;}

        while(!validInput) {
            if (validOptions.contains(input)) {
                validInput = true;
            } else {
                printMenu();
                System.out.println("Please select a valid option!");
                System.out.print(">");
                input = in.nextLine();
            }
        }
        return input;
    }
}
