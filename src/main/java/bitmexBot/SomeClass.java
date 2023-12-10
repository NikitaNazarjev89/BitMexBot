package bitmexBot;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SomeClass {

    public static void main(String[] args) throws IOException, InterruptedException {

String body  = " {\n" +
        "        \"userId\": 1,\n" +
        "        \"title\": \"My name is NIKITOS\",\n" +
        "        \"body\": \"I learn gttp request and responce\"\n" +
        "    }";

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("content-type","application/json")
                .build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request,HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
