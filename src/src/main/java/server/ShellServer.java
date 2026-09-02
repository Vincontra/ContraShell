package server;

import commands.Command;
import parser.ParsedCommand;

import java.util.Map;
import java.util.Scanner;
public class ShellServer {
    public static void start(Map<String, Command> commands) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("$ ");
            String line = scanner.nextLine();
            ParsedCommand command = ParsedCommand.fromInput(line);
            CommandHandler.handle(command, commands);
        }
    }
}
