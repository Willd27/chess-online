package Game;

public class BoardSingleton {
    private static Board board;

    public static Board getBoard() {
        if (board == null) {
            board = new Board(500, 500);
        }
        return board;
    }
}
