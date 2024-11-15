package automation.api;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ChatGPTClient {
    private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "YOUR_OPENAI_API_KEY_HERE"; // Замените на ваш ключ API
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    public String requestLocatorFix(String locator, String pageSource) {
        String prompt = generatePrompt(locator, pageSource);
        RequestBody body = new FormBody.Builder()
                .add("model", "text-davinci-003")
                .add("prompt", prompt)
                .add("max_tokens", "150")
                .add("temperature", "0.5")
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return parseResponse(responseBody);
            } else {
                logger.error("Failed to get response from ChatGPT API. Code: {}, Message: {}", response.code(), response.message());
            }
        } catch (IOException e) {
            logger.error("Error while making request to ChatGPT API", e);
        }
        return null;
    }

    private String generatePrompt(String locator, String pageSource) {
        return "The following locator is not working: " + locator + ". Here is the page source: " + pageSource + ". Can you suggest a corrected locator?";
    }

    private String parseResponse(String responseBody) {
        // Простой парсинг ответа для извлечения сгенерированного текста (может потребоваться доработка)
        int startIndex = responseBody.indexOf("\"text\":\"") + 8;
        int endIndex = responseBody.indexOf("\"", startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return responseBody.substring(startIndex, endIndex).trim();
        }
        logger.error("Unable to parse response from ChatGPT API: {}", responseBody);
        return null;
    }
}