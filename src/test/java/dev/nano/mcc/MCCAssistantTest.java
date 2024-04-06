package dev.nano.mcc;

import dev.nano.mcc.client.AIClientPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class )
class MCCAssistantTest {
    @InjectMocks
    MCCAssistant mccAssistant;
    
    @Mock
    AIClientPort aiClientPort;
    
    
    @Test
    void shouldReturnDetailedRecipeWhenProvidingRecipeRequest() {

        String recipeRequest = "Couscous with seven vegetables";

        String expected = "Detailed couscous with seven vegetables recipe";
        
        given(aiClientPort.generateRecipe("Can you provide a recipe for " + recipeRequest + "?")).willReturn(expected);

        String result = mccAssistant.getRecipes(recipeRequest);

        Assertions.assertThat(result).isEqualTo(expected);
        
        
    }
    
    @Test
    void shouldReturnDishImageWhenProvidingDishImageRequest() {
        String dishImageRequest = "Couscous with seven vegetables";

        String expectedUrl = "https://openai.com/image/generated-dish.png";

        given(aiClientPort.generateDishImage("Generate an image of a Moroccan dish called " + dishImageRequest)).willReturn(expectedUrl);

        String result = mccAssistant.getDishImage(dishImageRequest);

        Assertions.assertThat(result).isEqualTo(expectedUrl);
    }
}
