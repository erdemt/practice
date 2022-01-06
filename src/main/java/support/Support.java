package support;

import java.io.*;

public class Support {
    private static final String RESOURCE_PATH = "src/main/resources";
    private static File RESOURCE_DIR = new File(RESOURCE_PATH);

    public static InputStreamReader getIs(String name) {
        try {
            return new InputStreamReader(new FileInputStream(RESOURCE_DIR + "/" + name + "_input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileWriter getOs(String name) {
        try {
            return new FileWriter(RESOURCE_DIR + "/" + name + "_output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStreamReader getIs() {
        return getIs(getFileName());
    }

    public static FileWriter getOs() {
        return getOs(getFileName());
    }

    public static void createInOutFileIfNotExist() {
        File input = new File(RESOURCE_PATH + "/" + getFileName() + "_input.txt");
        File output = new File(RESOURCE_PATH + "/" + getFileName() + "_output.txt");
        try {
            if (!input.exists()) {
                input.createNewFile();
            }

            if (!output.exists()) {
                output.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getFileName() {
        return  Thread.currentThread().getStackTrace()[3].getClassName();
    }

}
