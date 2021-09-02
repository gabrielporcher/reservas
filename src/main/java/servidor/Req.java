package servidor;

import java.util.HashMap;
import java.util.Map;

public class Req {
    private String page;
    private Map<String, String> params = new HashMap<>();

    Req(String buffer) {
        String[] linhas = buffer.split("\n");
        String linha0 = linhas[0].split(" ")[1];
        String[] protocolo = linha0.split("\\?");

        this.page = protocolo[0];

        if (protocolo.length > 1) {
            String[] params = protocolo[1].split("&");
            //System.out.println("PARAMS: " + params);

            for (String p : params) {
                int index = p.indexOf("=");
                this.params.put(p.substring(0, index), p.substring(index + 1));
                //System.out.println("TESTE: " + p);
            }
        }
    }

    public String getPage() {
        return page;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        /*return "Req{" +
         *       "page='" + page + '\'' +
         *      ", params=" + params +
         *       '}';
         *
         */
        StringBuilder sb = new StringBuilder().append(this.page + "\n");
        for (String key : this.params.keySet()) {
            sb.append(key + "=\"" + this.params.get(key) + "\"\n");
        }
        return sb.toString();
    }
}
