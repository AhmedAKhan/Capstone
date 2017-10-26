package td.techjam.tangoclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RestService restService;
    private Button btnTest;
    private TextView tvResponse;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restService = new RestService();

        btnTest = (Button) findViewById(R.id.btn_test);
        tvResponse = (TextView) findViewById(R.id.tv_response);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTestRestCall();
            }
        });
    }

    private void sendTestRestCall() {
        restService.getTest(new TestCallback(progressBar));

//        restService.getTest(new Callback<String>() {
//
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                tvResponse.setText(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                tvResponse.setText("Failed");
//                t.printStackTrace();
//            }
//        });

    }

    class TestCallback extends ServiceCallback<String> {

        TestCallback(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        void onSuccessfulResponse(String response) {
            tvResponse.setText(response);
        }

        @Override
        void onFailure(String response) {
            tvResponse.setText(response);
        }
    }
}
