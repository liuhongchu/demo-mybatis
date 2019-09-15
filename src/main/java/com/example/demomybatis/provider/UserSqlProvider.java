package com.example.demomybatis.provider;

import com.example.demomybatis.entity.User;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider implements ProviderMethodResolver {

  public static String selectById (User user){
          return new SQL(){{
          SELECT (" *");
          FROM ("user")  ;
          WHERE ("id = #{id}");
      }}.toString();
  }

    public static String findByAll (){
        return new SQL(){{
            SELECT (" *");
            FROM ("user")  ;
        }}.toString();
    }





}
