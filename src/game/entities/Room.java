package game.entities;

import java.util.LinkedList;
import java.util.List;


public class Room {
    private int roomID;
    // private HashMap<String, Room> adjacentRooms;
    private List<Room> adjacentRooms;

    public Room() {}

    public Room(int roomID) {
        this.roomID = roomID;
        this.adjacentRooms = new LinkedList<Room>();
    }

    public Room(int roomID, List<Room> adjacentRooms) {
        this.roomID = roomID;
        this.adjacentRooms = new LinkedList<Room>(adjacentRooms);
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
    public List<Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(List<Room> adjacentRooms) {
        this.adjacentRooms = new LinkedList<Room>(adjacentRooms);
    }

    public void addAdjacentRoom(Room room) {
        adjacentRooms.add(room);
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if(obj instanceof Room && this.roomID == ((Room) obj).getRoomID())
    //         return true;
    //     else
    //         return false; 
    // }
}