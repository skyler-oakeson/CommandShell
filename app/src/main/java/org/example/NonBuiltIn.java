package org.example;
import java.lang.ProcessBuilder;
import java.io.File;

public class NonBuiltIn extends Command {
  @Override
  public boolean execute(String[] args) {
    return true;
  }
}
