package game.logic;

import java.util.List;

import game.entities.Room;

public class Parser {
    public static Commands parseCommandType(String commandString) {
        if(commandString.startsWith("go"))
            return Commands.GOTO;
        else if(commandString.startsWith("quit"))
            return Commands.QUIT;
        else
            return Commands.UNDEFINED;
    }

    public static Room parseGotoCommand(String commandString, Room currentRoom) {
        String[] commandWords = commandString.split(" ");
        List<Room> destinations = currentRoom.getAdjacentRooms();
        int commandDestination = Integer.parseInt(commandWords[1]);
        for(Room destination : destinations)
            if(commandDestination == destination.getRoomID()) return destination;
        return null;
    }
}