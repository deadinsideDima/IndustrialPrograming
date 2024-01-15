import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String SECRET_KEY = "YourSecretKey123";

    public static void main(String[] args) throws Exception {
        OutputResults.OutputResultsBuilder outputBuilder = new OutputResults.OutputResultsBuilder();

        ArrayList<Fabric> txt = ContainerCreator.createFabricListFromTxtFile("in_file.txt");
        outputBuilder.setFabricDetailsTitle("Fabric Details").setFabrics(txt);

        DataPrinter dataPrinter = DataPrinter.getInstance();
        dataPrinter.printMethod1(txt);
        dataPrinter.printMethod2(txt);
        dataPrinter.printMethod3(txt);

        OutputResults output = outputBuilder.build();

        ArrayList<String> encryptedDataList = ContainerCreator.createEncryptedData(txt, SECRET_KEY);
        outputBuilder.setEncryptedDataTitle("Encrypted Data").setEncryptedDataList(encryptedDataList);

        ArrayList<String> decryptedDataList = new ArrayList<>();
        for (String encryptedData : encryptedDataList) {
            String decryptedData = EncryptionUtil.decryptData(encryptedData, SECRET_KEY);
            decryptedDataList.add(decryptedData);
        }
        outputBuilder.setDecryptedDataTitle("Decrypted Data").setDecryptedDataList(decryptedDataList);

        ArrayList<Fabric> xml = ContainerCreator.createFabricListFromXMLFile("in_file.xml");
        WorkWithXML.WriteInFileXML(xml, "out_file.xml");

        ArrayList<Fabric> json = ContainerCreator.createFabricListFromJSONFile("in_file.json");
        WorkWithJSON.WriteInFileJSON(json);

        WorkWithTXT.WriteInFileTXT(txt);

        Map<String, Fabric> fabricMap = ContainerCreator.createFabricMap(txt);
        outputBuilder.setFabricMapDetailsTitle("Fabric Map Details").setFabricMap(fabricMap);

        FabricSorter.sortByType(txt);
        outputBuilder.setFabricDetailsTitle("Sorted Fabric Details").setFabrics(txt);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новые данные Fabric (Type Place Amount):");

        String newType = scanner.next();
        String newPlace = scanner.next();
        String newAmount = scanner.next();

        FabricBuilder fabricBuilder = FabricBuilder.getInstance();
        Fabric fabric1 = fabricBuilder.setType(newType).setPlace(newPlace).setAmount(newAmount).build();
        txt.add(fabric1);

        outputBuilder.setFabricDetailsTitle("Updated Fabric Details").setFabrics(txt);
        fabricMap = ContainerCreator.createFabricMap(txt);
        outputBuilder.setFabricMapDetailsTitle("Updated Fabric Map Details").setFabricMap(fabricMap);

        DataValidator.validateData(txt);

        Archieve.createZipArchive(txt, "house_archive.zip");
        Archieve.createZipArchive(txt, "house_archive1.rar");
        Archieve.createJarArchive(txt, "fabrics.jar");
        Archieve.convertZipToRar("house_archive.zip", "house_archive.rar");

        outputBuilder.setArchiveCreationResult("house_archive.zip");
        outputBuilder.setArchiveCreationResult("house_archive1.rar");
        outputBuilder.setArchiveCreationResult("fabrics.jar");
        ArrayList<String> extractedFromZip = Archieve.extractFromZip("house_archive.zip");
        ArrayList<String> extractedFromJar = Archieve.extractFromJar("fabrics.jar");

        output.displayExtractedZipContents(extractedFromZip);
        output.displayExtractedJarContents(extractedFromJar);

        output = outputBuilder.build();

        // Вывод результатов
        output.displayFabricDetails();
        output.displayEncryptedData();
        output.displayDecryptedData();
        output.displayFabricMapDetails();

        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Введите данные:");
        String type = scanner1.nextLine();
        String place = scanner1.nextLine();
        String amount = scanner1.nextLine();

        AbstractFabric fabric = new Fabric(type, place, amount);

        System.out.println("Введите метаданные:");
        String metadata = scanner1.nextLine();

        FabricMetadataDecorator fabricWithMetadata = new FabricMetadataDecorator(fabric, metadata);

        System.out.println("Новая Информация:");
        System.out.println("Тип: " + fabricWithMetadata.getType());
        System.out.println("Место: " + fabricWithMetadata.getPlace());
        System.out.println("Количество: " + fabricWithMetadata.getAmount());
        System.out.println("Метаданные: " + fabricWithMetadata.getMetadata().getMetadata());
        scanner1.close();

    }
}
