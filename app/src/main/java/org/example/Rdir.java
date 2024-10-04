package org.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.io.File;
import java.util.List;

public class Rdir extends Command {

  @Override
  public boolean execute(String[] args) {
    if (args.length <= 1) {
      System.out.println("rdir: too few arguments");
      return false;
    }

    if (args.length >= 3) {
      System.out.println("rdir: too many arguments");
      return false;
    }

    if (args[1].charAt(0) != '/') {
      args[1] = '/' + args[1];
    }

    File toRemove = new File(System.getProperty("user.dir") + args[1]);
    String[] files = toRemove.list();
    if (toRemove.isDirectory()){
      for(String file: files) {
        File rmf = new File(file);
        rmf.delete();
      }
      toRemove.delete();
      return true;
    } else {
      System.out.println("rdir: not a directory");
      return false;
    }
  }
}
