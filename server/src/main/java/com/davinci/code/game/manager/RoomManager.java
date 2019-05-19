package com.davinci.code.game.manager;

import com.davinci.code.game.model.Event;

import java.util.ArrayList;

public class RoomManager {
  private static RoomManager roomManager = new RoomManager();

  public static RoomManager getInstance() {
    return roomManager;
  }

  ArrayList<GameExcuter> rooms = new ArrayList<>();

  private RoomManager() {
    rooms.add(new GameExcuter());
  }

  public void onEvent(String userId,Event e) {
      rooms.get(0).onEvent(userId,e);
  }
}
