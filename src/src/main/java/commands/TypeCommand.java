package commands;

import parser.ParsedCommand;
import utils.CommandUtils;

import java.util.List;
import java.util.Map;

public class TypeCommand implements Command {
    Map<String, Command> commands;
    public TypeCommand(Map<String, Command> commands) {
        this.commands = commands;
    }
    @Override
    public void execute(ParsedCommand command) {
        for(String arg: command.args){
            if(commands.containsKey(arg)){
                String type = commands.get(arg).type();
                System.out.println(type);
            }else{
                if(!checkInPath(arg)){
                    System.out.println(arg+": not found");
                }
            }
        }
    }

    @Override
    public String type() {
        return "type is a shell builtin";
    }

    private boolean checkInPath(String arg) {
        List<String> paths = CommandUtils.checkCommandInPaths(arg);
//        System.out.println(paths);
        if(!paths.isEmpty()){
            for(String location : paths){
                System.out.println(arg +" is "+location);
            }
            return true;
        }
        return false;
    }
}
