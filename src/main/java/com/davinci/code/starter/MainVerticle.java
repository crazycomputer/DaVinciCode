package com.davinci.code.starter;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;



public class MainVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServer server = vertx.createHttpServer();
    RouterRegister routerRegister = new RouterRegister();
    Router router = Router.router(vertx);
     // Register API
    routerRegister.init(router);
    // Start the HTTPS server
    server.requestHandler(router).listen(8080, asyncResult ->
    {
      if (asyncResult.failed()) {
        System.out.println("start server failed! " + asyncResult.cause());
      } else {
        System.out.println("start server success!");
      }
    });
  }
}
