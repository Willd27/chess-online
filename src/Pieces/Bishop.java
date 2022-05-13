package Pieces;

import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_bdt60.png" : "/img/Chess_blt60.png");
    }


    @Override
    public List<Location> possibleMoves() {
        List<Location> moves = new ArrayList<>();
        moves.addAll(Queen.specialMoves(location, 1, 1));
        moves.addAll(Queen.specialMoves(location, 1, -1));
        moves.addAll(Queen.specialMoves(location, -1, -1));
        moves.addAll(Queen.specialMoves(location, -1, 1));

        return moves;
    }
}
