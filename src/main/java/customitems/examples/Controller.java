package customitems.examples;

import customitems.controller.CustomItem;

public class Controller {

    public Controller() {
        CustomItem.register(Example.class);
    }

}
