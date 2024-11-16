package automation.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ChatGPTClient {
    private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = System.getenv("OPENAI_API_KEY"); // Получение API-ключа из переменной окружения
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient httpClient;

    public ChatGPTClient() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Запрос на исправление локатора через ChatGPT.
     *
     * @param brokenLocator Ломанный локатор
     * @param pageSource    Исходный код страницы
     * @return Исправленный локатор или null в случае неудачи
     */
    public String requestLocatorFix(String brokenLocator, String pageSource) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            logger.error("API ключ не установлен. Пожалуйста, задайте переменную окружения 'OPENAI_API_KEY'.");
            return null;
        }

        String prompt = "У меня есть сломанный локатор: " + brokenLocator + ". " +
                "Вот HTML-код текущей страницы: " + pageSource + ". " +
                "Пожалуйста, предложите исправленный локатор.";

        try {
            // Создаем тело запроса
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("model", "gpt-3.5-turbo");

            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", prompt);

            jsonObject.add("messages", JsonParser.parseString("[" + message.toString() + "]"));

            RequestBody body = RequestBody.create(JSON, jsonObject.toString());

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();

            // Проверка успешности запроса
            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                logger.info("Response from ChatGPT: {}", jsonResponse);
                return parseResponse(jsonResponse);
            } else {
                logger.error("Failed to get a response from ChatGPT. Code: {}, Message: {}",
                        response.code(), response.message());
            }
        } catch (Exception e) {
            logger.error("Error while requesting locator fix from ChatGPT", e);
        }
        return null;
    }

    /**
     * Парсинг ответа от ChatGPT для получения исправленного локатора.
     *
     * @param jsonResponse JSON-ответ от ChatGPT
     * @return Исправленный локатор или null
     */
    private String parseResponse(String jsonResponse) {
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            if (jsonObject.has("choices") && jsonObject.getAsJsonArray("choices").size() > 0) {
                return jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject()
                        .get("message").getAsJsonObject()
                        .get("content").getAsString();
            }
        } catch (Exception e) {
            logger.error("Error while parsing ChatGPT response", e);
        }
        return null;
    }
}