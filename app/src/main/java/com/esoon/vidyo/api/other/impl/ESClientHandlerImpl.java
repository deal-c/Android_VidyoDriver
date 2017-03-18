package com.esoon.vidyo.api.other.impl;

import com.esoon.pojo.ESMessage;
import com.esoon.vidyo.api.other.ESClientHandlerCallBackMethod;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ESClientHandlerImpl implements ESClientHandlerCallBackMethod {
    public static final int CALL_ENDED = 0;
    public static final int MSG_BOX = 1;
    public static final int CALL_RECEIVED = 2;
    public static final int CALL_STARTED = 3;
    public static final int SWITCH_CAMERA = 4;
    public static final int LOGIN_SUCCESSFUL = 5;
    public static final int LIBRARY_STARTED = 6;
    public static final int Event_Group_Chat = 7;
    public static final int Event_ShowBand = 10;
    public static final int Event_PartIn = 11;

    @Override
    public void onMessageEventConferenceActive(final ESMessage messages) {


    }

    @Override
    public void onMessageEventConferenceEnded(ESMessage messages) {

    }

    @Override
    public void onMessageEventOnOther(ESMessage messages) {

    }

    @Override
    public void onMessageEventJoinProgress(ESMessage messages) {

    }

    @Override
    public void onMessageEventReceived(ESMessage messages) {

    }

    @Override
    public void onInviteEventReceived(ESMessage message) {

    }

    @Override
    public void onPartyEventReceived(ESMessage message) {

    }
}
