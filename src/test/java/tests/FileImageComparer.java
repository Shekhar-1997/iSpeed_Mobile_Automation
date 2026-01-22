package tests;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import javax.imageio.ImageIO;

public class FileImageComparer {
    
    @Test
    public static void CompareSource() {
        // Define separate folder paths for text and image files
        String textFolderA = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\page_sources";
        String textFolderB = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\DB_data";
        String imageFolderA = "C:/Users/YourName/Documents/ImageFolderA";
        String imageFolderB = "C:/Users/YourName/Documents/ImageFolderB";
        // Separate difference folders for text and images
        String diffTextFolder = "C:/Users/YourName/Documents/difference_text_files";
        String diffImageFolder = "C:/Users/YourName/Documents/difference_image_files";

        // Create the difference folders if they don't exist
        new File(diffTextFolder).mkdirs();
        new File(diffImageFolder).mkdirs();

        // Compare Text Files
        compareFiles(textFolderA, textFolderB, diffTextFolder, "txt");

        // Compare PNG Images
        compareFiles(imageFolderA, imageFolderB, diffImageFolder, "png");
    }

    // **Method to compare text or image files in two folders**
    private static void compareFiles(String folderA, String folderB, String diffFolder, String fileType) {
        File dirA = new File(folderA);
        File dirB = new File(folderB);
        File[] filesA = dirA.listFiles((d, name) -> name.toLowerCase().endsWith("." + fileType));

        if (filesA == null) {
            System.out.println("Folder A is empty or doesn't exist for " + fileType + " files.");
            return;
        }

        for (File fileA : filesA) {
            File fileB = new File(folderB + "/" + fileA.getName());

            if (fileB.exists()) {
                if (fileType.equals("txt")) {
                    // Compare text files
                    if (compareTextFiles(fileA, fileB)) {
                        System.out.println(fileA.getName() + " is IDENTICAL.");
                    } else {
                        System.out.println(fileA.getName() + " is DIFFERENT. Copying to '" + diffFolder + "'...");
                        copyFile(fileA, new File(diffFolder + "/" + fileA.getName()));
                        copyFile(fileB, new File(diffFolder + "/B_" + fileB.getName()));
                    }
                } else if (fileType.equals("png")) {
                    // Compare PNG images
                    if (compareImages(fileA, fileB)) {
                        System.out.println(fileA.getName() + " is IDENTICAL.");
                    } else {
                        System.out.println(fileA.getName() + " is DIFFERENT. Copying to '" + diffFolder + "'...");
                        copyFile(fileA, new File(diffFolder + "/" + fileA.getName()));
                        copyFile(fileB, new File(diffFolder + "/B_" + fileB.getName()));
                    }
                }
            } else {
                System.out.println(fileA.getName() + " is UNIQUE in Folder A. Copying to '" + diffFolder + "'...");
                copyFile(fileA, new File(diffFolder + "/" + fileA.getName()));
            }
        }

        // Check for unique files in Folder B
        File[] filesB = dirB.listFiles((d, name) -> name.toLowerCase().endsWith("." + fileType));
        if (filesB != null) {
            for (File fileB : filesB) {
                File fileA = new File(folderA + "/" + fileB.getName());

                if (!fileA.exists()) {
                    System.out.println(fileB.getName() + " is UNIQUE in Folder B. Copying to '" + diffFolder + "'...");
                    copyFile(fileB, new File(diffFolder + "/" + fileB.getName()));
                }
            }
        }
    }

    // **Method to compare two text files line by line**
    private static boolean compareTextFiles(File file1, File file2) {
        try {
            List<String> content1 = Files.readAllLines(file1.toPath());
            List<String> content2 = Files.readAllLines(file2.toPath());
            return content1.equals(content2); // Returns true if files are identical
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // **Method to compare two PNG images pixel by pixel**
    private static boolean compareImages(File image1, File image2) {
        try {
            BufferedImage imgA = ImageIO.read(image1);
            BufferedImage imgB = ImageIO.read(image2);

            if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
                return false; // Images have different dimensions
            }

            for (int x = 0; x < imgA.getWidth(); x++) {
                for (int y = 0; y < imgA.getHeight(); y++) {
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                        return false; // Found different pixels
                    }
                }
            }
            return true; // Images are identical
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // **Method to copy files (text or images)**
    private static void copyFile(File source, File destination) {
        try {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}