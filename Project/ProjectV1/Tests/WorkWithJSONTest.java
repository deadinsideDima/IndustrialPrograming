import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WorkWithJSONTest {

    @Test
    public void testReadFromFileJSON() {
        // Подготовка
        ArrayList<Fabric> expectedList = new ArrayList<>();
        expectedList.add(new Fabric("Type1", "Place1", "Amount1"));
        expectedList.add(new Fabric("Type2", "Place2", "Amount2"));
        expectedList.add(new Fabric("Type3", "Place3", "Amount3"));

        try {
            WorkWithJSON.WriteInFileJSON(expectedList);
        } catch (IOException e) {
            fail("Failed to write to file for testing ReadFromFileJSON");
        }

        // Выполнение
        ArrayList<Fabric> actualList = WorkWithJSON.ReadFromFileJSON("out_file.json");

        // Проверка
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getType(), actualList.get(i).getType());
            assertEquals(expectedList.get(i).getPlace(), actualList.get(i).getPlace());
            assertEquals(expectedList.get(i).getAmount(), actualList.get(i).getAmount());
        }
    }

    @Test
    public void testWriteInFileJSON() {
        // Подготовка
        ArrayList<Fabric> testList = new ArrayList<>();
        testList.add(new Fabric("TestType1", "TestPlace1", "TestAmount1"));
        testList.add(new Fabric("TestType2", "TestPlace2", "TestAmount2"));

        // Выполнение и проверка
        try {
            WorkWithJSON.WriteInFileJSON(testList);
        } catch (IOException e) {
            fail("Failed to write to file for testing WriteInFileJSON");
        }

        // Проверка записанных данных в файл
        ArrayList<Fabric> readList = WorkWithJSON.ReadFromFileJSON("out_file.json");
        assertNotNull(readList);
        assertEquals(testList.size(), readList.size());
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i).getType(), readList.get(i).getType());
            assertEquals(testList.get(i).getPlace(), readList.get(i).getPlace());
            assertEquals(testList.get(i).getAmount(), readList.get(i).getAmount());
        }
    }
}
