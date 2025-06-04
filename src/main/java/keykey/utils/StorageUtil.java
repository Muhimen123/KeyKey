package keykey.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import keykey.models.KeyDesc;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StorageUtil {
    String filePath = System.getProperty("user.home") + "/keykey/.keykey.json";
    File file = new File(filePath);
    Path path = Paths.get(filePath);
    File parentDirectory = path.getParent().toFile();
    /**
     * When initializing this util, ensures that the storage file parent directory exist.
     */
    public StorageUtil() throws IOException {
        if (!parentDirectory.exists()) {
            boolean okay = parentDirectory.mkdirs();
            if(!okay) {
                System.err.println("Couldn't create directory: " + parentDirectory);
            }
        }

        if(!file.exists()) {
            boolean okay = file.createNewFile();
            if(okay) {
                JsonObject jsonObject = new JsonObject();
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
                }
            }
        }
    }

    /**
     * Writes the {@link KeyDesc} class to the appropriate application
     * @param application Name of the application where the shortcut belongs
     * @param keyDesc The actual information class which will be written
     * @throws IOException I think it's safe because we are making sure that the file exists when initializing
     */
    public void WriteKeyDesc(String application, KeyDesc keyDesc) throws IOException {
        JsonObject root = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        JsonArray applicationArray = root.has(application) ? root.get(application).getAsJsonArray() : new JsonArray();

        if(!root.has(application)) {
            root.add(application, applicationArray);
        }

        applicationArray.add(keyDesc.toJson());
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(root));
        } catch (Exception e) {
            System.err.println("Couldn't write to file: " + e.getMessage());
        }
    }

    /**
     *
     * @return a List of String that can be further used in {@link keykey.component.Sidebar}
     * @throws FileNotFoundException
     */
    public ArrayList<String> ReadAllApplications() throws FileNotFoundException {
        JsonObject root = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        return new ArrayList<>(root.keySet());
    }

}
