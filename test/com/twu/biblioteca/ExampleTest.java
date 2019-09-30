import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ExampleTest {
//    Allows you to test what has been printed to the command line
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

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

}
