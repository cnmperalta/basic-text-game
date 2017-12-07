package game.entities;

import java.util.HashMap;

public class Room {
    private int roomID;
    private String roomStringID;
    private String roomType;
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

    @Override
    public String toString() {
        return roomStringID;
    }
}