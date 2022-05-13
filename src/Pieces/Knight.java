package Pieces;

import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_ndt60.png" : "/img/Chess_nlt60.png");
    }

    @Override
    public List<Location> possibleMoves() {
        List<Location> moves = new ArrayList<>();
        addMoves(moves, new Location(location.getX() + 2, location.getY() + 1));
        addMoves(moves, new Location(location.getX() + 1, location.getY() + 2));
        addMoves(moves, new Location(location.getX() - 2, location.getY() - 1));
        addMoves(moves, new Location(location.getX() - 1, location.getY() - 2));
        addMoves(moves, new Location(location.getX() - 1, location.getY() + 2));
        addMoves(moves, new Location(location.getX() + 1, location.getY() - 2));
        addMoves(moves, new Location(location.getX() - 2, location.getY() + 1));
        addMoves(moves, new Location(location.getX() + 2, location.getY() - 1));
        return moves;
    }
}
