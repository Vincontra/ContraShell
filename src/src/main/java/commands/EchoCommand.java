package commands;

import parser.ParsedCommand;

public class EchoCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {
        if(command.args.isEmpty()){
            System.out.println();
        }else{
            System.out.println(String.join(" ", command.args));
        }
    }

    @Override
    public String type() {
        return "echo is a shell builtin";
    }
}
