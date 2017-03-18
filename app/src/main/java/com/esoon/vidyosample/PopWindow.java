package com.esoon.vidyosample;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.esoon.R;

public class PopWindow extends PopupWindow {

    private View.OnClickListener listener;
    private View view;
    private boolean clickOutSideClose = true;

    public PopWindow(Context mContext, View parent, int resid) {
        super(mContext);
        view = View
                .inflate(mContext, resid, null);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x44000000));
        this.setAnimationStyle(R.style.popwin_anim_style);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        update();
        setClickDismiss();
    }

    public void clickOutSideClose(boolean b){
        clickOutSideClose = b;
    }
    public void setClickDismiss(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(clickOutSideClose)
                    dismiss();
            }
        });
    }

    public View getView(){
        return view;
    }
}