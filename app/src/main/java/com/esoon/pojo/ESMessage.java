package com.esoon.pojo;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ESMessage {
    private int CALL_ENDED = 0;
    private int MSG_BOX = 1;
    private int CALL_RECEIVED = 2;
    private int CALL_STARTED = 3;
    private int SWITCH_CAMERA = 4;
    private int LOGIN_SUCCESSFUL = 5;
    private int LIBRARY_STARTED = 6;
    private int Event_Group_Chat = 7;
    private int Event_ShowBand = 10;
    private int Event_PartIn = 11;


    private int SCALL_ENDED = 0;
    private int SMSG_BOX = 1;
    private int SCALL_RECEIVED = 2;
    private int SCALL_STARTED = 3;
    private int SSWITCH_CAMERA = 4;
    private int SLOGIN_SUCCESSFUL = 5;
    private int SLIBRARY_STARTED = 6;
    private int SEvent_Group_Chat = 7;
    private int SEvent_ShowBand = 10;
    private int SEvent_PartIn = 11;

    public int getCALL_ENDED() {
        return CALL_ENDED;
    }

    public void setCALL_ENDED() {
        this.CALL_ENDED = this.SCALL_ENDED;
    }

    public int getMSG_BOX() {
        return MSG_BOX;
    }

    public void setMSG_BOX() {
        this.MSG_BOX = this.SMSG_BOX;
    }

    public int getCALL_RECEIVED() {
        return CALL_RECEIVED;
    }

    public void setCALL_RECEIVED() {
        this.CALL_RECEIVED = this.SCALL_RECEIVED;
    }

    public int getCALL_STARTED() {
        return CALL_STARTED;
    }

    public void setCALL_STARTED() {
        this.CALL_STARTED =this.SCALL_STARTED;
    }

    public int getSWITCH_CAMERA() {
        return SWITCH_CAMERA;
    }

    public void setSWITCH_CAMERA() {
        this.SWITCH_CAMERA = this.SSWITCH_CAMERA;
    }

    public int getLOGIN_SUCCESSFUL() {
        return LOGIN_SUCCESSFUL;
    }

    public void setLOGIN_SUCCESSFUL() {
        this.LOGIN_SUCCESSFUL = this.SLOGIN_SUCCESSFUL;
    }

    public int getLIBRARY_STARTED() {
        return LIBRARY_STARTED;
    }

    public void setLIBRARY_STARTED() {
        this.LIBRARY_STARTED = this.SLIBRARY_STARTED;
    }

    public int getEvent_Group_Chat() {
        return Event_Group_Chat;
    }

    public void setEvent_Group_Chat() {
        Event_Group_Chat = this.SEvent_Group_Chat;
    }

    public int getEvent_ShowBand() {
        return Event_ShowBand;
    }

    public void setEvent_ShowBand() {
        Event_ShowBand = this.SEvent_ShowBand;
    }

    public int getEvent_PartIn() {
        return Event_PartIn;
    }

    public void setEvent_PartIn() {
        Event_PartIn = this.SEvent_PartIn;
    }
}
