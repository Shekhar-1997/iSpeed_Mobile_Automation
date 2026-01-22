package tests;

import java.io.*;
import java.nio.file.*;

public class CsvSplitter {
    private static final int CHUNK_SIZE = 5000;

    public static void main(String[] args) {
        String inputCsv = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\textExcelout_updated.csv";
        String outputFolder = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\5k";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputCsv))) {
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Empty file.");
                return;
            }

            int fileCount = 1;
            int recordCount = 0;
            BufferedWriter writer = null;

            String line;
            while ((line = reader.readLine()) != null) {
                if (recordCount % CHUNK_SIZE == 0) {
                    if (writer != null) {
                        writer.close();
                    }
                    String outputFilePath = Paths.get(outputFolder, "chunk_" + fileCount + ".csv").toString();
                    writer = Files.newBufferedWriter(Paths.get(outputFilePath));
                    writer.write(header);
                    writer.newLine();
                    fileCount++;
                }

                writer.write(line);
                writer.newLine();
                recordCount++;
            }

            if (writer != null) {
                writer.close();
            }

            System.out.println("Split complete. Total files created: " + (fileCount - 1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
