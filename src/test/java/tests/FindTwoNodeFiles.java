package tests;

import java.io.File;

public class FindTwoNodeFiles {
    public static void main(String[] args) {
        String folderPath = "D:\\ST_QA\\PushDB_Ispeed_Automation\\JmeterPerformanceTesting(LoadTest)\\PostCachePerfromanceTest_20250807\\With-OutCacheNotificationService\\TC_01\\notificationService";

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        String file1AbsolutePath = null;
        String file2AbsolutePath = null;

        if (files != null && files.length > 0) {
            int count = 0;
            for (File file : files) {
                if (file.isFile() && file.getName().contains("node")) {
                    if (count == 0) {
                        file1AbsolutePath = file.getAbsolutePath();
                    } else if (count == 1) {
                        file2AbsolutePath = file.getAbsolutePath();
                        break; // Stop after finding two files
                    }
                    count++;
                }
            }

            // ✅ Print the results
            if (file1AbsolutePath != null) {
                System.out.println("file1AbsolutePath = " + file1AbsolutePath);
            }
            if (file2AbsolutePath != null) {
                System.out.println("file2AbsolutePath = " + file2AbsolutePath);
            }

        } else {
            System.out.println("Folder is empty or does not exist.");
        }
    }
}

