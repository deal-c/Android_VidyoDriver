package com.esoon.vidyo.api.other;

import android.app.Activity;

/**
 * Created by Administrator on 2017/3/8.
 */

public interface ESClientCallBackMsg {
    String esclientHandler(Activity activity);

    String onPartyEventReceived();

    String onInviteEventReceived();

    String onMessageEventReceived();

    String onMessageEventJoinProgress();

    String onMessageEventConferenceActive(Activity    activity);

    String onMessageEventConferenceEnded();

    String onMessageEventOnOther();


}
