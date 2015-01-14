import java.io.*;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        try {

            //Przykład kompresji danych Bzipem, zapisuje wynik do pliku o nazwie 'bzipData'

            String suroweDane = Loading.read("data").toString();


            Bzip2.compress(suroweDane, "bzipData" );

            System.out.println("\nPo dekompresji z użyciem Bzip2:");
            //Tutaj dekompresuje dane z pliku 'bzipData' i wyświetlam w terminalu
            System.out.println(Bzip2.decompress("bzipData"));

            //Teraz kompresuje dane algorytmem LZW, nie działa z polskimi znakami
            List<Integer> wynikKompresji = Lzw.compress( suroweDane );

            System.out.println( "\nPo kompresji z użyciem LZW:");
            System.out.println(wynikKompresji);


            System.out.println( "\nPo dekompresji z użyciem LZW:");

            //Teraz dekompresuje dane, które znajdują się w strukturze 'List'
            System.out.println( Lzw.decompress( wynikKompresji ) );


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}