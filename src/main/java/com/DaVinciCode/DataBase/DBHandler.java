package com.DaVinciCode.DataBase;

import com.DaVinciCode.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHandler{
  HashMap<String, User> userMap=new HashMap<>();

  public User create(User user){
    return userMap.put(user.getUserId(),user);
  }

  public User update(String userId,User user){
    return userMap.replace(userId,user);
  }

  public User retrieve(String userId){
    return userMap.get(userId);
  }

  public List<User> retrieveAll(){
    return new ArrayList<>(userMap.values());
  }

  public User delete(String userId){
    return userMap.remove(userId);
  }
}
