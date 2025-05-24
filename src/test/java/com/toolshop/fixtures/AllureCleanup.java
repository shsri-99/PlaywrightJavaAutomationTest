package com.toolshop.fixtures;

import java.io.File;

public class AllureCleanup {

    public static void cleanAllureResultsFolder(String resultsPath) {
        File folder = new File(resultsPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println("Deleted: " + file.getName());
                    }
                }
            }
        }
    }
}