package Multi;

import Game.Board;
import Game.BoardSingleton;
import Pieces.Piece;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@javax.websocket.ClientEndpoint
public class ClientEndpoint {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println ("--- Connected " + session.getId());
        this.session = session;
        try {
            session.getBasicRemote().sendText(BoardSingleton.getBoard().boardToJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println ("--- Received " + message);
            BoardSingleton.getBoard().updateAllPiecesAlive(message);
            String userInput = bufferRead.readLine();
            return userInput;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("--- Session: " + session.getId());
        System.out.println("--- Closing because: " + closeReason);
    }

    public void sendNewLocations(Piece piece){
        Board board = BoardSingleton.getBoard();

        try {
            String strData = piece.toJSON();
            this.session.getBasicRemote().sendText(strData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer() {
        ClientManager client = ClientManager.createClient();
        try {
            URI uri = new URI("ws://localhost:8100");
            client.connectToServer(ClientEndpoint.class, uri);
            System.out.println("Connected to Server");
            while (true) {}
        } catch (DeploymentException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println("Connexion closed");
        }
    }
}
