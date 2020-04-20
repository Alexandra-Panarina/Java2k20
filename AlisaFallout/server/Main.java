package AlisaFallout.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {
        DictParser.parse("rusDict.txt");
        String clientWord = "традиция";

        String dictValues = DictParser.assocMap.get(clientWord);

        //System.out.println(dictValues);

        String[] answers = dictValues.split(",");

        for( String ans: answers) {
            System.out.println(ans);
        }


        /*
        BufferedReader reader;
        Map<String, String> assocMap = new HashMap<String, String>();

        try {
            reader = new BufferedReader(new FileReader("rusDict.txt"));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                if (line.length() != 1) {
                    stringBuilder.append(line);
                }
            }

            String[] verbs = stringBuilder.toString().split("(\\d+\\+\\d+)\\+(\\d+\\+\\d+)");

            // String[] withoutDigits = Arrays.toString(verbs).split(":");

            for (String dictLine : verbs) {
                String[] dictElement = dictLine.split(":");
                assocMap.put(dictElement[0].toLowerCase(), dictElement[1]);
            }

            // Iterator it = assocMap.entrySet().iterator();

            /*while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}