package battleship;

public class Range {
    public final Coordinate start;
    public final Coordinate end;

    public Range(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public static boolean isValid(String rangeFormat) {
        try {
            parse(rangeFormat);
        } catch (RangeFormatException e) {
            return false;
        }
        return true;
    }

    public static Range parse(String coordinatesPair) {
        if (coordinatesPair == null || coordinatesPair.isEmpty()) {
            throw new CoordinateFormatException();
        }
        String[] parts = coordinatesPair.split("\\s+");
        if (parts.length != 2) {
            throw new RangeFormatException();
        }

        Coordinate start = null;
        Coordinate end = null;
        try {
            start = Coordinate.parse(parts[0]);
            end = Coordinate.parse(parts[1]);
        } catch (CoordinateFormatException e) {
            throw new RangeFormatException();
        }

        int minRow = Math.min(start.row, end.row);
        int minCol = Math.min(start.col, end.col);
        int maxRow = Math.max(start.row, end.row);
        int maxCol = Math.max(start.col, end.col);

        Coordinate normalizedStart = new Coordinate(minRow, minCol);
        Coordinate normalizedEnd = new Coordinate(maxRow, maxCol);

        return new Range(normalizedStart, normalizedEnd);
    }
}
