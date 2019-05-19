package com.davinci.code.game.model;

import lombok.Data;
import lombok.Value;

@Data
public class Event {
  String userID;
  int srcPlayer;
  int dstPlayer;
  Action action;
  @Data
  public static class Action{
    String type;
    int index;
    Number number;
    boolean result;
  }

}
