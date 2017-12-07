package game.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Room {
    private int roomID;
    private String roomStringID;
    private String roomType;
    private List<Item> items;
    private HashMap<String, Room> exits;

    public Room() {}

    public Room(int roomID) {
        this.roomID = roomID;
    }

    public Room(int roomID, String roomStringID) {
        this.roomID = roomID;
        this.roomStringID = roomStringID;
        roomType = roomStringID.split("-")[0];
        exits = new HashMap<String, Room>();
        items = new LinkedList<Item>();
    }

    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @return the roomStringID
     */
    public String getRoomStringID() {
        return roomStringID;
    }

    /**
     * @return the exits
     */
    public HashMap<String, Room> getExits() {
        return exits;
    }

    public void addExit(String direction, Room destination) {
        if(!exits.containsKey(direction))
            exits.put(direction, destination);
        else
            System.out.println("There is already a room in that direction.");
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for(Item item : items)
            if(item.getItemName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        return null;
    }

    @Override
    public String toString() {
        return roomStringID;
    }
}