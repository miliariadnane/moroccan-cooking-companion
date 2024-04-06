package dev.nano.mcc.client;

public interface AIClientPort {
    
    String generateRecipe(String instructionRecipe);
    String generateDishImage(String instructionDishImage);
}
