package dev.nano.moroccancookingcompanion;

import dev.nano.mcc.MCCApplication;
import dev.nano.mcc.MCCAssistant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MCCApplication.class)
class MCCApplicationTests {

    @Autowired
    MCCAssistant mccAssistant;

    @Test
    @Disabled
    void generateDishImage() {
        String imageUrl = mccAssistant.getDishImage("couscous with seven vegetables");
        assertThat(imageUrl).isNotNull();
        System.out.println("image url: " + imageUrl);
    }

    @Test
    void generateRecipes() {
        String recipe = mccAssistant.getRecipes("couscous with seven vegetables");
        assertThat(recipe).isNotNull();
        System.out.println("recipe: " + recipe);
    }

}
