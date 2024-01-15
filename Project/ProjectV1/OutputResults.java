import java.util.ArrayList;
import java.util.Map;

public class OutputResults {
    private final String encryptedDataTitle;
    private final ArrayList<String> encryptedDataList;
    private final String decryptedDataTitle;
    private final ArrayList<String> decryptedDataList;
    private final String fabricDetailsTitle;
    private final ArrayList<Fabric> fabrics;
    private final String fabricMapDetailsTitle;
    private final Map<String, Fabric> fabricMap;
    private final boolean isValid;
    private final String archiveCreationResult;

    private OutputResults(OutputResultsBuilder builder) {
        this.encryptedDataTitle = builder.encryptedDataTitle;
        this.encryptedDataList = builder.encryptedDataList;
        this.decryptedDataTitle = builder.decryptedDataTitle;
        this.decryptedDataList = builder.decryptedDataList;
        this.fabricDetailsTitle = builder.fabricDetailsTitle;
        this.fabrics = builder.fabrics;
        this.fabricMapDetailsTitle = builder.fabricMapDetailsTitle;
        this.fabricMap = builder.fabricMap;
        this.isValid = builder.isValid;
        this.archiveCreationResult = builder.archiveCreationResult;
    }

    public static class OutputResultsBuilder {
        private String encryptedDataTitle;
        private ArrayList<String> encryptedDataList;
        private String decryptedDataTitle;
        private ArrayList<String> decryptedDataList;
        private String fabricDetailsTitle;
        private ArrayList<Fabric> fabrics;
        private String fabricMapDetailsTitle;
        private Map<String, Fabric> fabricMap;
        private boolean isValid;
        private String archiveCreationResult;

        public OutputResultsBuilder() {
        }

        public OutputResultsBuilder setEncryptedDataTitle(String title) {
            this.encryptedDataTitle = title;
            return this;
        }

        public OutputResultsBuilder setEncryptedDataList(ArrayList<String> encryptedDataList) {
            this.encryptedDataList = encryptedDataList;
            return this;
        }

        public OutputResultsBuilder setDecryptedDataTitle(String title) {
            this.decryptedDataTitle = title;
            return this;
        }

        public OutputResultsBuilder setDecryptedDataList(ArrayList<String> decryptedDataList) {
            this.decryptedDataList = decryptedDataList;
            return this;
        }

        public OutputResultsBuilder setFabricDetailsTitle(String title) {
            this.fabricDetailsTitle = title;
            return this;
        }

        public OutputResultsBuilder setFabrics(ArrayList<Fabric> fabrics) {
            this.fabrics = fabrics;
            return this;
        }

        public OutputResultsBuilder setFabricMapDetailsTitle(String title) {
            this.fabricMapDetailsTitle = title;
            return this;
        }

        public OutputResultsBuilder setFabricMap(Map<String, Fabric> fabricMap) {
            this.fabricMap = fabricMap;
            return this;
        }

        public OutputResultsBuilder setIsValid(boolean isValid) {
            this.isValid = isValid;
            return this;
        }

        public OutputResultsBuilder setArchiveCreationResult(String result) {
            this.archiveCreationResult = result;
            return this;
        }

        public OutputResults build() {
            return new OutputResults(this);
        }
    }

    public void displayEncryptedData() {
        if (encryptedDataTitle != null && encryptedDataList != null) {
            System.out.println("--- " + encryptedDataTitle + " ---");
            for (String encryptedData : encryptedDataList) {
                System.out.println(encryptedData);
            }
        }
    }

    public void displayDecryptedData() {
        if (decryptedDataTitle != null && decryptedDataList != null) {
            System.out.println("--- " + decryptedDataTitle + " ---");
            for (String decryptedData : decryptedDataList) {
                System.out.println(decryptedData);
            }
        }
    }

    public void displayFabricDetails() {
        if (fabricDetailsTitle != null && fabrics != null) {
            System.out.println("--- " + fabricDetailsTitle + " ---");
            for (Fabric fabric : fabrics) {
                System.out.println(fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount());
            }
        }
    }

    public void displayFabricMapDetails() {
        if (fabricMapDetailsTitle != null && fabricMap != null) {
            System.out.println("--- " + fabricMapDetailsTitle + " ---");
            for (Map.Entry<String, Fabric> entry : fabricMap.entrySet()) {
                String fabricType = entry.getKey();
                Fabric fabric = entry.getValue();
                System.out.println("Fabric Details: " + fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount());
            }
        }
    }

    public void displayValidationResults() {
        System.out.println("--- Validation Results ---");
        if (isValid) {
            System.out.println("Data is valid.");
        } else {
            System.out.println("Data is not valid.");
        }
    }

    public void displayArchiveCreationResult() {
        if (archiveCreationResult != null) {
            System.out.println("Archive " + archiveCreationResult + " created successfully.");
        }
    }
}
