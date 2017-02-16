package com.esoon.vidyo.api.room.impl;

import com.esoon.vidyo.api.room.ESClientLoginInterface;
import com.esoon.vidyo.api.room.ESClientLoginDefine;

/**
 * Created by Administrator on 2017/2/11.
 */

public class ESClientLoginDefineImpl implements ESClientLoginDefine {
    @Override
    public boolean logindefine(String   userName,String userPsd) {

        ESClientLoginInterface loginInterface=new ESClientLoginImpl();

        return loginInterface.LoginMessage(userName,userPsd);
    }
}
