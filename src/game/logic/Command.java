package game.logic;

import java.util.Arrays;

public class Command {
    private String commandString;
    private String commandWord;
    private String[] commandArguments;
    private CommandType commandType;

    public Command() {}

    public Command(String commandString) {
        this.commandString = commandString;
        parseCommandString();
    }

    private void parseCommandString() {
        String[] strs = commandString.split(" ");
        commandWord = strs[0];
        commandType = Command.determineCommandType(commandWord);
        commandArguments = Arrays.copyOfRange(strs, 1, strs.length);
    }

    private static CommandType determineCommandType(String commandWord) {
        if(commandWord.equalsIgnoreCase("go")) return CommandType.GOTO;
        else if(commandWord.equalsIgnoreCase("quit")) return CommandType.QUIT;
        else if(commandWord.equalsIgnoreCase("help")) return CommandType.HELP;
        else if(commandWord.equalsIgnoreCase("pickup")) return CommandType.PICKUP;
        else if(commandWord.equalsIgnoreCase("putdown")) return CommandType.PUTDOWN;
        else if(commandWord.equalsIgnoreCase("inventory")) return CommandType.INVENTORY;
        else return CommandType.UNDEFINED;
    }

    /**
     * @return the commandArguments
     */
    public String[] getCommandArguments() {
        return commandArguments;
    }

    /**
     * @return the commandType
     */
    public CommandType getCommandType() {
        return commandType;
    }
}