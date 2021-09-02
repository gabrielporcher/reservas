package model;

import java.time.LocalDateTime;

public class Passageiro {
    private String nome;
    private LocalDateTime data;
    private String reqIp;

    public Passageiro(String nome) {
        this.nome = nome;
        this.data = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }
}
