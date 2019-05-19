package com.davinci.code.game.model;

import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.util.LinkedList;

@Value

public class Player {
  String userID;
  LinkedList<CardWrap> cards = new LinkedList<>();

  @Data
  public static class  CardWrap {
    Card card;

    String state;
  }
}
