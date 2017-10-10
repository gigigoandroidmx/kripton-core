package com.gigigo.kbase.presentation.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gigigo.kbase.R;

/**
 * Created by Omar on 7/18/17.
 */

public class SectionProgressLoaderView
        extends FrameLayout {

    private ContentLoadingProgressBar contentLoadingProgressBar;
    private TextView messageTextView;

    public SectionProgressLoaderView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SectionProgressLoaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SectionProgressLoaderView(@NonNull Context context, @Nullable AttributeSet attrs,
                                     @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SectionProgressLoaderView(@NonNull Context context, @Nullable AttributeSet attrs,
                                     @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View rootView = inflate(context, R.layout.progress_indicator_layout, null);
        contentLoadingProgressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.contentloading_progressbar);
        messageTextView = (TextView) rootView.findViewById(R.id.textview_content);
        addView(rootView);

        hide();
    }

    public void show(String message) {
        messageTextView.setText(message);

        if (message != null && !message.isEmpty()) {
            messageTextView.setVisibility(VISIBLE);
        } else {
            messageTextView.setVisibility(GONE);
        }

        setVisibility(VISIBLE);
    }

    public void updateMessage(String message) {
        if (messageTextView != null)
            messageTextView.setText(message);
    }

    public void setMessateTextSize(float size) {
        messageTextView.setTextSize(size);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public boolean isVisible() {
        return getVisibility() == VISIBLE;
    }

    public ContentLoadingProgressBar getProgressBar() {
        return contentLoadingProgressBar;
    }
}
