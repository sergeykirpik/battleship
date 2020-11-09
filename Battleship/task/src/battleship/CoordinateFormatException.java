package battleship;

public class CoordinateFormatException extends RuntimeException {
    public CoordinateFormatException(String message) {
        super(message);
    }

    public CoordinateFormatException() {
        this("Error! Invalid coordinates.");
    }
}
