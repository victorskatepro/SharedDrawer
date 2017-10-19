package com.victorsaico.shareddrawer.Repositories;

import com.orm.SugarRecord;
import com.victorsaico.shareddrawer.models.User;

import java.util.List;

/**
 * Created by JARVIS on 9/10/2017.
 */

public class UserRepository {

    //public static List<User> users = new ArrayList<>();

  //  static{
     //   users.add(new User(100, "ebenites", "tecsup", "Erick Benites"));
    //    users.add(new User(200, "jfarfan", "tecsup", "Jaime Farfán"));
     //   users.add(new User(300, "drodriguez", "tecsup", "David Rodriguez"));
    //}

   public static List<User> users = SugarRecord.listAll(User.class);

   public static User login(String email, String password) {
       boolean respuesta=true;
       for (User user : users) {
           if (user.getFullname().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
               return user;
           }
       }
       return null;
   }
   public static boolean validar(String fullname){
       boolean respuesta = true;
       for (User user:users){
           if(fullname.equals(user.getFullname())){
               respuesta = true;
               return respuesta;
           }else {
               respuesta = false;
               return respuesta;
           }
       }
       return respuesta;
   }

  //  public static User getUser(String username){
    //    for (User user : users){
         //   if(user.getUsername().equalsIgnoreCase(username)){
           //     return user;
         //   }
      ///  }
     //  return null;
    //}
    //public static User agregarUsuario(String username, String password){
      //  int id=100*(users.size()+1);
       // User user = new User(id, username, password, username);
     //   users.add(user);
    //    return user;
   // }
  public static User read(Long id){
      User user = SugarRecord.findById(User.class, id);
      return user;
  }

    public static User create(String fullname, String email, String password){
        User user = new User(fullname, email, password);
        SugarRecord.save(user);
        return user;
    }
}
