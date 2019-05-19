package com.davinci.code.game.websocket;

import io.vertx.core.Handler;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;

import java.util.concurrent.ConcurrentHashMap;

public class WsManager {
  Logger logger = LoggerFactory.getLogger(WsManager.class);

  private ConcurrentHashMap<String, ServerWebSocket> wsMap = new ConcurrentHashMap<>();

  public void add(ServerWebSocket webSocket) {
    wsMap.put(webSocket.textHandlerID(), webSocket);
    webSocket.frameHandler(frame -> {
      System.out.println("received a frame " + frame.textData());
      webSocket.writeFinalTextFrame("connected");
    });
  }


}
