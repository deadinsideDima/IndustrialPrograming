import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ArchieveTest {

    private ArrayList<Fabric> fabrics;
    private String zipFileName;
    private String jarFileName;
    private String zipFilePath;
    private String jarFilePath;
    private String rarFilePath;

    @BeforeEach
    void setUp() {
        // Initialize test data
        fabrics = new ArrayList<>();
        fabrics.add(new Fabric("Farmhouse", "Minsk", "100"));
        fabrics.add(new Fabric("Cottege", "Puhovichi", "50"));

        // Set up file paths
        zipFileName = "test.zip";
        jarFileName = "test.jar";
        rarFilePath = "test.rar";
        zipFilePath = "extracted_test.zip";
        jarFilePath = "extracted_test.jar";
    }

    @AfterEach
    void tearDown() {
        // Delete test files after each test
        new File(zipFileName).delete();
        new File(jarFileName).delete();
        new File(rarFilePath).delete();
        new File(zipFilePath).delete();
        new File(jarFilePath).delete();
    }

    @Test
    void testCreateZipArchive() {
        Archieve.createZipArchive(fabrics, zipFileName);
        File file = new File(zipFileName);
        assertTrue(file.exists());
    }

    @Test
    void testCreateJarArchive() {
        Archieve.createJarArchive(fabrics, jarFileName);
        File file = new File(jarFileName);
        assertTrue(file.exists());
    }

    @Test
    void testConvertZipToRar() {
        Archieve.createZipArchive(fabrics, zipFileName);
        Archieve.convertZipToRar(zipFileName, rarFilePath);
        File file = new File(rarFilePath);
        assertTrue(file.exists());
    }

    @Test
    void testExtractFromZip() {
        Archieve.createZipArchive(fabrics, zipFileName);
        Archieve.extractFromZip(zipFileName);
        File file = new File("extracted_house0.txt");
        assertTrue(file.exists());
    }

    @Test
    void testExtractFromJar() {
        Archieve.createJarArchive(fabrics, jarFileName);
        Archieve.extractFromJar(jarFileName);
        File file = new File("extracted_fabric0.txt");
        assertTrue(file.exists());
    }
}
