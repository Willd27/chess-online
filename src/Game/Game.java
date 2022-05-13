package Game;

import Multi.ClientEndpoint;
import Pieces.*;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Game.setupBoard();
        UI ui = UISingleton.getUi();

        Player playerB = new Player(ColorPiece.Black);
        Player playerW = new Player(ColorPiece.White);
        playerW.setOpponent(playerB);
        playerB.setOpponent(playerW);
        System.out.println("Start tour");
        playerW.startTurn();
        ui.getContentPane().addMouseListener(playerW);
        ui.getContentPane().addMouseListener(playerB);
        System.out.println("Mouse added");

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
}
