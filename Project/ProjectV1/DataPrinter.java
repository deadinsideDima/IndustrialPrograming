import java.util.ArrayList;
import java.util.Iterator;

public class DataPrinter {
    private static DataPrinter instance;

    private DataPrinter() {
        // Private constructor to prevent instantiation outside this class
    }

    public static DataPrinter getInstance() {
        if (instance == null) {
            instance = new DataPrinter();
        }
        return instance;
    }

    public void printMethod1(ArrayList<Fabric> txt) {
        System.out.println("--- Method 1 ---");
        for (Fabric fabric : txt) {
            System.out.println(fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount());
        }
        System.out.println();
    }

    public void printMethod2(ArrayList<Fabric> txt) {
        System.out.println("--- Method 2 ---");
        txt.forEach(fabric -> System.out.println(fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount()));
        System.out.println();
    }

    public void printMethod3(ArrayList<Fabric> txt) {
        System.out.println("--- Method 3 ---");
        Iterator<Fabric> fabricIterator = txt.iterator();
        while (fabricIterator.hasNext()) {
            Fabric fabric = fabricIterator.next();
            System.out.println(fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount());
        }
        System.out.println();
    }
}
