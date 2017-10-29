package td.techjam.tangoclient;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DashboardActivity extends BaseNetworkActivity {

    private TextView tvTestResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvTestResponse = (TextView) findViewById(R.id.tv_test_response);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    int getProgressBarId() {
        return R.id.dashboard_progress_bar;
    }

    public void onTrainClicked(View view) {
        NavigationManager.startTrainingActivity(this);
    }

    public void onOfflineClicked(View view) {

    }

    public void onOnlineClicked(View view) {

    }

    public void onTestClicked(View view) {
        RestService restService = new RestService();
        restService.getTest(new TestCallback(progressBar));
    }

    class TestCallback extends ServiceCallback<String> {

        TestCallback(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        void onSuccessfulResponse(String response) {
            tvTestResponse.setText(response);
        }

        @Override
        void onFailure(String response) {
            tvTestResponse.setText(response);
        }
    }
}
