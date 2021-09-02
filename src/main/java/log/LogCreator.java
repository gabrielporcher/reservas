package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class LogCreator implements Runnable{
    private static final Queue<String> buffer = new LinkedList<>(); //Buscar e remover do inicio e fim da fila //LinkedList implementa Queue
    private static final Object vazio = new Object(); // objeto para monitorar
    private final File file = new File("Reservas-log.txt");
    private FileWriter writeLog = new FileWriter(file);

    public LogCreator() throws IOException {
    }

    @Override
    public void run() {
        while(true) {
            writeFile();
        }
    }

    /*
     * Instancia o log e da start na thread, removendo a
     * necessidade de ter um construtor e um main
     */
    public static LogCreator startLog() {
        LogCreator lc = null;

        try {
            lc = new LogCreator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(lc).start();
        return lc;
    }

    /* Monitora o buffer e "acorda" a thread quando há acesso
     */
    public static void notifyEvent(String event) {
        synchronized (buffer) {
            buffer.add( LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm")) + " - " + event);
        }

        synchronized (vazio) {
            if (buffer.size() == 1) {
                vazio.notify(); //primeiro processo da fila
            }
        }
    }

    private void writeFile() {
        synchronized (vazio) {
            if (buffer.size() == 0) {
                try {
                    vazio.wait(); //"Pausa" a thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (buffer) {
            try{
                writeLog.write(buffer.poll());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writeLog.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
