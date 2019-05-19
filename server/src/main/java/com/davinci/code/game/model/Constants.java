package com.davinci.code.game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.davinci.code.game.model.Color.*;
import static com.davinci.code.game.model.Number.*;


public class Constants {
  private static Card[] cards = {
    new Card(BLACK, ZERO), new Card(WHITE, ZERO),
    new Card(BLACK, ONE), new Card(WHITE, ONE),
    new Card(BLACK, TWO), new Card(WHITE, TWO),
    new Card(BLACK, THREE), new Card(WHITE, THREE),
    new Card(BLACK, FOUR), new Card(WHITE, FOUR),
    new Card(BLACK, FIVE), new Card(WHITE, FIVE),
    new Card(BLACK, SIX), new Card(WHITE, SIX),
    new Card(BLACK, SEVEN), new Card(WHITE, SEVEN),
    new Card(BLACK, EIGHT), new Card(WHITE, EIGHT),
    new Card(BLACK, NINE), new Card(WHITE, NINE),
    new Card(BLACK, TEN), new Card(WHITE, TEN),
    new Card(BLACK, ELEVEN), new Card(WHITE, ELEVEN),
    new Card(BLACK, JOKER), new Card(WHITE, JOKER)
  };
  public static final List<Card> ALL_CARDS = Arrays.asList(cards);
  public static final int CARDS_COUNT = 26;

}
