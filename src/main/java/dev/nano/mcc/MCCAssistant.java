package dev.nano.mcc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MCCAssistant {

    @Value("classpath:/prompt/system-prompt.st")
    private Resource systemPrompt;

    private final OpenAIClient openAIClient;

    public String getRecipes(String query) {
        Message systemMessage = new SystemPromptTemplate(this.systemPrompt)
                .createMessage(Map.of("current_date", java.time.LocalDate.now()));

        log.info("System Message: {}", systemMessage.getContent());

        UserMessage userMessage = new UserMessage(query);

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        return openAIClient.getOpenAiChatClient().call(prompt).getResult().getOutput().getContent();
    }

    public String getDishImage(String dishName) {
        ImagePrompt imagePrompt = new ImagePrompt("Generate an image of a Moroccan dish called " + dishName);
        return openAIClient.getOpenAiImageClient().call(imagePrompt).getResult().getOutput().getUrl();
    }
}
