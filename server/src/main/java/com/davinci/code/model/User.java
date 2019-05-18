package com.davinci.code.model;


import lombok.Value;

@Value
public class User {
  private String name;
  private String password;
  private String userId;
  private String sex;
}
