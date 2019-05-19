package com.davinci.code.game.manager;

import com.davinci.code.game.model.Card;
import com.davinci.code.game.model.Constants;
import com.davinci.code.game.model.Event;
import com.davinci.code.game.model.Player;
import com.davinci.code.game.util.RandomUtil;
import com.davinci.code.game.websocket.WsManager;


import java.util.*;

public class GameExcuter {
  private ArrayList<Player> players = new ArrayList<>();
  private LinkedList<Card> cards = new LinkedList<>();

  public void addUser(String UserId) {
    players.add(new Player(UserId));
  }

  public void start() {
    dealCards();
    players.forEach(player -> WsManager.getInstance().sendTo(player.getUserID(), player.getCards()));
  }

  public void dealCards() {
    int[] sequence = RandomUtil.randomArray(0, Constants.CARDS_COUNT - 1, Constants.CARDS_COUNT);
    for (int i = 0; i < 26; i++) {
      cards.add(Constants.ALL_CARDS.get(sequence[i]));
    }
    if (players.size() < 4) {
      for (int i = 0; i < 4; i++) {
        players.forEach(player -> {
          Player.CardWrap cw = new Player.CardWrap();
          cw.setCard(cards.poll());
          cw.setState("UP");
          player.getCards().offer(cw);
        });
      }
    } else {
      for (int i = 0; i < 3; i++) {
        players.forEach(player -> {
          Player.CardWrap cw = new Player.CardWrap();
          cw.setCard(cards.poll());
          cw.setState("UP");
          player.getCards().offer(cw);
        });
      }
    }
  }

  public void onEvent(String userId, Event e) {
    Event.Action action = e.getAction();

    switch (action.getType()) {
      case "GUESS":
        Player sp = players.get(e.getSrcPlayer());
        Player dp = players.get(e.getDstPlayer());
        if (dp.getCards().get(action.getIndex()).getCard().getNumber() == action.getNumber()) {
          dp.getCards().get(action.getIndex()).setState("DOWN");
          e.getAction().setResult(true);
        } else {
          e.getAction().setResult(false);
        }
        publish(e);
        break;
      case "START":
        start();
        break;
      case "ENTER":
        addUser(userId);
    }
  }

  public void publish(Event e) {
    players.forEach(player -> {
      WsManager.getInstance().sendTo(player.getUserID(), e);
    });
  }


}
