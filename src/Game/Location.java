package Game;

import java.util.Objects;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Location pixelToLocation(int x, int y) {
        Board board = BoardSingleton.getBoard();
        return new Location(x * 8 / board.getWidth(), y * 8 /  board.getHeight());
    }

    public int xToPixels() {
        return x * BoardSingleton.getBoard().getWidth() / 8;
    }

    public int yToPixels() {
        return y * BoardSingleton.getBoard().getHeight() / 8;
    }

    public boolean isOnBoard() {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    public boolean isOnLine(int y) {
        return this.y == y;
    }

    public void moveTo(Location newLocation) {
        if (newLocation.x < 0 || newLocation.y < 0 || newLocation.x > 7 || newLocation.y > 7) {
            System.out.println("Your can't play outside the board...");
            return;
        }
        this.x = newLocation.x;
        this.y = newLocation.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
}
