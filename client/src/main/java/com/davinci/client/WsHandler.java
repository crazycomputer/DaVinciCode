package com.davinci.client;

import com.sun.javafx.scene.EnteredExitedHandler;
import io.vertx.core.Handler;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import com.davinci.code.game.model.Event;

import java.util.Scanner;

public class WsHandler implements Handler<WebSocket> {
  WebSocket webSocket;
  @Override
  public void handle(WebSocket webSocket) {
    this.webSocket = webSocket;
    new Thread(this::play).start();
    webSocket.frameHandler(frame -> {
      System.out.println("receive a frame" + frame.binaryData());
    });

  }
  private void play(){
    Scanner sc = new Scanner(System.in);
    Event.Action action=new Event.Action();
    Event event=new Event();
    while (true) {
      switch (sc.nextLine()){
        case "ENTER":

          action.setType("ENTER");

          event.setUserID("1");
          event.setAction(action);
          webSocket.writeFinalBinaryFrame(Json.encodeToBuffer(event));
          break;
        case "START":
          action.setType("START");
          event.setUserID("1");
          event.setAction(action);
          webSocket.writeFinalBinaryFrame(Json.encodeToBuffer(event));
          break;
      }
    }






  }
}
