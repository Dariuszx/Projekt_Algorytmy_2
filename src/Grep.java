import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Grep {

    public static void initGrep( byte[] dane, String regex ) {

        String inputText = regex;
        processRegularExpression(dane, inputText);
    }

    private static void processRegularExpression( byte[] dane, String regex) {

        Pattern p = Pattern.compile(regex);
        Matcher m;
        String[] tab = (new String(dane)).split("\n");
        int total = 0;

        for (String value : tab) {

            m = p.matcher(value);

            int count = 0;
            while (m.find()) {
                if (count == 0) {
                    //System.out.println(value);
                }
                count++;
                total++;
            }
        }
        if (total > 0) {
            System.out.println();
            if (total == 1) {
                System.out.println("Znaleziono " + total + " linię w której występuje wzorzec");
            } else {
                System.out.println("Znaleziono " + total + " linii w których występuje wzorzec");
            }
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Nie znaleziono wzorca w tekście");
            System.out.println();
        }

    }

}