package Pieces;

import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_rdt60.png" : "/img/Chess_rlt60.png");
    }

    @Override
    public List<Location> possibleMoves() {
        List<Location> moves = new ArrayList<>();
        moves.addAll(Queen.specialMoves(location, 1, 0));
        moves.addAll(Queen.specialMoves(location, -1, 0));
        moves.addAll(Queen.specialMoves(location, 0, -1));
        moves.addAll(Queen.specialMoves(location, 0, 1));

        return moves;
    }
}
