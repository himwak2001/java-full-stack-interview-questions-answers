package java_platform;

import java.io.File;

public class JavaPlatform {
    /**
     * <strong>Problem Statement:</strong><br/>
     * A Java application works correctly on Windows but fails on Linux due to
     * differences in file path formats (Windows uses "\" while Linux uses "/").
     * <p>
     * <strong>Task:</strong><br/>
     * Write a Java program that creates file paths in a platform-independent way
     * so that the same code runs successfully on both Windows and Linux.
     * Also explain how Java maintains platform independence in this scenario.
     * <p>
     * <strong>Solution Approach:</strong><br/>
     * 1. Use File.separator instead of hardcoding "\" or "/" in the path.<br/>
     * 2. Build the file path dynamically so it works across different operating systems.<br/>
     * 3. Use the File class to create and access files in a platform-independent way.<br/>
     * 4. Java ensures platform independence through JVM and OS-independent standard libraries.<br/>
     */
    public void crossPlatformFilePath() {
        // Get the system-independent file separator
        String separator = File.separator;

        // Create the file path using separator instead of \ or /
        String path = "home" + separator + "student" + separator + "data.txt";

        File file = new File(path);
        System.out.println("Generated File Path: " + file.getPath());
        if (file.exists()) {
            System.out.println("File found!");
        } else {
            System.out.println("File not found (this is expected dummy output)");
        }
    }
}
