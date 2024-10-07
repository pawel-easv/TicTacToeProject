package dk.easv.tictactoe.bll;

import okhttp3.*;
import org.json.JSONObject;

public class ChatGPTAPI {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "";  // Replace with your actual API key
        String prompt = "{{1, 1, 0}, {-1, 1, 0}, {-1, -1, -1}} is a Tic-Tac-Toe board where -1 is a blank space, 0 is O (circle), and 1 is X (cross). Return the row (0-2) and column (0-2) of the best move for player X (1). Format your answer as: ROW_NUMBER, COLUMN_NUMBER. dont add anything else";

        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{\n" +
                "  \"model\": \"gpt-4o\",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"user\", \"content\": \"" + prompt + "\"}\n" +
                "  ],\n" +
                "  \"max_tokens\": 100\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, jsonBody);

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        // Parse the response and extract the assistant's content
        JSONObject jsonResponse = new JSONObject(responseBody);
        String assistantResponse = jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

        // Print only the assistant's response
        System.out.println(assistantResponse);
    }
}
