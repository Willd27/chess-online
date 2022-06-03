package Multi;

import Game.Board;
import Game.ColorPiece;
import Game.BoardSingleton;
import Game.Game;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@javax.websocket.ClientEndpoint
public class ClientEndpoint {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("--- Connected " + session.getId());
        this.session = session;
        try {
            session.getBasicRemote().sendText("test connexion");
//            session.getBasicRemote().sendText(BoardSingleton.getBoard().boardToJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage(String message, Session session) {
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("--- Received : " + message);

        if (Objects.equals(message, "start B")) {
            Game.startGame(ColorPiece.Black, this);
        } else if (Objects.equals(message, "start W")) {
            Game.startGame(ColorPiece.White, this);
        } else if (Objects.equals(message, "session created")) {
            System.out.println("Connexion established");
        } else if (message.charAt(0) == '[') {
            try {
                BoardSingleton.getBoard().updateAllPiecesAlive(message);
            } catch (Exception e) {
                System.out.println("Can't update pieces");
                return "NACK";
            }
        }


//            String userInput = bufferRead.readLine();
        return "ACK";
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("--- Session: " + session.getId());
        System.out.println("--- Closing because: " + closeReason);
    }

    public void sendNewLocations() {
        Board board = BoardSingleton.getBoard();

        try {
            String strData = board.boardToJSON();
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
            while (true) {
            }
        } catch (DeploymentException | URISyntaxException e) {
            e.printStackTrace();
            System.out.println("Connexion closed");
        }
    }
}
