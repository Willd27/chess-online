package Game;

import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int height;
    private final int width;

    private List<Piece> allPiecesAlive = new ArrayList<>();

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public List<Piece> getAllPiecesAlive() {
        return allPiecesAlive;
    }

    public void updateAllPiecesAlive(String strJSON) {

    }

    public void removePiece(Piece piece) {
        allPiecesAlive.remove(piece);
    }

    public void addPiece(Piece piece) {
        allPiecesAlive.add(piece);
    }

    public boolean isLocationTaken(Location location, ColorPiece color) {
        for (Piece piece : allPiecesAlive) {
            if (location.equals(piece.getLocation()) && color == piece.getColor()) {
                return true;
            }
        }
        return false;
    }

    public Piece getPieceByLocation(Location location) {
        for (Piece piece : allPiecesAlive) {
            if (location.equals(piece.getLocation())) {
                return piece;
            }
        }
        return null;
    }

    public void drawBoard(Graphics2D g2d) {
        int size = width / 8;
        g2d.setColor(Color.DARK_GRAY);
        for (int line = 0; line < 8; line++) {
            for (int col = 0; col < 8; col++) {
                if ((col + line % 2) % 2 == 0) {
                    Rectangle rect = new Rectangle(size * col, size * line, size, size);
                    g2d.fill(rect);
                }
            }
        }
    }

    public void drawSelectedCell(Graphics2D g2d, Location location) {
        if (location != null) {
            if (getPieceByLocation(location) != null) {
                g2d.setColor(new Color(255, 21, 21, 80));
                g2d.fillRect(location.xToPixels(), location.yToPixels(), width / 8, height / 8);
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(new Color(227, 217, 36, 50));
                g2d.fillRect(location.xToPixels(), location.yToPixels(), width / 8, height / 8);
                g2d.setStroke(new BasicStroke(5));
                g2d.setColor(Color.ORANGE);
            }
            g2d.drawRect(location.xToPixels(), location.yToPixels(), width / 8, height / 8);
        }
    }


    public String boardToJSON() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < allPiecesAlive.size(); i++) {
            str.append("\"").append(i).append("\":");
            str.append(allPiecesAlive.get(i).toJSON());
            if (i != allPiecesAlive.size() - 1)
                str.append(", ");
        }
        str.append("}");
        return str.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
