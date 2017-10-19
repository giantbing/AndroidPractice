package giantbing.zonlinks.com.ipcmessenger.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.ipcmessenger.Util.MsgConstant;

public class MessengerService extends Service {

    private static class MessengerHadlel extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MsgConstant.MSG_FROM_CLIENT:
                    LogUtil.d(msg.getData().getString("msg"));
                    returnMsg(msg);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }


        }
    }

    private static void returnMsg(Message msg){
        Messenger serviceMessenger = msg.replyTo;
        Message returnData = Message.obtain(null,MsgConstant.MSG_FROM_SERVER);
        Bundle bundle = new Bundle();
        bundle.putString("value","Thanks,noreply!");
        returnData.setData(bundle);
        try {
            serviceMessenger.send(returnData);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private final Messenger serviceMessenger = new Messenger(new MessengerHadlel());

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return serviceMessenger.getBinder();
    }
}
