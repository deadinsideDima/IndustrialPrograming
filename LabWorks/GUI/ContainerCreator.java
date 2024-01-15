import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


interface FabricContainer {
    ArrayList<String> createEncryptedData(ArrayList<Fabric> fabrics, String secretKey);

    Map<String, Fabric> createFabricMap(ArrayList<Fabric> fabrics);

    ArrayList<Fabric> createFabricListFromTxtFile(String filename);

    ArrayList<Fabric> createFabricListFromXMLFile(String filename);

    ArrayList<Fabric> createFabricListFromJSONFile(String filename);
}

class BasicFabricContainer implements FabricContainer {
    @Override
    public ArrayList<String> createEncryptedData(ArrayList<Fabric> fabrics, String secretKey) {
        ArrayList<String> encryptedDataList = new ArrayList<>();
        for (Fabric fabric : fabrics) {
            String data = fabric.getType() + "\n" + fabric.getPlace() + "\n" + fabric.getAmount();
            String encryptedData = EncryptionUtil.encryptData(data, secretKey);
            encryptedDataList.add(encryptedData);
        }
        return encryptedDataList;
    }

    @Override
    public Map<String, Fabric> createFabricMap(ArrayList<Fabric> fabrics) {
        Map<String, Fabric> fabricMap = new HashMap<>();
        for (Fabric fabric : fabrics) {
            fabricMap.put(fabric.getType(), fabric);
        }
        return fabricMap;
    }

    @Override
    public ArrayList<Fabric> createFabricListFromTxtFile(String filename) {
        return new ArrayList<>(WorkWithTXT.ReadFromFileTXT(filename));
    }

    @Override
    public ArrayList<Fabric> createFabricListFromXMLFile(String filename) {
        return new ArrayList<>(WorkWithXML.ReadFromFileXML(filename));
    }

    @Override
    public ArrayList<Fabric> createFabricListFromJSONFile(String filename) {
        return new ArrayList<>(WorkWithJSON.ReadFromFileJSON(filename));
    }
}

abstract class FabricContainerDecorator implements FabricContainer {
    private final FabricContainer fabricContainer;

    public FabricContainerDecorator(FabricContainer fabricContainer) {
        this.fabricContainer = fabricContainer;
    }

    @Override
    public ArrayList<String> createEncryptedData(ArrayList<Fabric> fabrics, String secretKey) {
        return fabricContainer.createEncryptedData(fabrics, secretKey);
    }

    @Override
    public Map<String, Fabric> createFabricMap(ArrayList<Fabric> fabrics) {
        return fabricContainer.createFabricMap(fabrics);
    }

    @Override
    public ArrayList<Fabric> createFabricListFromTxtFile(String filename) {
        return fabricContainer.createFabricListFromTxtFile(filename);
    }

    @Override
    public ArrayList<Fabric> createFabricListFromXMLFile(String filename) {
        return fabricContainer.createFabricListFromXMLFile(filename);
    }

    @Override
    public ArrayList<Fabric> createFabricListFromJSONFile(String filename) {
        return fabricContainer.createFabricListFromJSONFile(filename);
    }
}

class FabricContainerLogger extends FabricContainerDecorator {
    public FabricContainerLogger(FabricContainer fabricContainer) {
        super(fabricContainer);
    }

    @Override
    public ArrayList<String> createEncryptedData(ArrayList<Fabric> fabrics, String secretKey) {
        System.out.println("Creating encrypted data...");
        return super.createEncryptedData(fabrics, secretKey);
    }

}


public class ContainerCreator {

    public static ArrayList<String> createEncryptedData(ArrayList<Fabric> fabrics, String secretKey) {
        ArrayList<String> encryptedDataList = new ArrayList<>();
        for (Fabric fabric : fabrics) {
            String data = fabric.getType() + "\n" + fabric.getPlace() + "\n" + fabric.getAmount();
            String encryptedData = EncryptionUtil.encryptData(data, secretKey);
            encryptedDataList.add(encryptedData);
        }
        return encryptedDataList;
    }

    public static Map<String, Fabric> createFabricMap(ArrayList<Fabric> fabrics) {
        Map<String, Fabric> fabricMap = new HashMap<>();
        for (Fabric fabric : fabrics) {
            fabricMap.put(fabric.getType(), fabric);
        }
        return fabricMap;
    }

    public static ArrayList<Fabric> createFabricListFromTxtFile(String filename) {
        return new ArrayList<>(WorkWithTXT.ReadFromFileTXT(filename));
    }

    public static ArrayList<Fabric> createFabricListFromXMLFile(String filename) {
        return new ArrayList<>(WorkWithXML.ReadFromFileXML(filename));
    }

    public static ArrayList<Fabric> createFabricListFromJSONFile(String filename) {
        return new ArrayList<>(WorkWithJSON.ReadFromFileJSON(filename));
    }

}
