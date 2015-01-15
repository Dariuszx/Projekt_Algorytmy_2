import org.at4j.comp.bzip2.BZip2InputStream;
import org.at4j.comp.bzip2.BZip2OutputStream;
import org.entityfs.support.io.StreamUtil;

import java.io.*;


/*
 * Dzięki tej klasie możemy kompresować i dekompresować dane.
 * Metody klasy są statyczne
 */
public class Bzip2 {

    /*
     * Pierwszym argumentem są dane, które ZOSTANĄ skompresowane, drugim argumentem zaś nazwa pliku
     * do którego zapiszę skompresowane dane
     */
    public static void compress(String inputData, String fileName) throws FileNotFoundException, IOException {

        OutputStream os = new FileOutputStream(new File(fileName+".compressed"));
        try {
            OutputStream bzos = new BZip2OutputStream(os);
            try {
                bzos.write(inputData.getBytes());
            } finally {
                bzos.close();
            }
        } finally {
            os.close();
        }
    }


    /*
     * Pierwszy argument to nazwa pliku, który zawiera SKOMPRESOWANE dane
     * Funkcja ZWRACA dane PO skompresowaniu w postaci obiektu typu STRING
     */
    public static String decompress(String fileName) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(new File(fileName));
        String result;

        try {
            InputStream bzis = new BZip2InputStream(is);
            try {
                result = new String(StreamUtil.readStreamFully(bzis, 32));
            } finally {
                bzis.close();
            }
        } finally {
            is.close();
        }
        return result;
    }
}
