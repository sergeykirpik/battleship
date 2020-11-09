package battleship;

public class Ship {

    public final String name;
    public final int cells;

    private Range location;

    public Ship(String name, int cells) {
        this.name = name;
        this.cells = cells;
    }

    public Range getLocation() {
        return location;
    }

    public void setLocation(Range location) {
        this.location = location;
    }
}
