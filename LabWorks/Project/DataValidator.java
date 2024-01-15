import java.util.ArrayList;

public class DataValidator {

    public static void validateData(ArrayList<Fabric> fabrics) {
        System.out.println("--- Data Validation ---");
        for (Fabric fabric : fabrics) {
            if (fabric.getType() == null || fabric.getPlace() == null || fabric.getAmount() == null) {
                System.out.println("Invalid data found:");
                System.out.println("Type: " + fabric.getType());
                System.out.println("Place: " + fabric.getPlace());
                System.out.println("Amount: " + fabric.getAmount());
            } else {
                System.out.println("Data is valid for:");
                System.out.println("Type: " + fabric.getType());
                System.out.println("Place: " + fabric.getPlace());
                System.out.println("Amount: " + fabric.getAmount());
            }
            System.out.println("----------------------");
        }
    }
}
