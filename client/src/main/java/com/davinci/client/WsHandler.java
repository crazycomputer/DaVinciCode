package com.davinci.client;

import io.vertx.core.Handler;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class WsHandler implements Handler<WebSocket> {
  WebSocket webSocket;
  @Override
  public void handle(WebSocket webSocket) {
    this.webSocket = webSocket;
    webSocket.frameHandler(frame -> {
      System.out.println("receive a frame" + frame.binaryData());
      webSocket.writeFinalTextFrame("client send");
    });

  }
}
