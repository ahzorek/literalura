package com.andrezorek.literalura.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for creating and displaying command-line menus.
 * The first option in the list is reserved for the exit option.
 */
public class Menu {
    private List<String> options;
    private String menuMessage = "MENU";

    public Menu(List<String> options) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("Options list must contain at least an exit option.");
        }
        this.options = new ArrayList<>(options);
    }


    public void setMenuMessage(String message) {
        this.menuMessage = message != null ? message : this.menuMessage;
    }

    public void addMenuOption(String option) {
        this.options.add(option);
    }
    
    public String buildMenu() {
        StringBuilder menuBuilder = new StringBuilder(menuMessage + "\n");
        for (int i = 1; i < options.size(); i++) {
            menuBuilder.append(String.format("[%d] - %s%n", i, options.get(i)));
        }
        menuBuilder.append(String.format("[%d] - %s%n", 0, options.get(0)));
        return menuBuilder.toString();
    }

    public void display() {
        System.out.print(buildMenu());
    }
}
