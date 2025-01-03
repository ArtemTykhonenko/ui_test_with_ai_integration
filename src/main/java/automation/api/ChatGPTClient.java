package automation.api;

import automation.utils.Constants;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for interacting with the OpenAI ChatGPT API.
 * It is used to send requests and receive responses from the ChatGPT model
 * to fix broken Selenium locators based on the HTML page source.
 */
public class ChatGPTClient {
    private static final Logger logger = LoggerFactory.getLogger(ChatGPTClient.class);
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = Constants.ChatGPTAPI;

    // OkHttp client with specified timeouts for connection, write, and read operations
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    /**
     * Sends a request to the ChatGPT API to fix a given Selenium locator
     * based on the provided HTML page source.
     *
     * @param locator   The Selenium locator that needs to be fixed.
     * @param pageSource The HTML page source where the locator is used.
     * @return The corrected locator in Selenium format (e.g., By.id(), By.xpath(), By.cssSelector()),
     *         or null if the request fails.
     */
    public String requestLocatorFix(String locator, String pageSource) {
        String prompt = generatePrompt(locator, pageSource);

        // Construct the JSON request for the OpenAI API
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("model", "gpt-3.5-turbo");
        jsonRequest.put("messages", new JSONArray()
                .put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."))
                .put(new JSONObject().put("role", "user").put("content", prompt))
        );
        jsonRequest.put("max_tokens", 150);
        jsonRequest.put("temperature", 0.5);

        RequestBody body = RequestBody.create(
                jsonRequest.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                logger.info("Response from ChatGPT: {}", responseBody);
                return parseResponse(responseBody);
            } else {
                logger.error("Failed to get response from ChatGPT API. Code: {}, Message: {}", response.code(), response.message());
            }
        } catch (IOException e) {
            logger.error("Error while making request to ChatGPT API", e);
        }
        return null;
    }

    /**
     * Generates a prompt for ChatGPT based on the provided locator and HTML page source.
     *
     * @param locator   The Selenium locator that needs to be fixed.
     * @param pageSource The HTML page source where the locator is used.
     * @return The formatted prompt to be sent to ChatGPT.
     */
    private String generatePrompt(String locator, String pageSource) {
        return "The following Selenium locator is not working: " + locator +
                ". Here is the HTML page source:\n" + pageSource +
                "\nPlease provide only the corrected locator in the format Selenium can use (e.g., By.id(\"new-id\"), By.xpath(\"//new-xpath\"), or By.cssSelector(\"new-css\")), without any explanations.";
    }

    /**
     * Parses the response from ChatGPT to extract the corrected locator.
     *
     * @param responseBody The JSON response received from the ChatGPT API.
     * @return The corrected locator (e.g., By.id(), By.xpath(), By.cssSelector()),
     *         or null if parsing fails or the locator is not found.
     */
    private String parseResponse(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (choices.length() > 0) {
                String content = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();

                // Check if the response starts with a valid Selenium locator format
                if (content.startsWith("By.id") || content.startsWith("By.cssSelector") || content.startsWith("By.xpath")) {
                    logger.info("Parsed corrected locator: {}", content);
                    return content;
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing response from ChatGPT API", e);
        }
        logger.error("Unable to parse response from ChatGPT API: {}", responseBody);
        return null;
    }
}
