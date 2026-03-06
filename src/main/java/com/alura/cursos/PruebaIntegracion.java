package com.alura.cursos;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PruebaIntegracion {

    public static void main(String[] args) throws Exception {

        String apiKey = System.getenv("MISTRAL_API_KEY");

        String json = """
        {
          "model": "mistral-small-latest",
          "messages": [
            {"role": "system", "content": "Eres un generador de productos tecnológicos"},
            {"role": "user", "content": "Genera 5 productos con su precio"}
          ]
        }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.mistral.ai/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}