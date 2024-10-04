package org.example;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.io.File;
import java.util.List;

public class Ls extends Command {

  @Override
  public boolean execute(String[] args) {
    List<String> argsList = Arrays.asList(args);
    if (argsList.contains("-l") || argsList.contains("list")) {
      return lsl();
    } else {
      return ls();
    }
  }

  private boolean ls() {
    File[] fileList = new File(System.getProperty("user.dir")).listFiles();
    for (int i = 0; i < fileList.length; i++) {
      if (!fileList[i].isHidden()) {
        System.out.printf("%s%s%s", i == 0 ? "" : " ", fileList[i].getName(), i == fileList.length - 1 ? "" : " ");
      }
    }
    System.out.println();
    return true;
  }

  private boolean lsl() {
    File[] fileList = new File(System.getProperty("user.dir")).listFiles();
    int size = 0;
    for (File file : fileList) {
      size += file.length();
    }

    System.out.printf("total %d\n", size / 1000);
    for (File file : fileList) {
      if (!file.isHidden()) {
        Boolean isDir = file.isDirectory();
        Boolean canWrite = file.canWrite();
        Boolean canExecute = file.canExecute();
        Boolean canRead = file.canRead();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm");
        String sizeString = String.valueOf(file.length());
        long lastModified = file.lastModified();
        String lastModifiedFormatted = dateFormat.format(lastModified);
        System.out.printf("%s%s%s%s %s %s %s\n",
            isDir ? "d" : "-",
            canRead ? "r" : "-",
            canWrite ? "w" : "-",
            canExecute ? "x" : "-",
            String.format("%0" + (10 - sizeString.length()) + "d", 0).replace("0", " ") + sizeString,
            lastModifiedFormatted,
            file.getName());
      }
    }
    return true;
  }
}
