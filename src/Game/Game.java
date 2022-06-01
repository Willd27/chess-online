package Game;

import Multi.ClientEndpoint;
import Pieces.*;

public class Game {
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
        Player playerW;
        Player playerB;
        System.out.println(BoardSingleton.getBoard().getAllPiecesAlive());


        if (colorHostPlayer == ColorPiece.Black) {
            playerB = new Player(ColorPiece.Black, true, client);
            playerW = new Player(ColorPiece.White, false, null);
            ui.getContentPane().addMouseListener(playerB);
        } else {
            playerB = new Player(ColorPiece.Black, false, client);
            playerW = new Player(ColorPiece.White, true, client);
            ui.getContentPane().addMouseListener(playerW);
        }

        playerW.setOpponent(playerB);
        playerB.setOpponent(playerW);
        System.out.println("Game starting! You control the " + colorHostPlayer.toString().toLowerCase() + " pieces");

        System.out.println("Start tour");
        playerW.startTurn();

        System.out.println("Mouse added");

    }
}
