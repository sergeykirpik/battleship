package battleship;

import java.util.Scanner;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    private final Player[] players = new Player[] {
        new Player("Player 1"),
        new Player("Player 2")
    };

    private int turn;

    public void start() {
        turn = 0;

        manualArrangeFleet();
//        autoArrangeFleet();

        System.out.println("\nThe game starts!");

        while (true) {
            drawPvP();
            System.out.printf("%s, it's your turn:\n\n", player1().name);
            makeMove();
            if (isGameOver()) {
                break;
            }
            nextTurn();
        }
    }

    private void manualArrangeFleet() {
        arrangeFleet();
        nextTurn();
        arrangeFleet();
        nextTurn();
    }

    private void drawPvP() {
        System.out.println();
        player2().field.draw();
        drawSeparator();
        player1().field.drawWithoutFogOfWar();
        System.out.println();
    }

    private void drawSeparator() {
        for (int i = 0; i < 21; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void makeMove() {
        String coordinateStr = readCoordinate();
        Coordinate coordinate = Coordinate.parse(coordinateStr);

        String shotResult = "";
        switch (player2().acceptMove(coordinate)) {
            case SANK_LAST:
                shotResult = "You sank the last ship. You won. Congratulations!";
                break;
            case SANK:
                shotResult = "You sank a ship!";
                break;
            case HIT:
                shotResult = "You hit a ship!";
                break;
            case MISS:
                shotResult = "You missed!";
                break;
            default:
                break;
        }
        System.out.printf("\n%s\n", shotResult);
    }

    private boolean isGameOver() {
        return player1().isSankLast() || player2().isSankLast();
    }

    private void autoArrangeFleet() {
        String[] ranges = {
            "F3 F7",
            "A1 D1",
            "J10 J8",
            "B9 D9",
            "I2 J2"
        };

        for (int i = 0; i < ranges.length; i++) {
            player1().placeShip(i, Range.parse(ranges[i]));
            player2().placeShip(i, Range.parse(ranges[i]));
        }
    }

    private void arrangeFleet() {
        System.out.printf("\n%s, place your ships on the game field\n\n", player1().name);
        player1().field.drawWithoutFogOfWar();
        for (Ship ship: player1().fleet) {
            Range location = readShipLocation(ship);
            player1().placeShip(ship, location);
            System.out.println();
            player1().field.drawWithoutFogOfWar();
        }
    }

    private Range readShipLocation(Ship ship) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", ship.name, ship.cells);
        while (true) {
            String input = scanner.nextLine();
            if (!Range.isValid(input)) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
                continue;
            }
            Range shipLocation = Range.parse(input);

            boolean invalidLocation =
            shipLocation.start.row != shipLocation.end.row &&
            shipLocation.start.col != shipLocation.end.col;
            if (invalidLocation) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
                continue;
            }

            boolean invalidHorizontalSize =
            shipLocation.start.row == shipLocation.end.row &&
            shipLocation.end.col - shipLocation.start.col + 1 != ship.cells;

            boolean invalidVerticalSize =
            shipLocation.start.col == shipLocation.end.col &&
            shipLocation.end.row - shipLocation.start.row + 1 != ship.cells;

            if (invalidHorizontalSize || invalidVerticalSize) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", ship.name);
                continue;
            }

            if (!player1().field.isLocationFree(shipLocation)) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
                continue;
            }

            return shipLocation;
        }
    }

    private String readCoordinate() {
        while (true) {
            String input = scanner.nextLine();
            if (!Coordinate.isValid(input)) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            return input;
        }
    }

    private Player player1() {
        return players[turn % 2];
    }

    private Player player2() {
        return players[(turn + 1) % 2];
    }

    private void nextTurn() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        clearScreen();
        turn++;
    }

    public static void clearScreen() {
        for (int i = 0; i < 500; i++) {
            System.out.println();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}


