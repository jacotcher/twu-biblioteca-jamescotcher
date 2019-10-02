import java.util.ArrayList;
import java.util.Scanner;

/**
 * The BibliotecaApp class contains the main class for the Biblioteca App.
 * @author James Cotcher 2019
 *
 */
public class BibliotecaApp {

    /**
     * The main class is run in order to start the system.
     * @param args
     */
    public static void main(String[] args) {
//        Required tools/objects are initiated
        Library library = new Library();
        Menu menu = new Menu(library);
        library.init();
        Scanner in = new Scanner(System.in);
        String input;
        boolean systemActive = true;

//        A while loop keeps the system running until the user selects "Exit" from the menu
        do {
            ArrayList<String> validOptions = menu.getMenuOptions();
            System.out.print(">");
            input = in.nextLine();
            input = menu.checkInput(input, validOptions);
            System.out.println("__________________________");
            for (int i = 0; i < 20; ++i) System.out.println();
            System.out.println("__________________________");
            menu.makeChoice(input);
            systemActive = menu.getSystemActive();
        } while(systemActive);
    }
}
