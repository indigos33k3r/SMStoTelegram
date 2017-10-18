package com.tokinonagare.smstotelegram.receiver;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.tokinonagare.smstotelegram.model.MessageSend;
import com.tokinonagare.smstotelegram.service.ReceiverService;

/**
 * 监听来电
 * Created by tokinonagare on 12/10/2016.
 */

public class CallReceiver  {

    private Context context;
    private MessageSend messageSend;

    public CallReceiver(Context context) {
        this.context = context;
        messageSend = new MessageSend(context);
    }

    public void getCallFromPhone() {
        MyPhoneSateListener myPhoneSateListener = new MyPhoneSateListener();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(myPhoneSateListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    private class MyPhoneSateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
           super.onCallStateChanged(state, incomingNumber);
            // 当有新来电时发送该来电信息
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                String message = "新来电! " + incomingNumber;
                messageSend.sendMessage(message);
            }
        }
    }
}
