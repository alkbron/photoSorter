package testConsoleJava;

import com.sun.source.tree.AnnotatedTypeTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to tests some java features to manipulate files in folders (will be used in the final classes)
 */
public class Tries {

    public static void main(String[] args) {
        //Here is where we do some tests
        String startDir = "E:/DEKSTOP/Projets/photosortTest";

        //We list what is in this folder :

        System.out.println("\n\n\nListing of files recursively : \n");

        printListFilesRecursive(startDir);

        //Then we create 2 directories
        createDirectory(startDir, "css_files");
        createDirectory(startDir, "js_files");

        //Then we select all the js file and move it to /js_file, same for css files
        List<String> list_js = new ArrayList<>();
        List<String> list_css = new ArrayList<>();

        listFilesRecursiveByExtension(startDir,"js",list_js);
        listFilesRecursiveByExtension(startDir,"css",list_css);

        for (String item_js : list_js){
            copyFile(item_js,startDir+"/js_files",new File(item_js).getName());
        }


        for (String item_css : list_css){
            copyFile(item_css,startDir+"/css_files",new File(item_css).getName());
        }
        System.out.println("Listing of file dummy : \n");
        //array in which we will store the names of files and directories

        printDummyListFiles(startDir);




        System.out.println("\n\n\nListing of JS files recursively  : \n");

        printListFilesRecursiveByExtension(startDir, "map");

        System.out.println("\n\n\nListing of JS files recursively  : \n");

        printListFilesRecursiveByExtension(startDir, "map");
    }

    /**
     * Function that print a list of content of a directory, works like a 'ls' command in linux
     *
     * @param startDir directory we want to explore
     */

    public static void printDummyListFiles(String startDir) {
        String[] list;

        File f = new File(startDir);

        list = f.list();

        for (String item : list) {
            System.out.println(item);
        }
    }


    /**
     * return an list of files/directory present in the startDir
     *
     * @param startDir target directory
     * @return the list
     */
    public static List<String> dummyListFiles(String startDir) {
        String[] array;
        List<String> list = new ArrayList<>();

        File f = new File(startDir);

        array = f.list();

        for (String item : array) {
            list.add(item);
        }

        return list;
    }

    /**
     * Function that print RECURSIVELY a list of content of a directory, works like a 'ls -r' command
     *
     * @param startDir directory we want to explore
     */
    public static void printListFilesRecursive(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    System.out.println("-- " + file.getName());
                    printListFilesRecursive(file.getAbsolutePath());
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public static void listFilesRecursive(String startDir, List<String> list) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    listFilesRecursive(file.getAbsolutePath(), list);
                } else {
                    list.add(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Function that list RECURSIVELY and SELECT BY EXTENSION content of a directory, works like a 'ls -r *.extension' command
     *
     * @param startDir  starting directory we want to explore
     * @param extension extension we want
     */

    public static void printListFilesRecursiveByExtension(String startDir, String extension) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    System.out.println("-- " + file.getName());
                    printListFilesRecursiveByExtension(file.getAbsolutePath(), extension);
                } else {
                    if (file.getName().endsWith(extension)) System.out.println(file.getName());
                }
            }
        }
    }

    public static void listFilesRecursiveByExtension(String startDir, String extension, List<String> list) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                //Check if the file is a directory
                if (file.isDirectory()) {
                    listFilesRecursiveByExtension(file.getAbsolutePath(), extension, list);
                } else {
                    if (file.getName().endsWith(extension)) list.add(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Create a directory in a folder (act like mkdir)
     *
     * @param srcDir  path where we want to create the directory
     * @param nameDir name of the directory
     */
    public static void createDirectory(String srcDir, String nameDir) {
        try {
            Path path = Paths.get(srcDir + "/" + nameDir);

            Files.createDirectories(path);
            System.out.println("Directory is created");
        } catch (IOException e) {
            System.err.println("ERROR CREATION! " + e.getMessage());
        }

    }


    public static void copyFile(String src, String dir_target, String name_target) {
        InputStream is = null;
        OutputStream os = null;
        File src_file = new File(src);
        File target_file = new File(dir_target + "/" + name_target);


        try {
            is = new FileInputStream(src_file);
            os = new FileOutputStream(target_file);

            //Buffer size 1k
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("ERROR COPY FILE CAUSED BY " + e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
