package battleship;

enum Cell {
    
    FOG_OF_WAR('~'),
    SHIP('O'),
    MISS('M'),
    HIT('X');
    
    private final char markOnField;
    
    Cell(char markOnField) {
        this.markOnField = markOnField;
    }

    public char mark() {
        return markOnField;
    }

    @Override
    public String toString() {
        return String.valueOf(markOnField);
    }
}