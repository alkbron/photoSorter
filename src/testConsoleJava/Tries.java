package testConsoleJava;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to tests some java features to manipulate files in folders (will be used in the final classes)
 */
public class Tries {

    public static void main(String[] args) {
        String startDir = "E:/DEKSTOP/Projets/photosortTest";
        System.out.println("Listing of file dummy : \n");
        //array in which we will store the names of files and directories

        listFiles(startDir);

        System.out.println("\n\n\nListing of files recursively : \n");

        listFilesRecursive(startDir);

        System.out.println("\n\n\nListing of JS files recursively  : \n");

        listFilesRecursiveByExtension(startDir,"map");

        createDirectory(startDir,"TestCreation");

        System.out.println("\n\n\nListing of JS files recursively  : \n");

        listFilesRecursiveByExtension(startDir,"map");
    }

    /**
     * Function that list content of a directory, works like a 'ls' command in linux
     *
     * @param startDir directory we want to explore
     */

    public static void listFiles(String startDir) {
        String[] list;

        File f = new File(startDir);

        list = f.list();

        for (String item : list) {
            System.out.println(item);
        }
    }

    /**
     * Function that list RECURSIVELY content of a directory, works like a 'ls -r' command
     *
     * @param startDir directory we want to explore
     */
    public static void listFilesRecursive(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    System.out.println("-- " + file.getName());
                    listFilesRecursive(file.getAbsolutePath());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }

    /**
     * Function that list RECURSIVELY and SELECT BY EXTENSION content of a directory, works like a 'ls -r *.extension' command
     * @param startDir starting directory we want to explore
     * @param extension extension we want
     */

    public static void listFilesRecursiveByExtension(String startDir,String extension){
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    System.out.println("-- " + file.getName());
                    listFilesRecursiveByExtension(file.getAbsolutePath(),extension);
                } else {
                    if(file.getName().endsWith(extension)) System.out.println(file.getName());
                }
            }
        }
    }

    public static void createDirectory(String srcDir, String nameDir){
        try {
            Path path = Paths.get(srcDir + "/" + nameDir);

            Files.createDirectories(path);
            System.out.println("Directory is created");
        }catch (IOException e){
            System.err.println("ERROR CREATION! " + e.getMessage());
        }

    }

}
