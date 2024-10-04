package org.example;

import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;

public class Cd extends Command {

  @Override
  public boolean execute(String[] args) {
    String curr = System.getProperty("user.dir");


    if (args.length > 2) {
      System.out.println("cd: too many arguments");
      return false;
    }

    if (args.length == 2) {

      if (args[1].charAt(0) != '/' || args[1].charAt(0) != '.') {
        args[1] = '/' + args[1];
      }

      if (args[1] == "..") {
        String prev = curr.subSequence(0, curr.lastIndexOf("/")).toString();
        System.setProperty("user.dir", prev);
        return true;
      }

      if (args[1] == ".") {
        return true;
      }

      java.nio.file.Path proposed = java.nio.file.Paths.get(curr + args[1]);
      if (proposed.toFile().isDirectory()) {
        System.setProperty("user.dir", curr + args[1]);
        return true;
      } else {
        System.out.println("cd: not a directory");
        return false;
      }

    }

    System.setProperty("user.dir", "/home");
    return true;
  }
}
