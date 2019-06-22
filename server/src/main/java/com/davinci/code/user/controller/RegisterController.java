package com.davinci.code.user.controller;

import com.davinci.code.user.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class RegisterController {
  public String register(String json){
   User user=Json.decodeValue(json,User.class);

      return null;
  }

  private boolean isName(String name){
    boolean result=false;
    int cnt=0;
    if(name.length()>0&&name.length()<21) {
      char[] c = name.toCharArray();
      for (int i = 0; i < c.length; i++)
        if (Character.isDigit(c[i]))
          cnt++;
        else if (Character.isLetter(c[i]))
          cnt++;
        else if (Character.isSpaceChar(c[i]))
          cnt++;
    }
    if (cnt==name.length())
      result=true;
      return result;
  }

  private boolean isPassword(String password){
    boolean result=false;
    int cnt=0;
    if(password.length()>6&&password.length()<21) {
      char[] c = password.toCharArray();
      for (int i = 0; i < c.length; i++)
        if (Character.isDigit(c[i]))
          cnt++;
        else if (Character.isLetter(c[i]))
          cnt++;
    }
    if (cnt==password.length())
      result=true;
    return result;
  }
}
