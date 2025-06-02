package keykey.models;

import java.util.Arrays;

public class KeyDesc {
    String keys;
    String description;
    String[] platforms;

    public KeyDesc() {}

    public KeyDesc(String keys, String description, String[] platform) {
        this.keys = keys;
        this.description = description;
        this.platforms = platform;
    }

    public String getKeys() {
        return keys;
    }
    public String getDescription() {
        return description;
    }
    public String[] getPlatforms() {
        return platforms;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String toString() {

        return ("Key combination: ") +
                keys +
                "\n" +
                "Description: " +
                description +
                "\n" +
                "Platforms: " +
                Arrays.toString(platforms) +
                "\n";
    }
}
