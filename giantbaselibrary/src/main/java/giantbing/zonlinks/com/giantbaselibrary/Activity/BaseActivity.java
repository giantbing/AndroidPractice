package giantbing.zonlinks.com.giantbaselibrary.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by P on 2017/9/15.
 */

public abstract class BaseActivity extends Activity {

    protected Handler mHandler;
    protected SharedPreferences mPrefs;


    protected abstract void initVariables();

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract void destroyView();

    protected abstract void destroyData();

    protected abstract void initClick();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initInnerData();
        initVariables();
        initView();
        initClick();
        loadData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyView();
        destroyData();
        destroyInnerData();
    }

    private void initInnerData() {
        if (mHandler == null)
            mHandler = new Handler(Looper.getMainLooper());
        if (mPrefs == null)
            mPrefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
    }

    private void destroyInnerData() {
        mHandler = null;
        mPrefs = null;
    }
//    @Override
//    public void setContentView(@LayoutRes int layoutResID) {
//        super.setContentView(layoutResID);
//        ButterKnife.bind(this);
//
//    }
//
//
//    @Override
//    public void setContentView(View view) {
//        super.setContentView(view);
//        ButterKnife.bind(this);
//    }
//
//    @Override
//    public void setContentView(View view, ViewGroup.LayoutParams params) {
//        super.setContentView(view, params);
//        ButterKnife.bind(this);
//    }
}
