package pages;

import model.Onibus;
import model.Passageiro;

public class Index {

    String header = "HTTP/1.1 200 OK\nContent-Type: text/html; charset=UTF-8\n\n";
    String body;
    private String onibus = renderAssentos(); //mudar para assentos
    private String content = "<div class=\"mt-5\"> " + onibus + " </div>";

    public Index() {
        this.body = "<!doctype html>" +
                "<html lang=\"pt-BR\">" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">" +
                "<title>Reserva de passagens</title>"+
                "</head> <body>" +
                "<div class=\"container\">" +
                "<h1>Reserva de passagens</h1>" +
                content +
                "</div>" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>" +
                "</body> </html>";

    }

    private String renderAssentos() {
        StringBuilder onibus = new StringBuilder();

        for (int i = 1; i < Onibus.getTotalAssentos(); i ++ ) {
            Passageiro p = Onibus.getAssentos().get(i).getPassageiro();

            if ( !Onibus.getAssentos().get(i).isReserva() ) {
                onibus.append("<a href=\"/cadastro?lugar=")
                        .append(i).append("\" class=\"btn btn-primary mt-3\">")
                        .append(i).append(" - Disponível").append("</a>\n");
            } else {
                onibus.append("<div class=\"btn btn-danger mt-3\" >")
                        .append(i).append(" - " + p.getNome())
                        .append("</br>").append(p.getData())
                        .append("</div>\n");
            }


        }
        return onibus.toString();
    }

    public String render() {
        return header + body;
    }

}
