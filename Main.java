import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.awt.Desktop;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("--- BUSCANDO FOTO ESPACIAL DA NASA ---");

        String minhaChave = "CQqTcxDfufRJN5nMuRs4aVKcms1NfoOiaWhD1D9Z";
        String endereco = "https://api.nasa.gov/planetary/apod?api_key=" + minhaChave;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        String corpoResposta = response.body();

        System.out.println("\nResposta da NASA:");
        System.out.println(corpoResposta);

        String urlImagem = corpoResposta.split("\"url\":\"")[1].split("\"")[0];

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI(urlImagem));
        }
    }
}