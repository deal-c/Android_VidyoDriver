package com.esoon.vidyo.api.room;

import com.esoon.pojo.CreateRomMsg;
import com.esoon.pojo.ReturnMsg;

/**
 * Created by Administrator on 2017/2/14.
 */

public interface ESClientCreateRoom {
    ReturnMsg Createroom(CreateRomMsg createRomMsg);
}
