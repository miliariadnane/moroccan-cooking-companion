package dev.nano.mcc;

import dev.nano.mcc.client.AIClientPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MCCAssistant {

    private final AIClientPort aiClientPort;

    public MCCAssistant(AIClientPort aiClientPort) {
        this.aiClientPort = aiClientPort;
    }

    public String getRecipes(String dishName) {
        return aiClientPort.generateRecipe("Can you provide a recipe for " + dishName + "?");
    }

    public String getDishImage(String dishImageRequest) {
        return aiClientPort.generateDishImage("Generate an image of a Moroccan dish called " + dishImageRequest);
    }
}
