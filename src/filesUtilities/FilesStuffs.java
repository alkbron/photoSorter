package filesUtilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FilesStuffs {
    /**
     * Function that copy file to another place (act like 'cp src dir_target/name_target
     *
     * @param src         the absolute path of the file we want to copy
     * @param dir_target  the path were we want to copy the file
     * @param name_target the name of the copy
     */
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

    /**
     * Add to a list all files child of startDir where the extension matches
     *
     * @param startDir  directory to search (recursive btw)
     * @param extension extension we search about
     * @param list      list we will modify
     */
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
     * Add to a list all files child of startDir
     *
     * @param startDir directory to search (recursive btw)
     * @param list     list we will modify
     */
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
}
