import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI extends JFrame {
    private static final String SECRET_KEY = "YourSecretKey123";
    public GUI() {
        super("Fabric Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1));

        JButton displayFabricDetailsButton = new JButton("Display Fabric Details");
        ArrayList<Fabric> txt = ContainerCreator.createFabricListFromTxtFile("in_file.txt");
        displayFabricDetailsButton.addActionListener(e -> {
            StringBuilder outputText = new StringBuilder();

            outputText.append("--- Method 1 ---\n");
            for (Fabric p : txt) {
                outputText.append(p.getType()).append(" ").append(p.getPlace()).append(" ").append(p.getAmount()).append("\n");
            }
            outputText.append("\n");

            outputText.append("--- Method 2 ---\n");
            txt.forEach(n -> outputText.append(n.getType()).append(" ").append(n.getPlace()).append(" ").append(n.getAmount()).append("\n"));
            outputText.append("\n");

            outputText.append("--- Method 3 ---\n");
            Iterator<Fabric> fabricIterator = txt.iterator();
            while (fabricIterator.hasNext()) {
                Fabric t = fabricIterator.next();
                outputText.append(t.getType()).append(" ").append(t.getPlace()).append(" ").append(t.getAmount()).append("\n");
            }

            outputTextArea.setText(outputText.toString());
        });
        buttonPanel.add(displayFabricDetailsButton);


        JButton displayEncryptedDataButton = new JButton("Display Encrypted Data");
        displayEncryptedDataButton.addActionListener(e -> {
            ArrayList<String> encryptedDataList = ContainerCreator.createEncryptedData(txt, SECRET_KEY);
            outputTextArea.setText("Encrypted Data:\n");
            for (String encryptedData : encryptedDataList) {
                outputTextArea.append(encryptedData + "\n");
            }
        });
        buttonPanel.add(displayEncryptedDataButton);


        JButton createZipArchiveButton = new JButton("Create Zip Archive");
        createZipArchiveButton.addActionListener(e -> {
            Archieve.createZipArchive(txt, "house_archive.zip");
            outputTextArea.setText("Zip Archive Created: house_archive.zip");
        });
        buttonPanel.add(createZipArchiveButton);

        JComboBox<String> archiveTypeComboBox = new JComboBox<>(new String[]{"Zip", "Jar"});
        archiveTypeComboBox.setSelectedIndex(0); // Выберите по умолчанию Zip

        JButton createArchiveButton = new JButton("Create Archive");
        createArchiveButton.addActionListener(e -> {
            String selectedOption = (String) archiveTypeComboBox.getSelectedItem();
            String archiveName;

            if (selectedOption.equals("Zip")) {
                archiveName = "house_archive.zip";
                Archieve.createZipArchive(txt, archiveName);
                outputTextArea.setText("Zip Archive Created: " + archiveName);
            } else {
                archiveName = "fabrics.jar";
                Archieve.createJarArchive(txt, archiveName);
                outputTextArea.setText("Jar Archive Created: " + archiveName);
            }
        });
        buttonPanel.add(archiveTypeComboBox);
        buttonPanel.add(createArchiveButton);


        JButton convertZipToRarButton = new JButton("Convert Zip to Rar");
        convertZipToRarButton.addActionListener(e -> {
            Archieve.convertZipToRar("house_archive.zip", "house_archive.rar");
            outputTextArea.setText("Converted Zip to Rar: house_archive.rar");
        });
        buttonPanel.add(convertZipToRarButton);


        JButton updateFabricDetailsButton = new JButton("Update Fabric Details");
        updateFabricDetailsButton.addActionListener(e -> {
            // Создание окна для ввода данных
            JTextField typeField = new JTextField(10);
            JTextField placeField = new JTextField(10);
            JTextField amountField = new JTextField(10);

            JPanel myPanel = new JPanel();
            myPanel.setLayout(new GridLayout(3, 2));
            myPanel.add(new JLabel("Type:"));
            myPanel.add(typeField);
            myPanel.add(new JLabel("Place:"));
            myPanel.add(placeField);
            myPanel.add(new JLabel("Amount:"));
            myPanel.add(amountField);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Fabric Details",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newType = typeField.getText();
                String newPlace = placeField.getText();
                String newAmount = amountField.getText();

                FabricBuilder fabricBuilder = FabricBuilder.getInstance();
                Fabric fabric1 = fabricBuilder.setType(newType).setPlace(newPlace).setAmount(newAmount).build();
                txt.add(fabric1);

                outputTextArea.setText("Updated Fabric Details");
            }
        });
        buttonPanel.add(updateFabricDetailsButton);


        mainPanel.add(buttonPanel, BorderLayout.WEST);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI mainGUI = new GUI();
            mainGUI.setVisible(true);
        });
    }
}
