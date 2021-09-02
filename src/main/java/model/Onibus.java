package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Onibus {
    private static final int totalAssentos = 45;
    private static ArrayList<Assento> assentos = new ArrayList<Assento>(totalAssentos);

    public Onibus() {
        for (int i=0; i<totalAssentos; i++) {
            assentos.add(new Assento(i+1));
        }
    }

    public static ArrayList<Assento> getAssentos() {
        return assentos;
    }

    public static int getTotalAssentos() {
        return totalAssentos;
    }

    public boolean reservarAssento(int numero, Passageiro passageiro) {
        Assento assento = assentos.get(numero);
        synchronized (assentos) {
            if (!assento.isReserva()) {
                assento.setReserva(true);
            } else {
                return false;
            }
        }
        assento.setData(LocalDateTime.now());
        assento.setPassageiro(passageiro);

        return true;
    }
}
