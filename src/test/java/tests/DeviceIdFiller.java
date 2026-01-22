package tests;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DeviceIdFiller {
    private static final int DEFAULT_STRING_LENGTH = 64;
    private static final Set<String> generatedStrings = new HashSet<>();

    public static void main(String[] args) {
        String inputCsv = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\textExcelout.csv";
        String outputCsv = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\textExcelout_updated.csv";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputCsv));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputCsv))) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("CSV is empty.");
                return;
            }

            String[] headers = headerLine.split(",");
            int platformIndex = -1;
            int deviceIdIndex = -1;

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equalsIgnoreCase("platform_kbn")) {
                    platformIndex = i;
                } else if (headers[i].equalsIgnoreCase("device_id")) {
                    deviceIdIndex = i;
                }
            }

            if (platformIndex == -1 || deviceIdIndex == -1) {
                System.out.println("Required columns not found.");
                return;
            }

            writer.write(String.join(",", headers));
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",", -1);

                if (columns.length <= Math.max(platformIndex, deviceIdIndex)) {
                    writer.write(line);
                    writer.newLine();
                    continue;
                }

                String platform = columns[platformIndex];
                int lengthToGenerate = DEFAULT_STRING_LENGTH;

                if ("1".equals(platform)) {
                    lengthToGenerate = 64;
                } else if ("2".equals(platform)) {
                    lengthToGenerate = 164;
                }

                columns[deviceIdIndex] = generateUniqueRandomString(lengthToGenerate);
                writer.write(String.join(",", columns));
                writer.newLine();
            }

            System.out.println("Updated CSV saved to " + outputCsv);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateUniqueRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String result;

        do {
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            result = sb.toString();
        } while (!generatedStrings.add(result)); // ensure uniqueness

        return result;
    }
}
