import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        int c = inputStream.read();
        while (c != -1 && c != '\n') {
            System.out.print(c);
            c = inputStream.read();
        }
        inputStream.close();
    }
}