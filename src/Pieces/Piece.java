package Pieces;

import Game.BoardSingleton;
import Game.ColorPiece;
import Game.Location;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public abstract class Piece {
    protected Image icon;
    protected ColorPiece color;
    protected Location location;
    protected boolean isSelected;
    protected int id;

    public Piece(Location location, ColorPiece color, String iconPath) {
        URL url = getClass().getResource(iconPath);
        this.location = location;
        this.color = color;
        this.isSelected = false;
        assert url != null;
        this.icon = new ImageIcon(url).getImage();
        BoardSingleton.getBoard().addPiece(this);
    }

    public List<Location> addMoves(List<Location> possibleMoves, Location move) {
        if (!BoardSingleton.getBoard().isLocationTaken(move, color)) {
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public ColorPiece getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public void movePieceTo(Location location) {
        if (isMoveValid(location)) {
            kill(BoardSingleton.getBoard().getPieceByLocation(location));
            this.location.moveTo(location);
            System.out.println("The piece is now in : " + location);
            System.out.println(this.toJSON());
        } else
            System.out.println("Can't move the piece to : " + location);
    }

    public void kill(Piece piece) {
        BoardSingleton.getBoard().removePiece(piece);
    }

    public void movePieceTo(int x, int y) {
        movePieceTo(new Location(x, y));
    }

    public boolean isMoveValid(Location location) {
        List<Location> possibleMoves = possibleMoves();
        if (possibleMoves != null) {
            for (Location loc : possibleMoves()) {
                if (loc.equals(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawIcon(Graphics2D g2d) {
        g2d.drawImage(icon, location.xToPixels(), location.yToPixels(), null);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "icon=" + icon +
                ", color=" + color +
                ", location=" + location +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject piece = new JSONObject();
        piece.put("color", color.toString());
        piece.put("x", location.getX());
        piece.put("y", location.getY());

        return piece;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public abstract List<Location> possibleMoves();
}
