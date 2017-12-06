package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import game.entities.Player;
import game.entities.Room;
// import game.entities.Item;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new  Scanner(System.in);
        String playerName;
        Player p = null;
        List<Room> rooms = null;
        // Room currentRoom = null;

        if(args.length < 1) {
            System.out.println("Usage: java Game /path/to/configuration/file.txt");
            System.exit(0);
        }

        rooms = initializeGame(args[0]);
        
        if(rooms == null) {
            System.out.println("Error in processing configuration file.");
            System.exit(0);
        }
        
        System.out.println("Who are you?");

        playerName = sc.nextLine();

        p = new Player(playerName);

        play(p, rooms, rooms.get(0));

        sc.close();
    }

    private static void play(Player p, List<Room> rooms, Room currentRoom) {
        boolean finished = false;
        Scanner sc = new Scanner(System.in);
        String commandString = null;

        while(!finished) {
            List<Room> destinations = null;

            System.out.println("You are in Room " + currentRoom.getRoomID() + ".");
            System.out.println("You can go to Rooms ");
            destinations = currentRoom.getAdjacentRooms();
            for(Room destination : destinations)
                System.out.print(destination.getRoomID() + " ");
            System.out.print("> ");
            commandString = sc.nextLine();
            switch(Parser.parseCommandType(commandString)) {
                case GOTO:
                    Room tempRoom = Parser.parseGotoCommand(commandString, currentRoom);
                    if(tempRoom == null) System.out.println("You can't go there.");
                    else currentRoom = tempRoom;
                    break;
                case QUIT:
                    System.out.println("Goodbye!");
                    finished = true;
                    break;
                case UNDEFINED:
                    System.out.println("Command not found.");
            }
        }
        sc.close();
    }

    private static List<Room> initializeGame(String gameConfigFile) {
        List<Room> rooms = null;
        try(BufferedReader br = new BufferedReader(new FileReader(gameConfigFile))) {
            String in = null;
            rooms = new LinkedList<Room>();

            in = br.readLine();
            if(in != null) {
                String[] roomIDs = in.split(" ");
                for(String roomID :  roomIDs)
                    rooms.add(new Room(Integer.parseInt(roomID)));
                // printRooms(rooms);
            }

            while((in = br.readLine()) != null) {
                String[] roomData = in.split(" ");
                int roomID = Integer.parseInt(roomData[0]);
                Room room = findRoom(rooms, roomID);

                for(int i = 1; i < roomData.length; i++)
                    room.addAdjacentRoom(findRoom(rooms, Integer.parseInt(roomData[i])));
            }

            // printRooms(rooms);
            return rooms;
        } catch(IOException ex) {
            System.out.println("Game configuration file not found.");
        }

        return null;
    }

    private static Room findRoom(List<Room> rooms, int roomID) {
        for(Room room : rooms)
            if(room.getRoomID() == roomID) return room;
        
        return null;
    }

    private static void printRooms(List<Room> rooms) {
        System.out.println("There are " + rooms.size() + " rooms.");
        for(Room room : rooms) {
            System.out.println("Room  ID: " + room.getRoomID());
            List<Room> adjacentRooms = room.getAdjacentRooms();
            System.out.print("Adjacent to: ");
            for(Room adjacentRoom : adjacentRooms)
                System.out.println(adjacentRoom.getRoomID() + " ");
            System.out.println();
        }
    }
}