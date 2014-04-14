package ludowars.core.gui;

import java.util.Stack;

/**
 *
 * @author kjagiello
 */
public class ScreenManager {
    private Stack screens;

    public ScreenManager() {
        screens = new Stack();
    }

    public Screen currentScreen() {
        try {
            return (Screen) screens.lastElement();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    public void push(Screen s) {
        if (currentScreen() != null) {
            currentScreen().hide();
        }
        screens.push(s);
        s.setManager(this);
        s.show();
    }

    public void pop() {
        currentScreen().hide();
        screens.pop();
        if (currentScreen() != null) {
            currentScreen().show();
        }
    }
}
