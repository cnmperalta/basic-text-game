package game.entities;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private List<Item> inventory;

    public Player() {}

    public Player(String name) {
        this.name = name;
        inventory = new LinkedList<Item>();
    }

    public Player(String name, List<Item> inventory) {
        this.name = name;
        this.inventory = new LinkedList<Item>(inventory);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }
}