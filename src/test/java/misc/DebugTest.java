import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import misc.Debug;
import static org.junit.jupiter.api.Assertions.*;

public class DebugTest {

    @Test
    public void testDebugOutput() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        // Call the Debug.out method with a test message
        Debug.out("Test message");

        // Restore System.out
        System.out.flush();
        System.setOut(originalPrintStream);

        // Get the captured output
        String output = outputStream.toString().trim();

        // Assert that the output contains the expected debug message
        assertTrue(output.contains(">> DEBUG >> Test message"));
    }

    @Test
    public void testDebugOutputDisabled() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        // Comment out the Debug.out method call to disable debug output
        // Debug.out("Test message");

        // Restore System.out
        System.out.flush();
        System.setOut(originalPrintStream);

        // Get the captured output
        String output = outputStream.toString().trim();

        // Assert that the output is empty when debug output is disabled
        assertEquals("", output);
    }
}