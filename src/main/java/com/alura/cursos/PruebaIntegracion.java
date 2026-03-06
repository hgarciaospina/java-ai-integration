package com.alura.cursos;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

import java.util.List;

public class PruebaIntegracion {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        var user = "Genere 5 productos";
        var system = "Eres un generador de productos de un ecommerce de tecnología "
                + "y debes responder solo el nombre del producto y su precio.";

        var openAI = SimpleOpenAI.builder()
                .apiKey(dotenv.get("OPENAI_API_KEY"))
                .build();

        ChatRequest request = ChatRequest.builder()
                .model("gpt-4o-mini")
                .messages(List.of(
                        ChatMessage.SystemMessage.of(system),
                        ChatMessage.UserMessage.of(user)
                ))
                .build();

        var response = openAI.chatCompletions()
                .create(request)
                .join();   // 👈 esperar respuesta

        response.getChoices().forEach(choice ->
                System.out.println(choice.getMessage().getContent())
        );
    }
}