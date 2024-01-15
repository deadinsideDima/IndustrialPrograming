import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataPrinterTest {

    @Test
    public void testPrintMethod1() {
        // Arrange
        DataPrinter dataPrinter = DataPrinter.getInstance();
        ArrayList<Fabric> fabrics = new ArrayList<>(Arrays.asList(
                new Fabric("Farmhouse", "Factory A", "100"),
                new Fabric("Flat", "Factory B", "50")
        ));
        String expectedOutput = "--- Method 1 ---\r\nFarmhouse Factory A 100\r\nFlat Factory B 50\r\n\r\n";
        ByteArrayOutputStream outputStream = redirectSystemOut();

        // Act
        dataPrinter.printMethod1(fabrics);

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintMethod2() {
        // Arrange
        DataPrinter dataPrinter = DataPrinter.getInstance();
        ArrayList<Fabric> fabrics = new ArrayList<>(Arrays.asList(
                new Fabric("Farmhouse", "Factory A", "100"),
                new Fabric("Flat", "Factory B", "50")
        ));
        String expectedOutput = "--- Method 2 ---\r\nFarmhouse Factory A 100\r\nFlat Factory B 50\r\n\r\n";
        ByteArrayOutputStream outputStream = redirectSystemOut();

        // Act
        dataPrinter.printMethod2(fabrics);

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintMethod3() {
        // Arrange
        DataPrinter dataPrinter = DataPrinter.getInstance();
        ArrayList<Fabric> fabrics = new ArrayList<>(Arrays.asList(
                new Fabric("Farmhouse", "Factory A", "100"),
                new Fabric("Flat", "Factory B", "50")
        ));
        String expectedOutput = "--- Method 3 ---\r\nFarmhouse Factory A 100\r\nFlat Factory B 50\r\n\r\n";
        ByteArrayOutputStream outputStream = redirectSystemOut();

        // Act
        dataPrinter.printMethod3(fabrics);

        // Assert
        assertEquals(expectedOutput, outputStream.toString());
    }

    // Helper method to redirect System.out for testing
    private ByteArrayOutputStream redirectSystemOut() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }
}
