package dev.nano.mcc.client;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import dev.nano.mcc.MCCAssistant;

@BrowserCallable
@AnonymousAllowed
public class MCCService {

    private final MCCAssistant mccAssistant;

    public MCCService(MCCAssistant mccAssistant) {
        this.mccAssistant = mccAssistant;
    }

    public String getRecipes(String dishName) {
        return mccAssistant.getRecipes(dishName);
    }

    public String getDishImage(String dishName) {
        return mccAssistant.getDishImage(dishName);
    }
}
