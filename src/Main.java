import java.io.*;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException {

        List<Integer> compressedDataFilenames = Lzw.compress( Loading.read("data").toString() );

        String help = "Wyszukiwarka logów systemowych:" + "poprawne wywołanie programu powinno wyglądać następująco - " + "-[typ kompresji] [nazwa skompresowanego pliku] [wzorzec do wyszukania]\n";


        String compressedDataFilename = null;

        if (args.length < 3) {

            System.err.println(help);

        } else {

            String alghoritm = "";


            if ( args[0].equals("-bzip2") && args[1].length() > 0 ) {
                    alghoritm = "bzip2";
                    compressedDataFilename = args[1];
            } else if (args[0].equals("-lzw") && args[1].length() > 0 ) {
                    alghoritm = "lzw";
                    compressedDataFilename = args[1];
            } else {
                System.err.println("Nieoczekiwany błąd\n" + help);
                System.exit(1);
            }

            if( args[2].length() == 0 ) System.exit(1);


            String decompressed = null;

            if( alghoritm == "bzip2" ) {
                //Bzip2.compress(Loading.read("data").toString(), "data");
                decompressed = Bzip2.decompress(compressedDataFilename);
            } else {
                decompressed = Lzw.decompress( compressedDataFilenames );
            }

            String pattern = args[2];

            byte[] decompressedData = decompressed.getBytes();

            Grep.initGrep(decompressedData, pattern);

        }
    }
}