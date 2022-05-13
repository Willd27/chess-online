package Pieces;

import Game.Board;
import Game.BoardSingleton;
import Game.ColorPiece;
import Game.Location;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Location location, ColorPiece color) {
        super(location, color, color == ColorPiece.Black ? "/img/Chess_pdt60.png" : "/img/Chess_plt60.png");
    }

    @Override
    public List<Location> possibleMoves() {
        List<Location> killMoves = canKill();
        assert killMoves != null;
        if (killMoves.size() < 1) {
            List<Location> moves = new ArrayList<>();
            int direction = 1;
            if (color == ColorPiece.Black) {
                direction = -1;
                if (location.isOnLine(6)) {
                    if (BoardSingleton.getBoard().getPieceByLocation(new Location(this.location.getX(), this.location.getY() + 2 * direction)) == null)
                        moves.add(new Location(this.location.getX(), this.location.getY() + 2 * direction));
                }
            } else if (location.isOnLine(1)) {
                if (BoardSingleton.getBoard().getPieceByLocation(new Location(this.location.getX(), this.location.getY() + 2 * direction)) == null)
                    moves.add(new Location(this.location.getX(), this.location.getY() + 2 * direction));
            }

            if (BoardSingleton.getBoard().getPieceByLocation(new Location(this.location.getX(), this.location.getY() + 2 * direction)) == null)
                moves.add(new Location(this.location.getX(), this.location.getY() + direction));
            return moves;
        }
        return killMoves;
    }

    public List<Location> canKill() {
        List<Location> moves = new ArrayList<>();
        Board board = BoardSingleton.getBoard();
        int direction = 1;
        if (color == ColorPiece.Black) {
            direction = -1;

        }

        Location leftLoca = new Location(location.getX() + 1, location.getY() + direction);
        Location rightLoca = new Location(location.getX() - 1, location.getY() + direction);

        if (board.getPieceByLocation(leftLoca) != null) {
            addMoves(moves, leftLoca);
        }
        if (board.getPieceByLocation(rightLoca) != null) {
            addMoves(moves, rightLoca);
        }
        return moves;
    }
}
