package td.techjam.tangoclient;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import td.techjam.tangoclient.model.GenericResponse;
import td.techjam.tangoclient.network.RestService;
import td.techjam.tangoclient.network.ServiceCallback;
import td.techjam.tangoclient.network.StubData;

public class DashboardActivity extends BaseNetworkActivity {

    @BindView(R.id.tv_test_response)
    TextView tvTestResponse;

    @BindView(R.id.rv_test_endpoints)
    RecyclerView rvTestEndpoints;

    enum TestEndPoint {
        TEST, SAVE
    }

    private RestService restService = new RestService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Boilerplate code needed for ButterKnife to work
        ButterKnife.bind(this);

        initTestEndPointsView();
    }

    private void initTestEndPointsView() {
        TestEndPointsAdapter.TestEndPointClickListener testEndPointClickListener = new
            TestEndPointsAdapter.TestEndPointClickListener() {
            @Override
            public void onClicked(TestEndPoint endpoint) {
                switch (endpoint) {
                    case TEST:
                        restService.getTest(new TestCallback<String>(progressBar));
                        break;
                    case SAVE:
                        restService.save(StubData.getSaveRequest(),
                            new TestCallback<GenericResponse>(progressBar));
                        break;
                    default:
                        // shouldn't fire
                        break;
                }
            }
        };

        TestEndPoint[] testEndPoints = new TestEndPoint[] { TestEndPoint.TEST, TestEndPoint.SAVE};
        TestEndPointsAdapter adapter = new TestEndPointsAdapter(testEndPoints, testEndPointClickListener);
        rvTestEndpoints.setHasFixedSize(true);
        rvTestEndpoints.setLayoutManager(new LinearLayoutManager(this));
        rvTestEndpoints.setAdapter(adapter);
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
        NavigationManager.startLettersActivity(this);
    }

    public void onOfflineClicked(View view) {

    }

    public void onOnlineClicked(View view) {

    }

    class TestCallback<T> extends ServiceCallback<T> {

        TestCallback(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        public void onSuccessfulResponse(Response response, T body) {
            tvTestResponse.setText(response.raw().toString());
        }

        @Override
        public void onFailure(Response response) {
            tvTestResponse.setText("Failed");
        }
    }

}
