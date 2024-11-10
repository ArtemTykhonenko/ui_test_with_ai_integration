package automation.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ChatGPTClient {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "your_actual_api_key"; // Замените на ваш реальный API ключ
    private final OkHttpClient httpClient;

    public ChatGPTClient() {
        this.httpClient = new OkHttpClient();
    }

    public String requestLocatorFix(String brokenLocator, String pageSource) {
        String prompt = "У меня есть сломанный локатор: " + brokenLocator + ". " +
                "Вот HTML-код текущей страницы: " + pageSource + ". " +
                "Пожалуйста, предложите исправленный локатор.";

        try {
            RequestBody body = RequestBody.create(
                    MediaType.get("application/json"),
                    "{ \"model\": \"gpt-3.5-turbo\", \"messages\":[{\"role\":\"user\", \"content\":\"" + prompt + "\"}]}");

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                return parseResponse(jsonResponse); // Обработка ответа
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Если не удалось, возвращаем null
    }

    private String parseResponse(String jsonResponse) {
        // Используем библиотеку Gson для парсинга JSON-ответа
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Извлечение исправленного локатора из jsonObject
        if (jsonObject.has("choices") && jsonObject.getAsJsonArray("choices").size() > 0) {
            return jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject()
                    .get("message").getAsJsonObject().get("content").getAsString();
        }
        return null; // Если не удалось извлечь ответ
    }
}