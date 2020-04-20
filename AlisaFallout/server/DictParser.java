package AlisaFallout.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DictParser {
    public static Map<String, String> assocMap = new HashMap<String, String>();

    public static void parse(String file) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                if (line.length() != 1) {
                    stringBuilder.append(line);
                }
            }

            String[] verbs = stringBuilder.toString().split("(\\d+\\+\\d+)\\+(\\d+\\+\\d+)");

            for (String dictLine : verbs) {
                String[] dictElement = dictLine.split(":");
                assocMap.put(dictElement[0].toLowerCase(), dictElement[1].replaceAll("[\\d\\{\\}]", "").replaceAll(";", ","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}