package com.davinci.code.game.websocket;

import com.davinci.code.game.manager.RoomManager;
import com.davinci.code.game.model.Event;
import io.vertx.core.Handler;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;

import java.util.concurrent.ConcurrentHashMap;

public class WsManager {
  public static WsManager wsManager = new WsManager();

  public static WsManager getInstance() {
    return wsManager;
  }

  Logger logger = LoggerFactory.getLogger(WsManager.class);
  RoomManager rm = RoomManager.getInstance();

  private ConcurrentHashMap<String, ServerWebSocket> wsMap = new ConcurrentHashMap<>();

  public void add(String userID, ServerWebSocket webSocket) {
    wsMap.put(userID, webSocket);
    webSocket.frameHandler(frame -> {
      Event event = Json.decodeValue(frame.binaryData(), Event.class);
      rm.onEvent(userID,event);
    });
  }

  public void sendTo(String userID, Object object) {
    wsMap.get(userID).writeFinalBinaryFrame(Json.encodeToBuffer(object));
  }



}
