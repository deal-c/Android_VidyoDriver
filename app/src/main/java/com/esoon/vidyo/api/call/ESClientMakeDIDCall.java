package com.esoon.vidyo.api.call;

import com.esoon.pojo.CallingManagerMsg;
import com.esoon.pojo.ReturnMsg;

/**
 * Created by Administrator on 2017/2/16.
 */

public interface ESClientMakeDIDCall {
    ReturnMsg esclientMakeDiDCall(CallingManagerMsg callingManagerMsg);

}
