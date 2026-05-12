package UTILS;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/*
 * Reads the JSON as a tree, splits the key path (for example "user.address.city")
 * into parts, starts from the root node, and moves through each child node one
 * level at a time using path(key). When the final node is reached, its value is
 * returned as text using asText().
 *
 * readTree()   -> loads the whole JSON into memory as a tree
 * split("\\.") -> splits the key path into individual keys
 * path(key)    -> moves one level deeper into the JSON
 * asText()     -> converts the final node into a String
 */

public class Utils {

    public JsonNode readJson(String file, String keyPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode nodes = mapper.readTree(new File(file));

        if(keyPath != null && !keyPath.isBlank()){
            String[] keys = keyPath.split("\\.");

            for (String key : keys){
                nodes = nodes.path(key);
            }
        }

        return nodes;
    }

    public String getUrl(String str) throws IOException {
        JsonNode node = readJson("DATA/urls.json", "ORANGE_HRM");

        if(str.equalsIgnoreCase("base")){
            return node.path("BASE_URL").asText();
        }else{
            node = node.path("PAGES");
            String page = str.toUpperCase();
            return node.path(page).asText();
        }
    }

    public String getLocatorString(String page, String element) throws IOException {
        JsonNode node = readJson("DATA/locators.json", "");
        page = page.toUpperCase();
        element = element.toUpperCase();

        return node.path(page).path(element).asText();
    }

    public JsonNode getData(String key) throws IOException {
        return readJson("DATA/data.json", key.toUpperCase());
    }
}
