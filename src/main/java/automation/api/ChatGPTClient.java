package automation.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ChatGPTClient {
    private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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"; // Replace with your actual API key
    private final OkHttpClient httpClient;

    public ChatGPTClient() {
        this.httpClient = new OkHttpClient();
    }

    /**
     * Method to send a request to OpenAI to fix a broken locator.
     */
    public String requestLocatorFix(String brokenLocator, String pageSource) {
        String prompt = generatePrompt(brokenLocator, pageSource);
        logger.info("Preparing request to OpenAI API...");
        logger.debug("Prompt for OpenAI: {}", prompt);

        // Create request body using Gson
        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("model", "gpt-3.5-turbo");

        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", prompt);
        messages.add(message);

        requestBodyJson.add("messages", messages);

        String requestBodyString = requestBodyJson.toString();
        logger.debug("Request body: {}", requestBodyString);

        RequestBody body = RequestBody.create(
                requestBodyString,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        logger.info("Sending request to OpenAI API...");
        try (Response response = httpClient.newCall(request).execute()) {
            logger.info("Request sent. Response code: {}", response.code());

            if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                logger.debug("Response from OpenAI: {}", jsonResponse);
                return parseResponse(jsonResponse);
            } else {
                logger.error("Error while executing request to OpenAI API. Response code: {}", response.code());
                if (response.body() != null) {
                    logger.error("Response body: {}", response.body().string());
                }
            }
        } catch (IOException e) {
            logger.error("Error connecting to OpenAI API: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Generate prompt for the request to OpenAI.
     */
    private String generatePrompt(String brokenLocator, String pageSource) {
        return "I have a broken locator: " + brokenLocator + ". Here is the HTML code of the current page: " + pageSource +
                ". Please suggest a corrected locator in Selenium format (e.g., By.id(\"...\"), By.cssSelector(\"...\"), By.xpath(\"...\")).";
    }

    /**
     * Parse response from OpenAI to extract the corrected locator.
     */
    private String parseResponse(String jsonResponse) {
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("choices") && jsonObject.getAsJsonArray("choices").size() > 0) {
                String content = jsonObject.getAsJsonArray("choices").get(0)
                        .getAsJsonObject()
                        .get("message")
                        .getAsJsonObject()
                        .get("content")
                        .getAsString();

                logger.info("Received content from OpenAI: {}", content);

                // Extract locator from response in Selenium format
                if (content.contains("By.id")) {
                    return content.substring(content.indexOf("By.id"), content.indexOf(")") + 1).trim();
                } else if (content.contains("By.cssSelector")) {
                    return content.substring(content.indexOf("By.cssSelector"), content.indexOf(")") + 1).trim();
                } else if (content.contains("By.xpath")) {
                    return content.substring(content.indexOf("By.xpath"), content.indexOf(")") + 1).trim();
                }

                logger.warn("Failed to extract locator from response: {}", content);
            }
        } catch (Exception e) {
            logger.error("Error parsing response from OpenAI: {}", e.getMessage());
        }
        return null;
    }
}
