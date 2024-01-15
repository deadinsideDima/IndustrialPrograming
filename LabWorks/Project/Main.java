import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;

public class Main {
    private static final String SECRET_KEY = "YourSecretKey123";

    public static void main(String[] args) throws Exception {
        ArrayList<Fabric> txt = new ArrayList<>(WorkWithTXT.ReadFromFileTXT("in_file.txt"));

        System.out.println("--- Encrypted Data ---");
        ArrayList<String> encryptedDataList = new ArrayList<>();
        for (Fabric fabric : txt) {
            String data = fabric.getType() + "\n" + fabric.getPlace() + "\n" + fabric.getAmount();
            String encryptedData = EncryptionUtil.encryptData(data, SECRET_KEY);
            encryptedDataList.add(encryptedData);
            System.out.println(encryptedData);
        }

        // Decrypt encrypted data
        System.out.println("\n--- Decrypted Data ---");
        for (String encryptedData : encryptedDataList) {
            String decryptedData = EncryptionUtil.decryptData(encryptedData, SECRET_KEY);
            System.out.println(decryptedData);
        }

        ArrayList<Fabric> xml = new ArrayList<>(WorkWithXML.ReadFromFileXML("in_file.xml"));
        WorkWithXML.WriteInFileXML(xml, "out_file.xml");

        ArrayList<Fabric> json = new ArrayList<>(WorkWithJSON.ReadFromFileJSON("in_file.json"));
        WorkWithJSON.WriteInFileJSON(json);

        WorkWithTXT.WriteInFileTXT(txt);

        System.out.println("--- Method 1 ---");
        for (Fabric p : txt) {
            System.out.println(p.getType() + " " + p.getPlace() + " " + p.getAmount());
        }
        System.out.println("\n");

        System.out.println("--- Method 2 ---");
        txt.forEach(n -> System.out.println(n.getType() + " " + n.getPlace() + " " + n.getAmount()));
        System.out.println("\n");

        System.out.println("--- Method 3 ---");
        Iterator<Fabric> fabricIterator = txt.iterator();
        while (fabricIterator.hasNext()) {
            Fabric t = fabricIterator.next();
            System.out.println(t.getType() + " " + t.getPlace() + " " + t.getAmount());
        }


        FabricSorter.sortByType(txt);
        System.out.println("--- Sorted by Type ---");
        txt.forEach(fabric -> System.out.println(fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount()));


        Map<String, Fabric> fabricMap = new HashMap<>();


        for (Fabric fabric : txt) {
            fabricMap.put(fabric.getType(), fabric);
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новые данные Fabric (Type Place Amount):");

        String newType = scanner.next();
        String newPlace = scanner.next();
        String newAmount = scanner.next();


        Fabric newFabric = new Fabric(newType, newPlace, newAmount);


        fabricMap.put(newFabric.getType(), newFabric);

        System.out.println("--- Contents of Fabric Map ---");
        for (Map.Entry<String, Fabric> entry : fabricMap.entrySet()) {
            String fabricType = entry.getKey();
            Fabric fabric = entry.getValue();
            System.out.println("Fabric Details: " + fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount());
        }

        DataValidator.validateData(txt);
        Archieve.createZipArchive(txt, "house_archive.zip");
        Archieve.createZipArchive(txt, "house_archive1.zip");
        Archieve.createJarArchive(txt, "fabrics.jar");
        Archieve.convertZipToRar("house_archive.zip", "house_archive.rar");
    }
}
