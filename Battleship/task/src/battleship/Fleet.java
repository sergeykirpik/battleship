package battleship;

import java.util.Arrays;
import java.util.Iterator;

public class Fleet implements Iterable<Ship> {

    private static final int SHIP_COUNT = 5;
    private final Ship[] fleet = new Ship[SHIP_COUNT];

    public Fleet() {
        fleet[0] = new Ship("Aircraft Carrier", 5);
        fleet[1] = new Ship("Battleship", 4);
        fleet[2] = new Ship("Submarine", 3);
        fleet[3] = new Ship("Cruiser", 3);
        fleet[4] = new Ship("Destroyer", 2);
    }

    public Ship shipAtCoordinate(Coordinate coordinate) {
        for (Ship ship: fleet) {
            Range loc = ship.getLocation();
            boolean inRow = loc.start.row <= coordinate.row && coordinate.row <= loc.end.row;
            boolean inCol = loc.start.col <= coordinate.col && coordinate.col <= loc.end.col;
            boolean inRange = inRow && inCol;
            if (inRange) {
                return ship;
            }
        }
        return null;
    }

    @Override
    public Iterator<Ship> iterator() {
        return Arrays.asList(fleet).iterator();
    }

    public Ship get(int index) {
        return fleet[index];
    }
}
