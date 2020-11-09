import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String line = reader.readLine().trim();
        int wordsCount = 0;
        if (!line.isEmpty()) {
            wordsCount = line.split("\\s+").length;
        }
        System.out.println(wordsCount);
        reader.close();
    }
}