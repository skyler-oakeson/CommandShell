package org.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.io.File;
import java.util.List;

public class Mdir extends Command {

  @Override
  public boolean execute(String[] args) {
    if (args.length <= 1) {
      System.out.println("mdir: too few arguments");
      return false;
    }

    if (args.length >= 3) {
      System.out.println("mdir: too many arguments");
      return false;
    }

    if (args[1].charAt(0) != '/') {
      args[1] = '/' + args[1];
    }

    String curr = System.getProperty("user.dir");
    File newDir = new File(curr + args[1]);
    if (!newDir.exists()){
        newDir.mkdirs();
        return true;
    } else {
      System.out.println("mdir: directory already exists");
      return false;
    }
  }
}
