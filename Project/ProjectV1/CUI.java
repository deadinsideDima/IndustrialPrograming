import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CUI {
    private static final String SECRET_KEY = "YourSecretKey123";

    private static void displayMenu() {
        System.out.println("\n===== Fabric Data Management Menu =====");
        System.out.println("1. Display Fabric Details");
        System.out.println("2. Add New Fabric");
        System.out.println("3. Display Encrypted Data");
        System.out.println("4. Display Decrypted Data");
        System.out.println("5. Display Fabric Map Details");
        System.out.println("6. Archive Data");
        System.out.println("7. Write to XML");
        System.out.println("8. Write to JSON");
        System.out.println("9. Write to TXT");
        System.out.println("10. Exit");
        System.out.println("Enter your choice (1-10): ");
    }

    private static void displayFabricDetails(ArrayList<Fabric> fabrics) {
        OutputResults.OutputResultsBuilder outputBuilder = new OutputResults.OutputResultsBuilder();

        outputBuilder.setFabricDetailsTitle("Fabric Details").setFabrics(fabrics);
        DataPrinter dataPrinter = DataPrinter.getInstance();
        dataPrinter.printMethod1(fabrics);
        dataPrinter.printMethod2(fabrics);
        dataPrinter.printMethod3(fabrics);
    }

    private static void addNewFabric(Scanner scanner, ArrayList<Fabric> fabrics) {
        System.out.println("Enter new Fabric data (Type Place Amount):");
        String newType = scanner.next();
        String newPlace = scanner.next();
        String newAmount = scanner.next();

        FabricBuilder fabricBuilder = FabricBuilder.getInstance();
        Fabric newFabric = fabricBuilder.setType(newType).setPlace(newPlace).setAmount(newAmount).build();
        fabrics.add(newFabric);

        System.out.println("New Fabric added successfully!");
    }

    private static void displayEncryptedData(ArrayList<Fabric> fabrics) {
        // Display encrypted data
        ArrayList<String> encryptedDataList = ContainerCreator.createEncryptedData(fabrics, SECRET_KEY);
        for (String encryptedData : encryptedDataList) {
            System.out.println(encryptedData);
        }
    }

    private static void displayDecryptedData(ArrayList<Fabric> fabrics) {
        OutputResults.OutputResultsBuilder outputBuilder = new OutputResults.OutputResultsBuilder();

        outputBuilder.setFabricDetailsTitle("Fabric Details").setFabrics(fabrics);
        ArrayList<String> encryptedDataList = ContainerCreator.createEncryptedData(fabrics, SECRET_KEY);
        outputBuilder.setEncryptedDataTitle("Encrypted Data").setEncryptedDataList(encryptedDataList);
        ArrayList<String> decryptedDataList = new ArrayList<>();
        for (String encryptedData : encryptedDataList) {
            String decryptedData = EncryptionUtil.decryptData(encryptedData, SECRET_KEY);
            decryptedDataList.add(decryptedData);
        }
        outputBuilder.setDecryptedDataTitle("Decrypted Data").setDecryptedDataList(decryptedDataList);
        OutputResults output = outputBuilder.build();
        output.displayDecryptedData();
    }

    private static void displayFabricMapDetails(ArrayList<Fabric> fabrics) {
        // Display fabric map details
        Map<String, Fabric> fabricMap = ContainerCreator.createFabricMap(fabrics);
        for (Map.Entry<String, Fabric> entry : fabricMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    private static void archiveData(ArrayList<Fabric> fabrics) {
        // Вызываем методы для архивации данных
        Archieve.createZipArchive(fabrics, "house_archive.zip");
        Archieve.createJarArchive(fabrics, "fabrics.jar");
        Archieve.convertZipToRar("house_archive.zip", "house_archive.rar");

        System.out.println("Data has been archived.");
    }

    private static void exitProgram(Scanner scanner) {
        System.out.println("Exiting the program. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Fabric> fabrics = ContainerCreator.createFabricListFromTxtFile("in_file.txt");

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        displayFabricDetails(fabrics);
                        break;
                    case 2:
                        addNewFabric(scanner, fabrics);
                        break;
                    case 3:
                        displayEncryptedData(fabrics);
                        break;
                    case 4:
                        displayDecryptedData(fabrics);
                        break;
                    case 5:
                        displayFabricMapDetails(fabrics);
                        break;
                    case 6:
                        archiveData(fabrics);
                        break;
                    case 7:
                        writeXML(fabrics);
                        break;
                    case 8:
                        writeJSON(fabrics);
                        break;
                    case 9:
                        writeTXT(fabrics);
                        break;
                    case 10:
                        exitProgram(scanner);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    private static void writeXML(ArrayList<Fabric> fabrics) {
        ArrayList<Fabric> xml = ContainerCreator.createFabricListFromXMLFile("in_file.xml");
        WorkWithXML.WriteInFileXML(xml, "out_file.xml");
        System.out.println("Data has been written to XML file.");
    }

    private static void writeJSON(ArrayList<Fabric> fabrics) throws IOException {
        ArrayList<Fabric> json = ContainerCreator.createFabricListFromJSONFile("in_file.json");
        WorkWithJSON.WriteInFileJSON(json);
        System.out.println("Data has been written to JSON file.");
    }

    private static void writeTXT(ArrayList<Fabric> fabrics) {
        WorkWithTXT.WriteInFileTXT(fabrics);
        System.out.println("Data has been written to TXT file.");
    }
}
