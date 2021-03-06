package Game;

import Pieces.Piece;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.stream.Collectors;

public class DrawingCanvas extends JComponent {

    public DrawingCanvas() {
        Board board = BoardSingleton.getBoard();
        this.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Board board = BoardSingleton.getBoard();

        List<Piece> pieces = board.getAllPiecesAlive();

        board.drawBoard(g2d);

        for (Piece piece : pieces) {
            piece.drawIcon(g2d);
            if (piece.isSelected()) {
                for (Location location : piece.possibleMoves().stream().filter(Location::isOnBoard).collect(Collectors.toList())) {
                    board.drawSelectedCell(g2d, location);
                }
            }
        }
    }
}
