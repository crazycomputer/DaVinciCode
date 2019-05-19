package com.davinci.code.game.model;

import com.davinci.code.game.util.RandomUtil;
import com.davinci.code.user.model.User;
import sun.awt.image.ImageWatched;

import java.util.*;

public class Room {
  private HashMap<String, LinkedList<Card>> userCards = new HashMap<>();
  private LinkedList<Card> cards = new LinkedList<>();

  public void addUser(String UserId) {
    userCards.put(UserId, new LinkedList<>());
  }

  public void dealCards() {
    int[] sequence = RandomUtil.randomArray(0, Constants.CARDS_COUNT-1, Constants.CARDS_COUNT);
    for (int i=0;i<26;i++){
      cards.add(Constants.ALL_CARDS.get(sequence[i]));
    }

  }


}
