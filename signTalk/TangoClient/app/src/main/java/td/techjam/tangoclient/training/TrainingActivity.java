package td.techjam.tangoclient.training;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import td.techjam.tangoclient.R;

public class TrainingActivity extends FragmentActivity {
    private static final String TAG = TrainingActivity.class.getSimpleName();
    public static final String LETTER = "LETTER";

    @BindView(R.id.tv_letter)
    TextView tvLetter;

    private String letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey(LETTER)) {
            Log.e(TAG, "Missing letter extra");
        } else {
            letter = extras.getString(LETTER);
            initView();
        }
    }

    private void initView() {
        tvLetter.setText(letter);
    }
}
