package dev.nano.mcc;

import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class OpenAIClient {

    @Value("${spring.ai.openai.api-key}")
    String apiKey;

    public OpenAiChatClient getOpenAiChatClient() {
        OpenAiApi openAiApi = new OpenAiApi(apiKey);
        var options = new OpenAiChatOptions.Builder()
                .withModel("gpt-4")
                .build();
        return new OpenAiChatClient(openAiApi, options);
    }

    public OpenAiImageClient getOpenAiImageClient() {
        OpenAiImageApi openAiApi = new OpenAiImageApi(apiKey);
        var options = OpenAiImageOptions.builder()
                .withHeight(1024).withWidth(1024)
                .withResponseFormat("url")
                .withModel("dall-e-3")
                .build();
        return new OpenAiImageClient(openAiApi,options, null);
    }
}
