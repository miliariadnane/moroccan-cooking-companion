package dev.nano.mcc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaderNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest(classes = MCCApplication.class)
class MCCApplicationIntegrationTests {

    @Autowired
    MCCAssistant mccAssistant;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final ClientAndServer mockServer = startClientAndServer(2445);

    private static final String TEST_DISH_NAME = "couscous with seven vegetables";
    
    @BeforeEach
    void setup() {
        mockServer.reset();
    }

    @Test
    void generateDishImage() throws JsonProcessingException {

        OpenAiImageApi.OpenAiImageResponse mockedResponse = new OpenAiImageApi.OpenAiImageResponse(
                20L,
                List.of(new OpenAiImageApi.Data(
                        "https://openai.com/image/dish_generated_url.png",
                        "base64_encoding_value",
                        "revised_prompt_value"
                ))
        );
        
        mockOpenAiGenerativeResponses("/v1/images/generations", objectMapper.writeValueAsString(mockedResponse));
        String imageUrl = mccAssistant.getDishImage(TEST_DISH_NAME);
        assertThat(imageUrl).isNotNull().isEqualTo("https://openai.com/image/dish_generated_url.png");
        System.out.println("image url: " + imageUrl);
    }

    

    @Test
    void generateRecipes() throws JsonProcessingException {

        OpenAiApi.ChatCompletion mockedResponse = new OpenAiApi.ChatCompletion(
                "id_value",
                List.of(new OpenAiApi.ChatCompletion
                        .Choice(
                        OpenAiApi.ChatCompletionFinishReason.STOP,
                        1,
                        new OpenAiApi.ChatCompletionMessage("Detailed dish of a couscous recipe with seven vegetables", null),
                        null)),
                10L,
                "gpt-4",
                "systemFingerPrint",
                null,
                new OpenAiApi.Usage(1, 2, 3)
        );
        mockOpenAiGenerativeResponses("/v1/chat/completions", objectMapper.writeValueAsString(mockedResponse));

        String recipe = mccAssistant.getRecipes(TEST_DISH_NAME);
        assertThat(recipe)
                .isNotNull()
                .isEqualTo("Detailed dish of a couscous recipe with seven vegetables");
    }

    private void mockOpenAiGenerativeResponses(String path, String objectMapper) throws JsonProcessingException {
        mockServer.when(request().withMethod("POST").withPath(path))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(HttpHeaderNames.CONTENT_TYPE.toString(), MediaType.APPLICATION_JSON.toString())
                                .withBody(objectMapper));
    }
    

}
