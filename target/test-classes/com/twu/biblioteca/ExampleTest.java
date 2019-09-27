import org.hamcrest.core.Is;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ExampleTest {



    @Test
    public void printFunctionPrintsCorrectMessage() {
//        Given
        Printer printer = new Printer();
//        When
        String message = printer.print("Hello, world!");
        assertThat("Hello, world!", is(message));
    }


}
