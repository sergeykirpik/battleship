import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        String line = reader.readLine();
        StringBuilder reversed = new StringBuilder(line).reverse();
        System.out.println(reversed);
        reader.close();
    }
}