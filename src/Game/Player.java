package Game;

import Multi.ClientEndpoint;
import Pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends MouseAdapter {
    private ColorPiece color;
    private List<Piece> piecesAlive;
    private Piece selectedPiece;
    private boolean isPlaying;
    private Player opponent;
    private boolean isHostPlayer;
    private ClientEndpoint client;

    public Player(ColorPiece color, boolean isHostPlayer, ClientEndpoint client) {
        this.color = color;
        piecesAlive = new ArrayList<>();
        isPlaying = false;
        opponent = null;
        this.isHostPlayer = isHostPlayer;
        this.client = client;

    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void startTurn() {
        isPlaying = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        if (isPlaying) {
            System.out.println("BEFORE CLICK : " + selectedPiece);

            if (selectedPiece == null) {
                selectAPiece(e);
            } else {
                Location location = Location.pixelToLocation(e.getX(), e.getY());
                Piece piece = BoardSingleton.getBoard().getPieceByLocation(location);
                if (piece == selectedPiece) {
                    selectedPiece = null;
                    piece.setSelected(false);
                } else {
                    selectedPiece.movePieceTo(location);
                    client.sendNewLocations();
                    selectedPiece.setSelected(false);
                    selectedPiece = null;
                    isPlaying = false;
                    opponent.startTurn();
                }
            }
            UISingleton.getUi().updateUI();
            System.out.println("AFTER CLICK : " + selectedPiece + " \n---------------");
        }
    }

    private void selectAPiece(MouseEvent e) {
        Piece piece = BoardSingleton.getBoard().getPieceByLocation(Location.pixelToLocation(e.getX(), e.getY()));

        if (piece != null && piece.getColor() == color) {
            selectedPiece = piece;
            piece.setSelected(true);
        }
    }
}
