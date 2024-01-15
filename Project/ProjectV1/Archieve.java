import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.JarEntry;
import java.util.zip.ZipInputStream;
import java.util.jar.JarInputStream;

public class Archieve {
    public static void createZipArchive(ArrayList<Fabric> fabrics, String zipFileName) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < fabrics.size(); i++) {
                Fabric fabric = fabrics.get(i);

                ZipEntry entry = new ZipEntry("house" + i + ".txt");
                zos.putNextEntry(entry);

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

                JarEntry entry = new JarEntry("fabric" + i + ".txt");
                jos.putNextEntry(entry);

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
            List<String> command = new ArrayList<>();
            command.add("C:\\Program Files\\WinRAR\\WinRAR.exe");
            command.add("a");
            command.add(rarFilePath); 
            command.add(zipFilePath);

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

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
    public static ArrayList<String> extractFromZip(String zipFilePath) {
        try {
            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);

            ZipEntry entry;
            int entryCount = 0;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                FileOutputStream fos = new FileOutputStream("extracted_" + entryName);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = zis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
                zis.closeEntry();
                entryCount++;
            }

            zis.close();
            fis.close();
            System.out.println("Extracted " + entryCount + " entries from " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> extractFromJar(String jarFilePath) {
        try {
            FileInputStream fis = new FileInputStream(jarFilePath);
            JarInputStream jis = new JarInputStream(fis);

            JarEntry entry;
            int entryCount = 0;
            while ((entry = jis.getNextJarEntry()) != null) {
                String entryName = entry.getName();
                FileOutputStream fos = new FileOutputStream("extracted_" + entryName);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = jis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
                jis.closeEntry();
                entryCount++;
            }

            jis.close();
            fis.close();
            System.out.println("Extracted " + entryCount + " entries from " + jarFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
