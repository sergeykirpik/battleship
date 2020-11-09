package battleship;

import java.util.Arrays;

public class GameField {
    public static final int FIELD_SIZE = 10;
    private final Cell[] field = new Cell[FIELD_SIZE * FIELD_SIZE];

    public GameField() {
        Arrays.fill(field, Cell.FOG_OF_WAR);
    }

    public void drawWithoutFogOfWar() {
        drawField(false);
    }

    public void draw() {
        drawField(true);
    }

    private void drawField(boolean withForOfWar) {
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        for (int row = 0; row < FIELD_SIZE; row++) {
            System.out.printf("%c", 'A' + row);
            for (int col = 0; col < FIELD_SIZE; col++) {
                Cell cell = cellAt(row, col);
                if (withForOfWar && cell == Cell.SHIP) {
                    cell = Cell.FOG_OF_WAR;
                }
                System.out.printf(" %s", cell);
            }
            System.out.println();
        }
    }

    public int countCellsInRange(Cell cell, Range range) {
        int count = 0;
        for (int row = range.start.row; row <= range.end.row; row++) {
            for (int col = range.start.col; col <= range.end.col; col++) {
                if (cellAt(row, col) == cell) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isLocationFree(Range location) {
        int startRow = Math.max(location.start.row - 1, 0);
        int endRow = Math.min(location.end.row + 1, FIELD_SIZE - 1);
        int startCol = Math.max(location.start.col - 1, 0);
        int endCol = Math.min(location.end.col + 1, FIELD_SIZE - 1);

        Coordinate start = new Coordinate(startRow, startCol);
        Coordinate end = new Coordinate(endRow, endCol);
        Range range = new Range(start, end);
        return countCellsInRange(Cell.SHIP, range) == 0;
    }

    public Cell cellAt(String coordinates) {
        return cellAt(Coordinate.parse(coordinates));
    }

    public int cellIndex(int row, int col) {
        return row * FIELD_SIZE + col;
    }

    public Cell cellAt(int row, int col) {
        return field[cellIndex(row, col)];
    }

    public Cell cellAt(Coordinate coordinate) {
        return cellAt(coordinate.row, coordinate.col);
    }

    public void putAt(Cell cell, int row, int col) {
        field[cellIndex(row, col)] = cell;
    }

    public void putAt(Cell cell, String coordinate) {
        if (Coordinate.isValid(coordinate)) {
            putAt(cell, Coordinate.parse(coordinate));
        }
    }

    public void putAt(Cell cell, Coordinate coordinate) {
        putAt(cell, coordinate.row, coordinate.col);
    }

}
