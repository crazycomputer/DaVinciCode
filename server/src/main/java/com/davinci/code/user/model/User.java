package com.davinci.code.user.model;


import lombok.Value;

@Value
public class User {
  private String name;
  private String password;
  private String userId;
  private String sex;
}
