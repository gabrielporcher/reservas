package pages;

public class NotFound {
    String header = "HTTP/1.1 200 OK\nContent-Type: text/html; charset=UTF-8\n\n";
    String body = "<h2>404 Not Found<h2>";

    public String render() {
        return header + body;
    }
}
