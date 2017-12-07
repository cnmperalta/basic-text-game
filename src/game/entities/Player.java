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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the inventory
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public Item getItemFromInventory(String itemName) {
        for(Item item : inventory)
            if(item.getItemName().equalsIgnoreCase(itemName)) return item;

        return null;
    }

    public boolean hasItem(String itemName) {
        for(Item item : inventory)
            if(item.getItemName().equalsIgnoreCase(itemName)) return true;
        return false;
    }

    public Item removeItemFromInventory(String itemName) {
        for(Item item : inventory)
            if(item.getItemName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                return item;
            }
        return null;
    }
}
