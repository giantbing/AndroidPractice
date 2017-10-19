package giantbing.zonlinks.com.ipcmessenger.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.ipcmessenger.R;
import giantbing.zonlinks.com.ipcmessenger.Service.MessengerService;
import giantbing.zonlinks.com.ipcmessenger.Util.MsgConstant;

public class MensengerActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection;

    private static class GetMsgHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MsgConstant.MSG_FROM_SERVER:
                    LogUtil.d(msg.getData().getString("value"));
                    break;

                default:
                    super.handleMessage(msg);
                    break;
            }

        }
    }

    private Messenger getMsgMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensenger);
        initView();
        bindService();
    }

    @Override
    protected void onDestroy() {
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }

        super.onDestroy();
    }

    private void initView() {
        getMsgMessenger = new Messenger(new GetMsgHandle());
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                Messenger clinetMessenger = new Messenger(iBinder);
                Message msg = Message.obtain(null, MsgConstant.MSG_FROM_CLIENT);
                Bundle bundle = new Bundle();
                bundle.putString("msg", "hello there,Imclinte");
                msg.setData(bundle);
                msg.replyTo = getMsgMessenger;
                try {
                    clinetMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    private void bindService() {
        Intent intent = new Intent(MensengerActivity.this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
