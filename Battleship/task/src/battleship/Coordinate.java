package battleship;

class Coordinate {
    public final int row;
    public final int col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static boolean isValid(String coordinates) {
        try {
            parse(coordinates);
        } catch (CoordinateFormatException e) {
            return false;
        }
        return true;
    }

    public static Coordinate parse(String coordinates) {
        if (coordinates == null || coordinates.isEmpty()) {
            throw new CoordinateFormatException();
        }
        if (coordinates.split("\\s+").length != 1) {
            throw new CoordinateFormatException();
        }

        char rowPart = coordinates.toUpperCase().charAt(0);
        String colPart = coordinates.substring(1);

        if (!colPart.matches("\\d+")) {
            throw new CoordinateFormatException();
        }

        int row = rowPart - 'A';
        int col = Integer.parseInt(colPart) - 1;

        boolean notInRange = row < 0 || row > 9 || col < 0 || col > 9;
        if (notInRange) {
            throw new CoordinateFormatException();
        }

        return new Coordinate(row, col);
    }
}

