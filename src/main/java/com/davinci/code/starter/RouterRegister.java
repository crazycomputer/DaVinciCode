package com.davinci.code.starter;


import com.davinci.code.controller.LoginController;
import com.davinci.code.controller.RegisterController;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.ValidationException;

public class RouterRegister {

  LoginController loginController=new LoginController();
  RegisterController registerController=new RegisterController();

  public void init(Router router) {
    // We need request bodies
    router.route().handler(BodyHandler.create());
    // We consume application/json, also produce it
    router.route().consumes("application/json").produces("application/json");
    router.post("/v1/user/register").handler(this::register).failureHandler(this::failedHandler);
    router.post("/v1/user/login").handler(this::login).failureHandler(this::failedHandler);
    router.post("/v1/user/logout").handler(this::logout).failureHandler(this::failedHandler);
  }

  /**
   * 用户注册
   *
   * @param routingContext routingContext
   */
  private void register(RoutingContext routingContext) {
    String bodyString = routingContext.getBodyAsString();
    routingContext.vertx().executeBlocking(futrue -> {
      String result=registerController.register(bodyString);
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
      String result=loginController.login(bodyString);
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
      String result=loginController.logOut(bodyString);
      routingContext.response().setStatusCode(HttpResponseStatus.OK.code()).end(result);
    }, asyncResult ->
    {
      if (asyncResult.failed()) {
        routingContext.fail(asyncResult.cause());
      }
    });
  }

  /**
   * 异常处理方法
   *
   * @param routingContext routingContext
   */
  public void failedHandler(RoutingContext routingContext) {
    HttpServerResponse response = routingContext.response();
    Throwable failure = routingContext.failure();
    if (failure instanceof ValidationException) {
      String errorMsg = failure.getMessage();
      response.setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(errorMsg);
    } else if (failure instanceof RuntimeException) {
      String errorCode = failure.getMessage();
      response.setStatusCode(Integer.parseInt(StringUtils.substring(errorCode, 0, 3)))
        .end();
    } else {
      response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end("internal error!");
    }
  }

}
