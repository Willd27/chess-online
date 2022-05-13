package Pieces;

import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_kdt60.png" : "/img/Chess_klt60.png");
    }

    @Override
    public List<Location> possibleMoves() {
        List<Location> moves = new ArrayList<>();

        addMoves(moves, new Location(this.location.getX() + 1, this.location.getY()));
        addMoves(moves, new Location(this.location.getX(), this.location.getY() + 1));
        addMoves(moves, new Location(this.location.getX() - 1, this.location.getY()));
        addMoves(moves, new Location(this.location.getX(), this.location.getY() - 1));
        addMoves(moves, new Location(this.location.getX() + 1, this.location.getY() - 1));
        addMoves(moves, new Location(this.location.getX() - 1, this.location.getY() + 1));
        addMoves(moves, new Location(this.location.getX() - 1, this.location.getY() - 1));
        addMoves(moves, new Location(this.location.getX() + 1, this.location.getY() + 1));

        return moves;
    }
}
