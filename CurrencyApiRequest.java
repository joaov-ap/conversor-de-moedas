import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyApiRequest {
    private static final String URL = "https://v6.exchangerate-api.com/v6/";
    private String apiKey;

    public CurrencyApiRequest(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiRequest(CurrencyConverter currency) throws IOException, InterruptedException {
        String baseCode = currency.getBaseCode();
        String targetCode = currency.getTargetCode();
        double amount = currency.getAmount();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL + this.apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amount)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
