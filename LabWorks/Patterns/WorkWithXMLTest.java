import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkWithXMLTest {

    @Test
    public void testReadFromFileXML() {
        // Подготовка
        ArrayList<Fabric> expectedFabrics = new ArrayList<>();
        expectedFabrics.add(new Fabric("Farmhouse", "Puhovichi", "5"));
        expectedFabrics.add(new Fabric("Cottage", "Minsk", "200"));

        // Выполнение
        ArrayList<Fabric> actualFabrics = WorkWithXML.ReadFromFileXML("in_file.xml");

        // Проверка
        assertEquals(expectedFabrics.size(), actualFabrics.size());
        for (int i = 0; i < expectedFabrics.size(); i++) {
            assertEquals(expectedFabrics.get(i).getType(), actualFabrics.get(i).getType());
            assertEquals(expectedFabrics.get(i).getPlace(), actualFabrics.get(i).getPlace());
            assertEquals(expectedFabrics.get(i).getAmount(), actualFabrics.get(i).getAmount());
        }
    }

    @Test
    public void testWriteInFileXML() {
        // Подготовка
        ArrayList<Fabric> fabricsToWrite = new ArrayList<>();
        fabricsToWrite.add(new Fabric("Farmhouse", "Puhovichi", "5"));
        fabricsToWrite.add(new Fabric("Cottage", "Minsk", "200"));

        String filePath = "test_output.xml";

        // Выполнение
        WorkWithXML.WriteInFileXML(fabricsToWrite, filePath);
        ArrayList<Fabric> readFabrics = WorkWithXML.ReadFromFileXML(filePath);

        // Проверка
        assertEquals(fabricsToWrite.size(), readFabrics.size());
        for (int i = 0; i < fabricsToWrite.size(); i++) {
            assertEquals(fabricsToWrite.get(i).getType(), readFabrics.get(i).getType());
            assertEquals(fabricsToWrite.get(i).getPlace(), readFabrics.get(i).getPlace());
            assertEquals(fabricsToWrite.get(i).getAmount(), readFabrics.get(i).getAmount());
        }
    }
}
