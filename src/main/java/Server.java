import log.LogCreator;
import servidor.ConectionThreads;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static final int PORTA = 8080;

    public static void main(String[] args) throws IOException {
        LogCreator.startLog();
        ServerSocket ss = new ServerSocket(PORTA);

        while (true) {
            ConectionThreads.listen(ss.accept());
        }

    }
}
