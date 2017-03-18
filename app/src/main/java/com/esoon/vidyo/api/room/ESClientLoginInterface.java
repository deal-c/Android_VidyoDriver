package com.esoon.vidyo.api.room;


import com.esoon.pojo.LoginMessage;

/**
 * Created by Administrator on 2017/2/9.
 */

public interface ESClientLoginInterface {
   public boolean loginMessage(LoginMessage  loginMessage);
   public boolean loginCallBack(boolean string);

}
