package Pieces;

import Game.BoardSingleton;
import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_qdt60.png" : "/img/Chess_qlt60.png");
    }

    public static List<Location> specialMoves(Location location, int incrementX, int incrementY) {
        List<Location> moves = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            Location currLoc = location;
            if (incrementX == 1)
                currLoc = new Location(currLoc.getX() + i, currLoc.getY());
            else if (incrementX == -1)
                currLoc = new Location(currLoc.getX() - i, currLoc.getY());
            if (incrementY == 1)
                currLoc = new Location(currLoc.getX(), currLoc.getY() + i);
            else if (incrementY == -1)
                currLoc = new Location(currLoc.getX(), currLoc.getY() - i);

            if (BoardSingleton.getBoard().isLocationTaken(currLoc, BoardSingleton.getBoard().getPieceByLocation(location).color) || !currLoc.isOnBoard())
                break;
            moves.add(currLoc);
        }

        return moves;
    }

    @Override
    public List<Location> possibleMoves() {
        List<Location> moves = new ArrayList<>();

        // Movement like Rook
        moves.addAll(specialMoves(location, 1, 0));
        moves.addAll(specialMoves(location, -1, 0));
        moves.addAll(specialMoves(location, 0, -1));
        moves.addAll(specialMoves(location, 0, 1));

        // Movement like Bishop
        moves.addAll(specialMoves(location, 1, 1));
        moves.addAll(specialMoves(location, 1, -1));
        moves.addAll(specialMoves(location, -1, -1));
        moves.addAll(specialMoves(location, -1, 1));

        return moves;
    }
}
