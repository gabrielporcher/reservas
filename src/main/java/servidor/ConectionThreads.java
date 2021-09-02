package servidor;

import log.LogCreator;
import model.Onibus;
import model.Passageiro;
import pages.Formulario;
import pages.Index;
import pages.NotFound;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConectionThreads implements Runnable {
    private Socket socket;
    private Onibus onibus = new Onibus();
    private String ip_req;

    public ConectionThreads(Socket socket) {
        this.socket = socket;
        this.ip_req = socket.getInetAddress().getHostAddress();
    }

    public static void listen(Socket socket) {
        new Thread(new ConectionThreads(socket)).start();
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int size = socket.getInputStream().read(buffer);

            Req req = new Req(new String (buffer, 0, size));
            LogCreator.notifyEvent("IP : " +ip_req + " Acessou: " + req);

            String page = null;

            if (req.getPage().equals("/")) {
                page = new Index().render();
            } else if (req.getPage().equals("/cadastro")) {
                page = new Formulario(req).render();
            } else if (req.getPage().equals("/compra")) {
                onibus.reservarAssento(Integer.parseInt(req.getParams().get("lugar")), new Passageiro(req.getParams().get("nome")));
                page = new Index().render();
            } else {
                page = new NotFound().render();
            }

            socket.getOutputStream().write((page).getBytes(StandardCharsets.UTF_8));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
