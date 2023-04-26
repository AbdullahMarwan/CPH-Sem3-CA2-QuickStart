package facades;

import com.google.gson.Gson;
import io.netty.handler.codec.http.HttpRequest;
import org.asynchttpclient.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class ExternalApiFacade {

    private static Gson GSON = new Gson();
    private static ExternalApiFacade instance;
    private static EntityManagerFactory emf;

    private ExternalApiFacade() {}

    public static ExternalApiFacade getExternalApiFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ExternalApiFacade();
        }
        return instance;
    }

    public static void jokesAPI() throws Exception {

        AsyncHttpClient client = new DefaultAsyncHttpClient();
        String url = "https://dad-jokes.p.rapidapi.com/random/joke";
        Request request = new RequestBuilder()
                .setMethod("GET")
                .setUrl(url)
                .addHeader("X-RapidAPI-Key", "73d1a7f55cmshd7234873c5eaf06p107fa6jsnea4135418dd7")
                .addHeader("X-RapidAPI-Host", "dad-jokes.p.rapidapi.com")
                .build();

        CompletableFuture<Response> responseFuture = client.executeRequest(request)
                .toCompletableFuture();

        responseFuture.thenAccept(response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response.getResponseBody());
                JSONArray jokesArray = jsonResponse.getJSONArray("body");
                JSONObject joke = jokesArray.getJSONObject(0);
                String setup = joke.getString("setup");
                String punchline = joke.getString("punchline");
                System.out.println("Setup: " + setup);
                System.out.println("Punchline: " + punchline);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        responseFuture.join();
        client.close();
    }
    public AtomicReference<String> factAPI() throws Exception {

        AtomicReference<String> facttext = new AtomicReference<>("");
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        //YOUR API URL
        String url = "https://fun-facts1.p.rapidapi.com/api/fun-facts";
        Request request = new RequestBuilder()
                .setMethod("GET")
                .setUrl(url)
                //Your headerkeys from RapidAPI
                .addHeader("X-RapidAPI-Key", "73d1a7f55cmshd7234873c5eaf06p107fa6jsnea4135418dd7")
                .addHeader("X-RapidAPI-Host", "fun-facts1.p.rapidapi.com")
                .build();

        CompletableFuture<Response> responseFuture = client.executeRequest(request)
                .toCompletableFuture();

        responseFuture.thenAccept(response ->

        {
            try {

                JSONObject jsonResponse = new JSONObject(response.getResponseBody());

                facttext.set(jsonResponse.getString("fact"));

                System.out.println("Fact: " + facttext);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        responseFuture.join();
        client.close();
    return facttext;
    }

    public String getKanyeQuote(String url) throws Exception {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder(URI.create(url))
                .header("accept", "application/json")
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.print(response.body());
        KanyeDTO gson = new Gson().fromJson(response.body(), KanyeDTO.class);
        System.out.print(gsonString);

        return gson.getQuote();
    }


    public static void main(String[] args) throws Exception {
        ExternalApiFacade facade = new ExternalApiFacade();
        try {
            facade.getKanyeQuote("https://api.kanye.rest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void allAPI(){
        try {
            //APIFunctions
            jokesAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}