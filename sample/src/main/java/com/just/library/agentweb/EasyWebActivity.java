package com.just.library.agentweb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWebUtils;
import com.just.library.BaseAgentWebActivity;
import com.just.library.IWebLayout;

/**
 * Created by cenxiaozhong on 2017/7/22.
 * <p>
 */
public class EasyWebActivity extends BaseAgentWebActivity {

    private TextView mTitleTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        LinearLayout mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mTitleTextView = (TextView) this.findViewById(R.id.toolbar_title);
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyWebActivity.this.finish();
//                onKeyDown(KeyEvent.KEYCODE_BACK,new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_BACK));
            }
        });

        addBGChild((FrameLayout) mAgentWeb.getWebCreator().getGroup()); // 得到 AgentWeb 最底层的控件
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setTitle(WebView view, String title) {
        mTitleTextView.setText(title);
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#ff0000");
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Nullable
    @Override
    protected String getUrl() {
        return "http://www.baidu.com";
    }

    @Nullable
    @Override
    protected IWebLayout getWebLayout() {
        return new WebLayout(this);
    }

    protected void addBGChild(FrameLayout frameLayout) {
        TextView mTextView = new TextView(frameLayout.getContext());
        mTextView.setText("技术由 baidu 提供\r\nwww.baidu.com");
        mTextView.setTextSize(16);
        mTextView.setTextColor(Color.parseColor("#727779"));
        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        frameLayout.setBackgroundColor(Color.parseColor("#272b2d"));
        FrameLayout.LayoutParams mFlp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mFlp.topMargin = AgentWebUtils.dp2px(frameLayout.getContext(), 15);
        frameLayout.addView(mTextView, 0, mFlp);
    }
}
