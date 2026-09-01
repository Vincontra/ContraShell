package commands;

import parser.ParsedCommand;

public class PwdCommand implements Command{
    @Override
    public void execute(ParsedCommand command) {
        System.out.println(System.getProperty("user.dir"));
    }

    @Override
    public String type() {
        return "pwd is a shell builtin";
    }
}
