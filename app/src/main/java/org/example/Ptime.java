package org.example;

public class Ptime extends Command {
  @Override
  public boolean execute(String[] args) {
    System.out.println(Timer.getInstance().getElapsed());
    return true;
  }
}
