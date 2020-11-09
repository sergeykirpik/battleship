package battleship;

public class Player {

    public final String name;
    public final Fleet fleet = new Fleet();
    public final GameField field = new GameField();

    public Player(String name) {
        this.name = name;
    }

    public void placeShip(Ship ship, Range location) {
        ship.setLocation(location);
        int startRow = location.start.row;
        int endRow = location.end.row;
        int startCol = location.start.col;
        int endCol = location.end.col;
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                field.putAt(Cell.SHIP, row, col);
            }
        }
    }

    public void placeShip(int index, Range location) {
        placeShip(fleet.get(index), location);
    }

    public MoveResult acceptMove(Coordinate coordinate) {
        if (field.cellAt(coordinate) == Cell.SHIP) {
            field.putAt(Cell.HIT, coordinate);
            if (isSankLast()) {
                return MoveResult.SANK_LAST;
            }
            if (isSank(coordinate)) {
                return MoveResult.SANK;
            }
            return MoveResult.HIT;
        }
        if (field.cellAt(coordinate) == Cell.FOG_OF_WAR) {
            field.putAt(Cell.MISS, coordinate);
        }
        return MoveResult.MISS;
    }

    public boolean isSankLast() {
        return field.countCellsInRange(Cell.SHIP, Range.parse("A1 J10")) == 0;
    }

    private boolean isSank(Coordinate coordinate) {
        Ship ship = fleet.shipAtCoordinate(coordinate);
        if (ship == null) {
            return false;
        }
        return field.countCellsInRange(Cell.SHIP, ship.getLocation()) == 0;
    }

}
