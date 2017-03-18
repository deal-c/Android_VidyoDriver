package com.esoon.vidyo.api.other;

import com.esoon.pojo.ESMessage;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface ESClientHandlerCallBackMethod {
    void onMessageEventConferenceActive(final ESMessage messages);
    void onMessageEventConferenceEnded(ESMessage messages);
    void onMessageEventOnOther(ESMessage messages);
    void onMessageEventJoinProgress(ESMessage messages);
    void onMessageEventReceived(ESMessage messages);
    void onInviteEventReceived(ESMessage message);
    void onPartyEventReceived(ESMessage message);
}
