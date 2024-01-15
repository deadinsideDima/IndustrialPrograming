import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class FabricSorterTest {

    @Test
    public void testSortByType() {
        // Подготовка
        ArrayList<Fabric> fabrics = new ArrayList<>();
        fabrics.add(new Fabric("Farmhouse", "Factory A", "100"));
        fabrics.add(new Fabric("Flat", "Factory B", "50"));
        fabrics.add(new Fabric("Cottege", "Factory C", "200"));

        ArrayList<Fabric> expectedList = new ArrayList<>(fabrics);
        expectedList.sort(Comparator.comparing(Fabric::getType));

        // Выполнение
        FabricSorter.sortByType(fabrics);

        // Проверка
        assertEquals(expectedList.size(), fabrics.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getType(), fabrics.get(i).getType());
            assertEquals(expectedList.get(i).getPlace(), fabrics.get(i).getPlace());
            assertEquals(expectedList.get(i).getAmount(), fabrics.get(i).getAmount());
        }
    }

    @Test
    public void testSortByPlace() {
        // Подготовка
        ArrayList<Fabric> fabrics = new ArrayList<>();
        fabrics.add(new Fabric("Farmhouse", "Factory A", "100"));
        fabrics.add(new Fabric("Flat", "Factory B", "50"));
        fabrics.add(new Fabric("Cottege", "Factory C", "200"));

        ArrayList<Fabric> expectedList = new ArrayList<>(fabrics);
        expectedList.sort(Comparator.comparing(Fabric::getPlace));

        // Выполнение
        FabricSorter.sortByPlace(fabrics);

        // Проверка
        assertEquals(expectedList.size(), fabrics.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getType(), fabrics.get(i).getType());
            assertEquals(expectedList.get(i).getPlace(), fabrics.get(i).getPlace());
            assertEquals(expectedList.get(i).getAmount(), fabrics.get(i).getAmount());
        }
    }

    @Test
    public void testSortByAmount() {
        // Подготовка
        ArrayList<Fabric> fabrics = new ArrayList<>();
        fabrics.add(new Fabric("Cotton", "Factory A", "100"));
        fabrics.add(new Fabric("Wool", "Factory B", "50"));
        fabrics.add(new Fabric("Silk", "Factory C", "200"));

        ArrayList<Fabric> expectedList = new ArrayList<>(fabrics);
        expectedList.sort(Comparator.comparing(Fabric::getAmount));

        // Выполнение
        FabricSorter.sortByAmount(fabrics);

        // Проверка
        assertEquals(expectedList.size(), fabrics.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getType(), fabrics.get(i).getType());
            assertEquals(expectedList.get(i).getPlace(), fabrics.get(i).getPlace());
            assertEquals(expectedList.get(i).getAmount(), fabrics.get(i).getAmount());
        }
    }
}
