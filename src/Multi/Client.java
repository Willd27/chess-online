package Multi;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8100);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {

        }
        System.out.println(s.getPort());
    }
}
