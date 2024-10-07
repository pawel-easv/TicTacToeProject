/*package dk.easv.tictactoe.bll;

import okhttp3.*;

public class ChatGPTAPI {
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String prompt = "My name is";
        String apiKey = "";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + prompt + "\"}");

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        System.out.println(responseBody);
    }
}*/
