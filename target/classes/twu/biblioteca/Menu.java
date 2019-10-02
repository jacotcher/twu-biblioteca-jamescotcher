import java.util.*;
import java.util.regex.*;

/**
 * Menu is the class that handles all user interaction with the books and library.
 * It contains all methods for user interaction and displaying results/menus.
 *
 */
public class Menu {/**
 * @param currentMenuOptions The menu options that have already been appended to the list
 * @return an arraylist containing any options that should be appended to the menuOptions list
 */
    private String menuState = "Login-id";
    private Library library;
    private  Boolean systemActive = true;
    private Hashtable<String, String> lastMenuOptions;
    private User currentUser;
    private String attemptedLogInAs;

    /**
     * The constructor class. Requires an instance of library
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
     * This method tests the menuStatue and appends the appropriate menu options before printing them
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
                break;
            case "Login-password":
                menuOptions.putAll(appendLoginPasswordOptions(menuOptions));
                break;
            case "ContactInfo":
                menuOptions.putAll(appendContactInfoOptions(menuOptions));
                break;

        }


//        This stores the current menu configuration in case it needs to be re-printed
        setLastMenuOptions(menuOptions);
//        Prints the menu to the console for the user to see
        printMenu(menuOptions);

//        This creates a list of keys that are valid input options to be returned to validate the user input.
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        return keys;
    }

    /** Adds all menu-options that are required on the contact info page
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Map<? extends String,? extends String> appendContactInfoOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        if (!currentUser.getLibrarianStatus()) {
            System.out.println("Name: " + currentUser.getName());
            System.out.println("Email: " + currentUser.getEmail());
            System.out.println("Phone: " + currentUser.getPhoneNo());
        }
        else {
            System.out.println("You are the librarian, your contact details are not stored.");
        }
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    /** Adds all the menu options that are required on the login-id page
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Map<? extends String,? extends String> appendLoginIdOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("*", "Enter your library ID");
        return menuOptions;
    }

    /** Adds all the menu options that are requried on the login-password page
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Map<? extends String,? extends String> appendLoginPasswordOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("*", "Enter your password");
        return menuOptions;

    }

    /** Adds all the menu options that are required on the home page
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Hashtable<String, String> appendHomeOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("2", "List all books");
        menuOptions.put("3", "Return items");
        menuOptions.put("4", "List all movies");
        menuOptions.put("5", "View my contact information");
        menuOptions.put("1", "Logout");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    /**Adds all the menu options that are required on the booklist page
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Hashtable<String, String> appendBooklistOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        menuOptions.put("2", "Checkout a book");
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    /** Adds all the menu options that are required on the checkoutBook page. Only displays books that have not been checked out yet.
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
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

    /** Adds all the menu options that are required on the checkoutMovie page. Only displays movies that have not been checked out yet.
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
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

    /** Adds all menu options that are required on the return page.
     * This method tests whether the user is a librarian or not, and adds the books or movies that
     * the current user needs to return. Or returns all of them if the user is a librarian
     * @param currentMenuOptions The menu options that have already been appended to the list.
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
    private Hashtable<String, String> appendReturnOptions(Hashtable<String, String> currentMenuOptions) {
        Hashtable<String, String> menuOptions = currentMenuOptions;
        ArrayList<LibraryItem> checkedOut = null;
        if(currentUser.getLibrarianStatus()) {
            checkedOut = library.getCheckedOutItems();
        }else {checkedOut = library.getUserCheckedOutItems(currentUser);}

        for(int i = 0; i < checkedOut.size(); i++) {
            if(Book.class.isInstance(checkedOut.get(i))) {
                Book book = (Book) checkedOut.get(i);
                if (currentUser.getLibrarianStatus()) {
                    menuOptions.put((String.valueOf((char) (i + 97))), "BOOK: " + book.getName() + "|" + book.getAuthor() + "|" + book.getYear() + " IS WITH: " + book.checkedOutBy.getName());
                } else {
                    menuOptions.put((String.valueOf((char) (i + 97))), "BOOK: " + book.getName() + "|" + book.getAuthor() + "|" + book.getYear());
                }
            }
            else if(Movie.class.isInstance(checkedOut.get(i))) {
                Movie movie = (Movie) checkedOut.get(i);
                if (currentUser.getLibrarianStatus()) {
                    menuOptions.put((String.valueOf((char) (i + 97))), "MOVIE: " + movie.getName() + "|" + movie.getDirector() + "|" + movie.getYear() + " IS WITH: " + movie.checkedOutBy.getName());
                } else {
                    menuOptions.put((String.valueOf((char) (i + 97))), "MOVIE: " + movie.getName() + "|" + movie.getDirector() + "|" + movie.getYear());
                }

            }
        }
        menuOptions.put("1", "Back");
        menuOptions.put("8", "Home");
        menuOptions.put("9", "Quit");
        return menuOptions;
    }

    /** Adds all the menu options that are required on the movielist page.
     * @param currentMenuOptions The menu options that have already been appended to the list
     * @return an arraylist containing any options that should be appended to the menuOptions list
     */
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
     * As a minimum it will change the menu state, but it also handles the printing of books to be checked out/returned and the login system.
     * @param input the validated user input that corresponds to one of the menu options.
     */
    public void makeChoice(String input) {
        if(menuState.equals("Login-id") || menuState.equals("Login-password")) {
            switch (menuState) {
                case "Login-id":
                    if (library.doesUserExist(input)) {
                        attemptedLogInAs = input;
                        setMenuState("Login-password");
                    } else {
                        System.out.println("You have entered an ID that is not stored in this system. Please try again.");
                    }
                    break;
                case "Login-password":
                    User tryingUser = library.findUserById(attemptedLogInAs);
                    if (tryingUser.checkPassword(input)) {
                        setMenuState("Home");
                        currentUser = tryingUser;
                    }
                    else {
                        setMenuState("Login-id");
                        System.out.println("The password that you have entered is incorrect. Please try logging in again.");
                    }
                    break;
            }


        } else {
            switch (input) {
//            It goes case by case on the user input and then makes changes to the system depending on the current menu state.
                case "1":
                    if (this.menuState.equals("Home")) {
                        setMenuState("Login-id");
                        currentUser = null;
                    } else if (this.menuState.equals("Booklist")) {
                        setMenuState("Home");
                    } else if (this.menuState.equals("CheckoutBook")) {
                        setMenuState("Booklist");
                    } else if (this.menuState.equals("CheckoutMovie")) {
                        setMenuState("Movielist");
                    } else if (this.menuState.equals("Return")) {
                        setMenuState("Home");
                    } else if (this.menuState.equals("ContactInfo")) {
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

                case "5":
                    setMenuState("ContactInfo");

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
                        book.checkout(currentUser);
                    } else if (this.menuState.equals("CheckoutMovie")) {
                        ArrayList<Movie> movies = library.getMovies();
                        Movie movie = movies.get((input.charAt(0) - 97));
                        movie.checkout(currentUser);
                    } else if (this.menuState.equals("Return")) {
                        if(!currentUser.getLibrarianStatus()) {
                            ArrayList<LibraryItem> checkedOutItems = library.getUserCheckedOutItems(currentUser);
                            if (Book.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                                Book book = (Book) checkedOutItems.get((input.charAt(0) - 97));
                                book.returnItem();
                            } else if (Movie.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                                Movie movie = (Movie) checkedOutItems.get((input.charAt(0) - 97));
                                movie.returnItem();
                            }
                        } else if(currentUser.getLibrarianStatus()) {
                            ArrayList<LibraryItem> checkedOutItems = library.getCheckedOutItems();
                            if (Book.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                                Book book = (Book) checkedOutItems.get((input.charAt(0) - 97));
                                book.returnItem();
                            } else if (Movie.class.isInstance(checkedOutItems.get((input.charAt(0) - 97)))) {
                                Movie movie = (Movie) checkedOutItems.get((input.charAt(0) - 97));
                                movie.returnItem();
                            }
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
        if (menuState.equals("Login-password")) {
            System.out.println("Hello " + library.findUserById(attemptedLogInAs).getName());
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
        System.out.println("Biblioteca Menu -> " + menuState);
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+menuOptions.get(key));
        }
        System.out.println("__________________________");
    }

    /**
     * This method prints the menu from the same parameters as the last time the menu was printed. This would only be used if the menu needed to be reprinted.
     * It was included so that the menu could be re-printed if it needed to be.
     */
    public void printMenu() {
        if (menuState.equals("Login-password")) {
            System.out.println("Hello " + library.findUserById(attemptedLogInAs).getName());
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ ");
        System.out.println("Biblioteca Menu -> " + menuState);
        ArrayList<String> keys = new ArrayList<String>(lastMenuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+lastMenuOptions.get(key));
        }
        System.out.println("__________________________");
    }

    /**
     * This method checks the user input against the possible options of the menu that they were using.
     * It will not allow them to enter an incorrect option, and will instead keep prompting them until they enter a valid option.
     * The only exception is on the login-pages the inputs are allowed to pass through to be checked in makeChoice.
     * @param input The user input that they attempt the first time
     * @param validOptions The ArrayList of valid options that the user is allowed to choose from in this menu state
     * @return Either the original input (if it is valid) or a new valid-input
     */
    public String checkInput(String input, ArrayList<String> validOptions) {
        Scanner in = new Scanner(System.in);
        boolean validInput;
        if (menuState.equals("Login-id") || menuState.equals("Login-password")) {
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

    /**
     * set method for setting the current user. Only used for testing purposes.
     * @param currentUser The user that is to be "logged on"
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
