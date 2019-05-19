package com.davinci.client;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.WebSocket;
import io.vertx.core.streams.ReadStream;

public class Client {
  public static void main(String[] args)  {
    Vertx vertx = Vertx.vertx();
    HttpClient client = vertx.createHttpClient();
    client.websocket(8080,"127.0.0.1","/v1/davinci/game",new WsHandler());


  }
}
