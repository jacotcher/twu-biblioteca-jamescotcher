import java.util.*;
import java.util.regex.*;

public class Menu {
    private String menuState = "Home";
    private Library library;
    private  Boolean systemActive = true;
    private Hashtable<String, String> lastMenuOptions;

    public Menu(Library library) {
        this.library = library;
    }

    public String getMenuState() {
        return menuState;
    }

    public void makeChoice(String input) {
        switch(input) {

            case "9":
                System.out.println("System exiting...");
                this.systemActive = false;
                System.exit(1);
                break;
            case "2":
                if(this.menuState.equals("Home")) {
                    library.showBooks();
                    setMenuState("Booklist");
                }else if(this.menuState.equals("Booklist")) {
                    setMenuState("Checkout");
                }
                break;
            case "1":
                if(this.menuState.equals("Home")) {
                    setMenuState("Home");
                }else if(this.menuState.equals("Booklist")) {
                    setMenuState("Home");
                } else if(this.menuState.equals("Checkout")) {
                    setMenuState("Booklist");
                }
                break;
            case "8":
                setMenuState("Home");
                break;
            default:
                ArrayList<Book> books = library.getBooks();
                Book book = books.get((input.charAt(0) - 97));
                if(book.checkout()) {
                    System.out.println("Thank you! Enjoy the book");
                }
                else {
                    System.out.println("Sorry, that book is not available");
                }
                break;
        }


    }

    public ArrayList<String> getMenuOptions() {

        String menuState = this.getMenuState();
        Hashtable<String, String> menuOptions = new Hashtable<String, String>();


        switch(menuState) {
            case "Home":
                menuOptions.put("1", "Back");
                menuOptions.put("2", "List all books");
                break;
            case "Booklist":
                menuOptions.put("2", "Checkout a book");
                menuOptions.put("1", "Back");
                menuOptions.put("8", "Home");

                break;
            case "Checkout":
                ArrayList<Book> books = library.getBooks();
                int numberOfBooks = books.size();
                for(int i = 0; i< numberOfBooks; i++) {
                    menuOptions.put((String.valueOf((char) (i+97))), books.get(i).getName() +"|" + books.get(i).getAuthor() +"|" + books.get(i).getYear());
                }
                menuOptions.put("1", "Back");
                menuOptions.put("8", "Home");


        }
        menuOptions.put("9", "Quit");
        setLastMenuOptions(menuOptions);
        printMenu(menuOptions);

        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());

        return keys;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public Boolean getSystemActive() {
        return systemActive;
    }

    public void setLastMenuOptions(Hashtable<String, String> lastMenuOptions) {
        this.lastMenuOptions = lastMenuOptions;
    }

    public Hashtable<String, String> getLastMenuOptions() {
        return lastMenuOptions;
    }

    private void printMenu(Hashtable<String, String> menuOptions) {
        System.out.println("Biblioteca Menu");
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+menuOptions.get(key));
        }
        System.out.println("____________________________________");
    }

    public void printMenu() {
        System.out.println("Biblioteca Menu");
        Hashtable<String, String> menuOptions = getLastMenuOptions();
        ArrayList<String> keys = new ArrayList<String>(menuOptions.keySet());
        Collections.sort(keys);
        for(String key: keys){
            System.out.println(key+". "+menuOptions.get(key));
        }
        System.out.println("____________________________________");

    }

    public String checkInput(String input, ArrayList<String> validOptions) {
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        do {
            if (validOptions.contains(input)) {
                validInput = true;
            } else {
                printMenu();
                System.out.println("Please select a valid option!");
                input = in.nextLine();
            }
        } while(!validInput);
        return input;
    }
}
