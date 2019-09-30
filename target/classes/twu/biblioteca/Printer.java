
/** @author James Cotcher
 * This class allows the creation of a Printer object to print messages to the command line.
 * It is used so that testing of outputs is simplified because the returned value can be tested rather than just be
 * printed in the console.
 *
 */
public class Printer {

    public Printer() {
    }
    /** Used as a replacement for System.out.print()
    * @param message The message that should be written to the console
    * @return message the message that was printed to the console. Mainly used in test cases to compare desired message to
    */
    public String print(String message) {
        System.out.print(message);
        return message;
    }

    /** Used as a replacement for System.out.println()
     * @param message The message that should be written to the console
     * @return message the message that was printed to the console. Mainly used in test cases to compare desired message to
     */
    public String println(String message) {
        System.out.println(message);
        return message;
    }
}
