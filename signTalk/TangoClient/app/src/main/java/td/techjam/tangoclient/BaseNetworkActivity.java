package td.techjam.tangoclient;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public abstract class BaseNetworkActivity extends AppCompatActivity {

    protected ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        progressBar = (ProgressBar) findViewById(getProgressBarId());
    }

    protected void doPreNetworkCall() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void doPostNetworkCall() {
        progressBar.setVisibility(View.GONE);
    }

    abstract @LayoutRes int getLayoutId();

    abstract @IdRes int getProgressBarId();
}
