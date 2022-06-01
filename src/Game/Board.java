package Game;

import Pieces.Pawn;
import Pieces.Piece;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;
import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int height;
    private final int width;

    private final List<Piece> allPiecesAlive = new ArrayList<>();

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public List<Piece> getAllPiecesAlive() {
        return allPiecesAlive;
    }

    public void updateAllPiecesAlive(String strJSON) {
        System.out.println("WIlliam");

        Object obj = JSONValue.parse(strJSON);
        System.out.println(obj);
        JSONArray array = (JSONArray) obj;
        System.out.println("----------JSONArray");


        System.out.println(array);

        allPiecesAlive.clear();
//        System.out.println(allPiecesAlive);

        for (int i = 0; i < array.size(); i++) {
            JSONObject currPiece = (JSONObject) array.get(i);
            System.out.println("Curr Piece = " + currPiece);
            System.out.println((long) currPiece.get("x") + 5);
            System.out.println(currPiece.get("y").getClass());

            System.out.println(currPiece.get("color"));
            System.out.println(ColorPiece.valueOf((String) currPiece.get("color")).getClass());


            new Pawn(new Location((int) (long) currPiece.get("x"), (int) (long) currPiece.get("y")), ColorPiece.valueOf((String) currPiece.get("color")));
        }

//        allPiecesAlive.add(new Pawn(new Location(0,1), ColorPiece.Black));


        System.out.println(allPiecesAlive);


        UISingleton.getUi().updateUI();
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
        System.out.println("Board To Json Func");
        JSONArray piecesToJson = new JSONArray();

        for (Piece piece : allPiecesAlive) {
            piecesToJson.add(piece.toJSON());
        }
        return piecesToJson.toJSONString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
