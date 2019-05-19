package com.davinci.code.starter;


import com.davinci.code.game.websocket.WsManager;
import com.davinci.code.user.controller.LoginController;
import com.davinci.code.user.controller.RegisterController;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.concurrent.ConcurrentHashMap;


public class MainVerticle extends AbstractVerticle {

  private LoginController loginController = new LoginController();
  private RegisterController registerController = new RegisterController();
  private WsManager wsManager = new WsManager();
//  private ConcurrentHashMap<String,ServerWebSocket> wsMap=new ConcurrentHashMap<>();

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    // Register API
    init(router);
    // Start the HTTPS server
//    server.websocketHandler(wsEventHandler);
    server.requestHandler(router).listen(8080, asyncResult ->
    {
      if (asyncResult.failed()) {
        System.out.println("start server failed! " + asyncResult.cause());
      } else {
        System.out.println("start server success!");
      }
    });
  }


  private void init(Router router) {
    router.post("/v1/user/register").handler(this::register);
    router.post("/v1/user/login").handler(this::login);
    router.post("/v1/user/logout").handler(this::logout);
    router.get("/v1/davinci/game").handler(this::wsHandle);
  }


  private void wsHandle(RoutingContext routingContext) {
    ServerWebSocket ws = routingContext.request().upgrade();
    wsManager.add(ws);
  }

  /**
   * 用户注册
   *
   * @param routingContext routingContext
   */
  private void register(RoutingContext routingContext) {
    String bodyString = routingContext.getBodyAsString();
    routingContext.vertx().executeBlocking(futrue -> {
      String result = registerController.register(bodyString);
      routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result);
      futrue.complete();
    }, asyncResult ->
    {
      if (asyncResult.failed()) {
        routingContext.fail(asyncResult.cause());
      }
    });
  }

  /**
   * 用户登陆
   *
   * @param routingContext routingContext
   */
  private void login(RoutingContext routingContext) {
    String bodyString = routingContext.getBodyAsString();
    routingContext.vertx().executeBlocking(futrue -> {
      String result = loginController.login(bodyString);
      routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result);
    }, asyncResult ->
    {
      if (asyncResult.failed()) {
        routingContext.fail(asyncResult.cause());
      }
    });
  }

  /**
   * 用户注销
   *
   * @param routingContext routingContext
   */
  private void logout(RoutingContext routingContext) {
    String bodyString = routingContext.getBodyAsString();
    routingContext.vertx().executeBlocking(futrue -> {
      String result = loginController.logOut(bodyString);
      routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result);
    }, asyncResult ->
    {
      if (asyncResult.failed()) {
        routingContext.fail(asyncResult.cause());
      }
    });
  }
}
