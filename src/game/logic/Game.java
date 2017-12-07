package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import game.entities.Item;
import game.entities.Player;
import game.entities.Room;

public class Game {
    private Player player;
    private List<Room> rooms;
    private List<Item> items;
    private Room currentRoom;

    public Game(String configurationFile) {
        initializeRooms(configurationFile);
    }

    private void initializeRooms(String configurationFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(configurationFile))) {
            String line = br.readLine(); // read number of rooms
            int numberOfRooms = 0;
            int numberOfItems = 0;

            if(line != null && (numberOfRooms = Integer.parseInt(line)) > 0) {
                rooms = new LinkedList<Room>();

                // Initialize rooms with no exits
                for(int i = 0; i < numberOfRooms; i++) {
                    line = br.readLine();
                    rooms.add(new Room(i, line));
                }

                // Add exits to each room
                for(int i = 0; i < numberOfRooms; i++) {
                    Room tempRoom = null;
                    String[] strs;

                    line = br.readLine();
                    strs = line.split(" ");
                    tempRoom = findRoomByRoomStringID(strs[0]);
                    for(int j = 1; j < strs.length; j += 2)
                        tempRoom.addExit(strs[j], findRoomByRoomStringID(strs[j+1]));
                }

                line = br.readLine(); // read number of items
                if(line != null && (numberOfItems = Integer.parseInt(line)) > 0) {
                    items = new LinkedList<Item>();
                    for(int i = 0; i < numberOfItems; i++) {
                        String[] strs = br.readLine().split(" ");
                        String[] itemInfo = strs[0].split("-");
                        Room tempRoom = findRoomByRoomStringID(strs[1]);
                        Item tempItem = new Item(i, strs[0], itemInfo[0], tempRoom);

                        items.add(tempItem);
                        tempRoom.addItem(tempItem);
                    }
                } else {
                    System.out.println("Please specify a positive integer value for the number of items.");
                }


                printRooms();
                printItems();
            } else {
                System.out.println("Please specify a positive integer value for the number of rooms.");
            }

        } catch (IOException e) {
            System.out.println("Failed to initialized game: Error in configuration file.");
        } catch (NumberFormatException e) {
            System.out.println("Error processing expected integer value.");
        }
    }

    private void printItems() {
        for(Item item : items)
            System.out.println("There is a " + item.getItemType() + " in " + item.getLocation().getRoomStringID());
    }

    private void printRooms() {
        for(Room room : rooms) {
            System.out.println(room.getRoomID() + " is a " + room.getRoomType() + " and has " + room.getExits().size() + " exit(s):");
            printExits(room);
            System.out.println(room.getRoomID() + " also has the following items: ");
            printItems(room);
        }
    }

    private void printItems(Room room) {
        for(Item i : room.getItems())
            System.out.println("  " + i.getItemType());
    }

    private void printExits(Room room) {
        HashMap<String, Room> exits = room.getExits();
        for(String direction : exits.keySet())
            System.out.println("  " + direction + " towards a " + exits.get(direction).getRoomType());
    }

    private Room findRoomByRoomStringID(String roomStringID) {
        for(Room room : rooms)
            if(room.getRoomStringID().equals(roomStringID))
                return room;
        return null;
    }

    public void play() {
        int startRoomID = (int) Math.random() * rooms.size();
        String playerName;
        Scanner sc = new Scanner(System.in);
        boolean finished = false;
        String commandString;

        currentRoom = rooms.get(startRoomID);

        System.out.print("What is your name? ");
        playerName = sc.nextLine();
        player = new Player(playerName);

        System.out.println("Welcome, " + player.getName() + "!");

        do {
            Command currentCommand;

            System.out.println("You are in a " + currentRoom.getRoomType());
            System.out.println("You can go...");
            printExits(currentRoom);
            System.out.print("> ");
            commandString = sc.nextLine();
            currentCommand = new Command(commandString);

            switch(currentCommand.getCommandType()) {
                case GOTO:
                    String direction = currentCommand.getCommandArguments()[0];
                    if(!move(direction)) System.out.println("There's nowhere to go there.");
                    break;
                case QUIT:
                    System.out.println("Bye!");
                    finished = true;
                    break;
                case UNDEFINED:
                    System.out.println("You can't do that.");
                    break;
            }
        } while(!finished);

        sc.close();
    }

    private boolean move(String destination) {
        if(currentRoom.getExits().containsKey(destination)) {
            currentRoom = currentRoom.getExits().get(destination);
            return true;
        } else return false;
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage: java Game /path/to/configuration/file.txt");
            System.exit(0);
        }
        Game g = new Game(args[0]);
        g.play();
    }
}
