package com.davinci.code;
import com.davinci.code.starter.MainVerticle;
import io.vertx.core.Vertx;

public class Application {
  public static void main(String[] args) throws Exception {
    MainVerticle mainVerticle=new MainVerticle();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(mainVerticle);
  }

}
