package org.syntax;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * This class demonstrates usage of files, like: loading, reading, etc.
 * If we don't want to state absolute path to a file, we can load a file from resources folder of each class.
 * We specify name of class that we will get resource from, and file name.
 * It will be automatically loaded from "resources" folder (instead of "java" folder)
 * with identical package structure ("org/syntax") than class we are using.
 * To to this, we just call getResource() method of our class (specified either with this, or with ClassName.getClass())
 *
 * Directory structure example:
 * src/main/java/org/syntax/ClassName.java
 * src/main/resources/org/syntax/resource_file.txt
 *
 * Loading examples:
 * this.getResource("resource_file.txt");
 * ClassName.getClass().getResource("resource_file.txt");
 */
public class ReadFile {
    Scanner myReader;
    private static final String FILE_NAME = "resource_file.txt";

    public static void main(String[] args) {
        ReadFile obj = new ReadFile();
        obj.readFile();
    }

    /**
     * Function will read file and print its content to standard output
     */
    public void readFile() {
        //get resource FILE_NAME from "resources" folder with similar structure as our package name
        URL fileUrl = ReadFile.class.getResource(FILE_NAME);
        //URL fileUrl = this.getClass().getResource(FILE_NAME);  //identical way of accessing our resource
        System.out.println("Location of resource is: " + fileUrl.toString());

        //Get file descriptor
        File file = new File(fileUrl.getFile());

        //Try to open Scanner of our file descriptor
        try {
            myReader = new Scanner(file);
        //If Scanner cannot be opened, file probably does not exist, or it is not readable
        } catch (Exception e) {
            System.err.println("Opening Scanner with given file failed: file does not exist or is not readable");
        }

        //Print content of our line
        System.out.println("Printing content of file:");
        while (myReader.hasNextLine()) {
            System.out.println(myReader.nextLine());
        }
    }
}
