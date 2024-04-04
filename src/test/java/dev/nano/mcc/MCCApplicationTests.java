package dev.nano.mcc;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MCCApplication.class)
class MCCApplicationTests {

    @Autowired
    MCCAssistant mccAssistant;

    private static final String TEST_DISH_NAME = "couscous with seven vegetables";

    @Test
    void generateDishImage() {
        String imageUrl = mccAssistant.getDishImage(TEST_DISH_NAME);
        assertThat(imageUrl).isNotNull();
        System.out.println("image url: " + imageUrl);
    }

    @Test
    void generateRecipes() {
        String recipe = mccAssistant.getRecipes(TEST_DISH_NAME);
        assertThat(recipe).isNotNull();
        System.out.println("recipe: " + recipe);
    }

}
