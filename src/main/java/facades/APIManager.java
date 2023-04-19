package facades;

import org.asynchttpclient.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;

public class APIManager {
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
    public static void factAPI() throws Exception {

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

                String facttext = jsonResponse.getString("fact");

                System.out.println("Fact: " + facttext);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        responseFuture.join();
        client.close();

    }
    public static void allAPI(){
        try {
            //APIFunctions
            jokesAPI();
            factAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}