import java.io.*;

public class Loading {

    public static StringBuilder read(String fileName) {

        BufferedReader bfr = null;
        FileReader fr = null;

        try {
            fr = new FileReader(fileName);
            bfr = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println("Bledny plik");
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        String s;

        try {
            while ((s = bfr.readLine()) != null) {
                sb.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb;
    }

    public static void write(String fileName, String data) throws IOException {

        File file = new File(fileName);
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(data);
        output.close();
    }

}