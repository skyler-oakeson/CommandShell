package org.example;
import java.io.IOException;

public abstract class Command {
  abstract boolean execute(String[] args);
}
