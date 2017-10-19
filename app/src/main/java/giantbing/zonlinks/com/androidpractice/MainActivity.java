package giantbing.zonlinks.com.androidpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import giantbing.zonlinks.com.giantbaselibrary.Activity.BaseActivity;
import giantbing.zonlinks.com.giantbaselibrary.Util.StartActivityHelper;
import giantbing.zonlinks.com.ipcmessenger.Activity.MensengerActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_ipcmessenger)
    TextView mainIpcmessenger;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void destroyView() {

    }

    @Override
    protected void destroyData() {

    }

    @Override
    protected void initClick() {

        mainIpcmessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartActivityHelper.startActivityTraslate(MainActivity.this, MensengerActivity.class, StartActivityHelper.Anmotion.NONE);


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }
}
