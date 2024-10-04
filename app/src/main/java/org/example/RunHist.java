package org.example;

import java.util.ArrayList;


public class RunHist extends Command {
  @Override
  public boolean execute(String[] args) {
    if (args.length == 2) {
      try {
        ShellEnv env = ShellEnv.getInstance();
        env.executeCmd(env.getHistoryVal(Integer.valueOf(args[1])));
      } catch (NumberFormatException e) {
        System.out.printf("%s: not a number\n", args[1]);
        return false;
      } catch (Exception e) {
        System.out.println("Something went wrong\n");
        return false;
      }
      return true;
    } else {
      System.out.println("^: too many arguments");
      return false;
    }
  }
}


