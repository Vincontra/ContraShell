package commands;

import parser.ParsedCommand;
import utils.CommandUtils;

import java.io.File;

public class CdCommand implements Command {
    @Override
    public void execute(ParsedCommand command) {
        if(command.args.size() > 1){
            System.out.println("cd: too many arguments");
            return;
        }
        else if(command.args.isEmpty()){
            System.setProperty("user.dir", getHomeDir());
            return;
        }
        if(command.args.getFirst().equals("~")){
            System.setProperty("user.dir", getHomeDir());
            return;
        }

        String targetPath = command.args.getFirst();
        try{
            File targetDir = CommandUtils.resolvePath(targetPath);
            if(!targetDir.exists()){
                System.out.println("cd: " + command.args.getFirst() + ": No such file or directory");
                return;
            }
            if(!targetDir.isDirectory()){
                System.out.println("cd: " + command.args.getFirst() + ": Not a directory");
                return;
            }
            System.setProperty("user.dir", targetDir.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("cd: " + command.args.getFirst() + ": " + e.getMessage());
        }
    }

    @Override
    public String type() {
        return "cd is a shell builtin";
    }

    private String getHomeDir() {
        String homeDir = System.getenv("HOME");
        if(homeDir != null && !homeDir.isEmpty()){
            return homeDir;
        }
        return System.getProperty("user.home");
    }
}
