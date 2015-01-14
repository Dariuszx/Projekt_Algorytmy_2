import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lzw {

    /*
     * Jako argument podajemy dane nieskompresowane
     */
    public static List<Integer> compress( String uncompressedData ) {

        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char) i, i);

        String w = "";
        List<Integer> result = new ArrayList<Integer>();

        for (char c : uncompressedData.toCharArray()) {

            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));

                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        if (!w.equals("")) result.add(dictionary.get(w));

        return result;
    }


    /*
     * Metoda zwraca ZDEKOMPRESOWANE dane, a przyjmuje to co zwraca metoda COMPRESS czyli
     * skompresowane dane
     */
    public static String decompress( List<Integer> compressedData ) {

        int dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<Integer, String>();

        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char) i);

        String w = "" + (char) (int) compressedData.remove(0);
        StringBuffer result = new StringBuffer(w);

        for (int k : compressedData) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Źle skompresowane k: " + k);

            result.append(entry);

            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        return result.toString();
    }
}
