package server;

import commands.Command;
import parser.ParsedCommand;
import utils.CommandUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CommandHandler {
    public static void handle(ParsedCommand command, Map<String,Command> commands) {
        if(commands.containsKey(command.command)){
            commands.get(command.command).execute(command);
        }
        else{
            List<String> commandLocations = CommandUtils.checkCommandInPaths(command.command);
            if(commandLocations.isEmpty()){
                System.out.println(command.command + ": command not found");
            }
            else{
                try{
                    int statusCode = runExecutable(command, commandLocations);
                    if(statusCode!=0){
                        System.out.println(command.command + ": command failed");
                    }
                }catch(IOException | InterruptedException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private static int runExecutable(ParsedCommand command, List<String> commandLocations) throws IOException, InterruptedException {
        if(commandLocations.size() > 1){
            System.out.println("Multiple executables found");
        }
        String executable = commandLocations.getFirst();
        List<String> args = command.args;
        File executableFile = new File(executable);
        if(!executableFile.exists() && !executableFile.canExecute()){
            System.out.println(executable + " is not executable");
            return 1;
        }

        String processName = executableFile.getName();
        ProcessBuilder processBuilder = new ProcessBuilder(processName);
        processBuilder.command().addAll(args);

        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = processBuilder.start();

        return process.waitFor();
    }
}
