import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Archieve {
    public static void createZipArchive(ArrayList<Fabric> fabrics, String zipFileName) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < fabrics.size(); i++) {
                Fabric fabric = fabrics.get(i);

                // Creating an entry for each Person in the zip file
                ZipEntry entry = new ZipEntry("house" + i + ".txt");
                zos.putNextEntry(entry);

                // Writing Person's data to the zip entry
                String personData = fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount();
                zos.write(personData.getBytes());

                zos.closeEntry();
            }

            zos.close();
            fos.close();
            System.out.println("Data has been archived to " + zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJarArchive(ArrayList<Fabric> fabrics, String jarFileName) {
        try {
            FileOutputStream fos = new FileOutputStream(jarFileName);
            JarOutputStream jos = new JarOutputStream(fos);

            for (int i = 0; i < fabrics.size(); i++) {
                Fabric fabric = fabrics.get(i);

                // Creating an entry for each fabric in the jar file
                JarEntry entry = new JarEntry("fabric" + i + ".txt");
                jos.putNextEntry(entry);

                // Writing fabric's data to the jar entry
                String fabricData = fabric.getType() + " " + fabric.getPlace() + " " + fabric.getAmount();
                jos.write(fabricData.getBytes());

                jos.closeEntry();
            }

            jos.close();
            fos.close();
            System.out.println("Data has been archived to " + jarFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertZipToRar(String zipFilePath, String rarFilePath) {
        try {
            // Command to run WinRAR from the command line to convert ZIP to RAR
            List<String> command = new ArrayList<>();
            command.add("C:\\Program Files\\WinRAR\\WinRAR.exe"); // Replace 'path_to_winrar' with the actual path to WinRAR executable
            command.add("a"); // 'a' command in WinRAR stands for 'add to archive'
            command.add(rarFilePath); // Destination RAR file path
            command.add(zipFilePath); // Source ZIP file path

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Conversion completed successfully.");
            } else {
                System.err.println("Conversion failed. WinRAR command exited with error code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void extractZipArchive(String zipFilePath, String extractDir) {
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String entryName = zipEntry.getName();
                String fileName = extractDir + System.getProperty("file.separator") + entryName;
                FileOutputStream fos = new FileOutputStream(fileName);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("ZIP archive has been extracted to " + extractDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
