package Game;

import Multi.ClientEndpoint;
import Pieces.*;

public class Game {
    static Player player;
    static Player enemy;

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        ClientEndpoint clientEndpoint = new ClientEndpoint();

        clientEndpoint.connectToServer();
    }

    private static void setupBoard() {
        for (int i = 0; i < 8; i++) {
            new Pawn(new Location(i, 1), ColorPiece.White);
            new Pawn(new Location(i, 6), ColorPiece.Black);
        }
        new Rook(new Location(0, 0), ColorPiece.White);
        new Rook(new Location(0, 7), ColorPiece.Black);
        new Rook(new Location(7, 0), ColorPiece.White);
        new Rook(new Location(7, 7), ColorPiece.Black);

        new Bishop(new Location(2, 0), ColorPiece.White);
        new Bishop(new Location(2, 7), ColorPiece.Black);
        new Bishop(new Location(5, 0), ColorPiece.White);
        new Bishop(new Location(5, 7), ColorPiece.Black);

        new Knight(new Location(1, 0), ColorPiece.White);
        new Knight(new Location(1, 7), ColorPiece.Black);
        new Knight(new Location(6, 0), ColorPiece.White);
        new Knight(new Location(6, 7), ColorPiece.Black);

        new Queen(new Location(4, 0), ColorPiece.White);
        new Queen(new Location(4, 7), ColorPiece.Black);
        new King(new Location(3, 0), ColorPiece.White);
        new King(new Location(3, 7), ColorPiece.Black);
    }

    public static void sendDataToServer() {

    }

    public static void retrieveDataFromServer() {

    }

    public static void startGame(ColorPiece colorHostPlayer, ClientEndpoint client) {
        Game.setupBoard();
        UI ui = UISingleton.getUi();
        System.out.println(BoardSingleton.getBoard().getAllPiecesAlive());

        player = new Player(colorHostPlayer, true, client);

        if (colorHostPlayer == ColorPiece.Black) {
            enemy = new Player(ColorPiece.White, false, null);
        } else {
            enemy = new Player(ColorPiece.Black, false, null);
        }

        ui.getContentPane().addMouseListener(player);

        player.setOpponent(enemy);
        enemy.setOpponent(player);
        System.out.println("Game starting! You control the " + colorHostPlayer.toString().toLowerCase() + " pieces");

        System.out.println("Start tour");
        player.startTurn();

        System.out.println("Mouse added");

    }
}
