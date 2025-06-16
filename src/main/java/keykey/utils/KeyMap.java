package keykey.utils;

import java.util.HashMap;

public class KeyMap {
    HashMap<String, String> keys;
    public KeyMap() {
        keys = new HashMap<>();
        keys.put("DIGIT1", "1");
        keys.put("DIGIT2", "2");
        keys.put("DIGIT3", "3");
        keys.put("DIGIT4", "4");
        keys.put("DIGIT5", "5");
        keys.put("DIGIT6", "6");
        keys.put("DIGIT7", "7");
        keys.put("DIGIT8", "8");
        keys.put("DIGIT9", "9");
        keys.put("DIGIT0", "0");
    }

    public String getKey(String key) {
        if(keys.containsKey(key)) {
            return keys.get(key);
        } return key;
    }
}
