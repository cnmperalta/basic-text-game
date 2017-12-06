package game.entities;

public class Item {
    private String name;
    private Room location;
    private boolean inRoom;

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
     * @return the location
     */
    public Room getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Room location) {
        this.location = location;
    }

    /**
     * @param inRoom the inRoom to set
     */
    public void setInRoom(boolean inRoom) {
        this.inRoom = inRoom;
    }

    /**
     * @return the inRoom
     */
    public boolean getInRoom() {
        return inRoom;
    }
}