package td.techjam.tangoclient.training;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import td.techjam.tangoclient.HorizontalProgressBarView;
import td.techjam.tangoclient.R;
import td.techjam.tangoclient.TwoButtonView;

public class TrainingActivity extends FragmentActivity implements TwoButtonView.TwoButtonClickListener, TrainingView {
    private static final String TAG = TrainingActivity.class.getSimpleName();
    public static final String LETTER = "LETTER";

    @BindView(R.id.tv_letter)
    TextView tvLetter;

    @BindView(R.id.bottom_buttons)
    TwoButtonView bottomButtons;

    @BindView(R.id.progress_bar_timer)
    HorizontalProgressBarView progressBarTimer;

    private String letter;
    private TrainingPresenter presenter;
    private TimerTask timerTask;

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
        presenter = new TrainingPresenter(this);
        tvLetter.setText(letter);
        bottomButtons.setTwoButtonClickListener(this);
    }

    @Override
    public void leftButtonClicked() {
//        bottomButtons.setTwoButton("Save", "Reset");
//        bottomButtons.setLeftColor(R.color.colorSave);
//        bottomButtons.setRightColor(R.color.colorReset);
        presenter.singleButtonClicked();
    }

    @Override
    public void rightButtonClicked() {
        Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateOneButton(String text, @ColorRes int color) {
        bottomButtons.setOneButton(text);
        bottomButtons.setLeftColor(color);
    }

    @Override
    public void updateTwoButton(String textLeft, String textRight, @ColorRes int colorLeft, @ColorRes int colorRight) {
        bottomButtons.setTwoButton(textLeft, textRight);
        bottomButtons.setLeftColor(colorLeft);
        bottomButtons.setRightColor(colorRight);
    }

    @Override
    public void startTimer() {
        progressBarTimer.setStatus("Recording...");
        timerTask = new TimerTask();
        timerTask.execute();
        Log.d(TAG, "start timer");
    }

    @Override
    public void stopTimer() {
        progressBarTimer.setStatus("Cancelled");
        timerTask.cancel(true);
        progressBarTimer.setProgress(0);
        Log.d(TAG, "stop timer");
    }

    class TimerTask extends AsyncTask<Void, Integer, Void> {

        private final int TOTAL_SECONDS = 3;
        private int progress = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            while (progress < 100) {
                try {
                    Thread.sleep(TOTAL_SECONDS * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isCancelled()) {
                    publishProgress(0);
                    break;
                }

                progress++;
                Log.d(TAG, String.format("progress:%d", progress));
                publishProgress(progress);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            progressBarTimer.setProgress(progress);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            presenter.onRecordingFinished();
        }
    }
}
