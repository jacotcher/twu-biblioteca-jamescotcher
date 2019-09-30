import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class BibliotecaApp {

    public static void main(String[] args) {

        Library library = new Library();
        Menu menu = new Menu(library);
        library.init();
        Scanner in = new Scanner(System.in);
        String input;
        boolean systemActive = true;

        do {
            ArrayList<String> validOptions = menu.getMenuOptions();
            input = in.nextLine();
            input = menu.checkInput(input, validOptions);
            menu.makeChoice(input);

            systemActive = menu.getSystemActive();
        } while(systemActive);




    }


}
