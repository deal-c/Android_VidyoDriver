package com.esoon.vidyo;

/**
 * Created by Administrator on 2017/2/20.
 */

public class VidyoApplication {
    static {
        System.loadLibrary("VidyoClientApp");
        System.loadLibrary("ndkVidyoSample");
    }


}
