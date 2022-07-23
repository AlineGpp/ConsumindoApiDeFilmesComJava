import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=872995efee79646f5b0d834c33522673";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        int i = 0;

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            String tituloFilme = filme.get("title");
            float nota = Float.parseFloat(filme.get("vote_average"));
            int notaInt = (int) nota;

            String emoji = "";
            System.out.println("\u001b[1m" + "\u001b[37m \u001b[44m" + tituloFilme + "\u001b[m");
            System.out.println("https://image.tmdb.org/t/p/w500" + filme.get("backdrop_path"));

            for (int j = 0; j < notaInt; j++) {
                emoji += "\u2B50";
            }
            System.out.println(emoji);
        }

    }
}