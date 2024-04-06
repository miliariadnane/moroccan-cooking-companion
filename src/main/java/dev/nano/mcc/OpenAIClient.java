package dev.nano.mcc;

import dev.nano.mcc.client.AIClientPort;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Repository
public class OpenAIClient implements AIClientPort {

    @Value("${spring.ai.openai.api-key}")
    String apiKey;
    
    @Value("${spring.ai.openai.base-url}")
    String baseUrl;

    private final Resource systemPrompt;
    

    public OpenAIClient(@Value("classpath:/prompt/system-prompt.st") Resource systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public String generateRecipe(String instructionRecipe) {
        Prompt prompt = new Prompt(List.of(new SystemMessage(this.systemPrompt), new UserMessage(instructionRecipe)));
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey);
        OpenAiChatOptions options = new OpenAiChatOptions.Builder()
                .withModel("gpt-4")
                .build();
        return new OpenAiChatClient(openAiApi, options).call(prompt).getResult().getOutput().getContent();
    }

    @Override
    public String generateDishImage(String instructionDishImage) {
        OpenAiImageApi openAiApi = new OpenAiImageApi(baseUrl, apiKey, RestClient.builder());
        var options = OpenAiImageOptions.builder()
                .withQuality("hd")
                .withHeight(1024).withWidth(1024)
                .withResponseFormat("url")
                .withModel("dall-e-3")
                .build();
        OpenAiImageClient openAiImageClient = new OpenAiImageClient(openAiApi, options, RetryTemplate.builder().build());
        return openAiImageClient.call(new ImagePrompt(instructionDishImage)).getResult().getOutput().getUrl();
    }
}
