package com.example.user.mvpapp.ui.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.mvpapp.R;
import com.example.user.mvpapp.di.component.ActivityComponent;
import com.example.user.mvpapp.ui.common.BaseFragment;

import javax.inject.Inject;


/**
 * Created by user on 01.09.2017.
 */

public class InfoFragment extends BaseFragment implements InfoMvpView {
    @Inject
    InfoMvpPresenter<InfoMvpView> mPresenter;

    View infoView;
    public static final String TAG = "InfoFragment";

    public static final String LOG_TAG = InfoFragment.class.getSimpleName();

    public static InfoFragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        infoView = inflater.inflate(R.layout.fragment_info, container, false);
        WebView webView = (WebView) infoView.findViewById(R.id.webView);
        String url = getResources().getString(R.string.enetturl);
        webView.loadUrl(url);
        ProgressBar progressBar = (ProgressBar) infoView.findViewById(R.id.progressBar);
        progressBar.setAlpha(1);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView v, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                ((ProgressBar) infoView.findViewById(R.id.progressBar)).setProgress(progress);
                if (progress == 100) {
                    ((ProgressBar) infoView.findViewById(R.id.progressBar)).setAlpha(0);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Connection error", Toast.LENGTH_SHORT).show();
            }
        });


        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttached(this);
        }

        return infoView;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
