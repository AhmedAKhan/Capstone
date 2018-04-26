package td.techjam.tangoclient.training;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import td.techjam.tangoclient.HorizontalProgressBarView;
import td.techjam.tangoclient.R;
import td.techjam.tangoclient.model.GenericResponse;
import td.techjam.tangoclient.model.SaveRequest;
import td.techjam.tangoclient.network.RestService;
import td.techjam.tangoclient.TwoButtonView;
import td.techjam.tangoclient.Utils;
import td.techjam.tangoclient.model.RGBData;
import td.techjam.tangoclient.network.ServiceCallback;

public class TrainingActivity extends FragmentActivity
    implements TwoButtonView.TwoButtonClickListener, TrainingView {
    private static final String TAG = TrainingActivity.class.getSimpleName();
    public static final String LETTER = "LETTER";

    @BindView(R.id.tv_letter)
    TextView tvLetter;

    @BindView(R.id.bottom_buttons)
    TwoButtonView bottomButtons;

    @BindView(R.id.progress_bar_timer)
    HorizontalProgressBarView progressBarTimer;

    @BindView(R.id.training_progress_bar)
    ProgressBar progressBar;

    private String letter;
    private TrainingPresenter presenter;
    private TimerTask timerTask;
    private TangoFragment tangoFragment;
    private RestService restService = new RestService();

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
            initTangoFragment(savedInstanceState);
        }
    }

    private void initView() {
        presenter = new TrainingPresenter(this, letter);
        tvLetter.setText(letter);
        bottomButtons.setTwoButtonClickListener(this);
    }

    private void initTangoFragment(Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.tango_fragment) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            tangoFragment = new TangoFragment();
            tangoFragment.setPresenter(presenter);

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                .add(R.id.tango_fragment, tangoFragment).commit();
        }
    }

    @Override
    public void leftButtonClicked() {
        TwoButtonView.STATE bottomButtonsState = bottomButtons.getState();
        if (bottomButtonsState == TwoButtonView.STATE.SINGLE_BUTTON) {
            presenter.singleButtonClicked();
        } else {
            presenter.dualButtonClicked(true);
        }
    }

    @Override
    public void rightButtonClicked() {
        presenter.dualButtonClicked(false);
    }

    @Override
    public void updateOneButton(String text, @ColorRes int color) {
        bottomButtons.setOneButton(text);
        bottomButtons.setLeftColor(color);
    }

    @Override
    public void updateTwoButton(String textLeft, String textRight,
        @ColorRes int colorLeft, @ColorRes int colorRight) {
        bottomButtons.setTwoButton(textLeft, textRight);
        bottomButtons.setLeftColor(colorLeft);
        bottomButtons.setRightColor(colorRight);
    }

    @Override
    public void startRecording() {
        Utils.LogD(TAG, "start recording");
        timerTask = new TimerTask();
        timerTask.execute();
    }

    @Override
    public void stopRecording() {
        Utils.LogD(TAG, "stop recording");
        timerTask.cancel(true);
        progressBarTimer.setProgress(0);
    }

    @Override
    public void saveRecording(SaveRequest saveRequest) {
        // TODO: Disable SAVE/RESET when saving. Change back to START after done saving
        int numFrames = saveRequest.rgb.numFrames;
//        Toast.makeText(this, String.format("Saving %d frames", numFrames),
//            Toast.LENGTH_SHORT).show();
        Utils.LogD("malik", "saving " + numFrames + " frames");
        restService.save(saveRequest, new SaveCallback<GenericResponse>(progressBar));
    }

    @Override
    public void updateRecordingStatus(String status) {
        progressBarTimer.setStatus(status);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    class TimerTask extends AsyncTask<Void, Integer, Void> {

        private final int TOTAL_SECONDS = 3;
        private int progress = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            while (progress < 100) {
                try {
                    // 1% of TOTAL_SECONDS in millis (i.e. TOTAL_SECONDS * 1000 / 100)
                    Thread.sleep(TOTAL_SECONDS * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isCancelled()) {
                    publishProgress(0);
                    break;
                }

                progress++;
                Utils.LogD(TAG, String.format("progress:%d", progress));
                publishProgress(progress);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            progressBarTimer.setProgress(progress);
            // TODO: When progress > 0, start saving data
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            presenter.onRecordingFinished();
        }
    }

    class SaveCallback<SaveRequest> extends ServiceCallback<SaveRequest> {

        public SaveCallback(ProgressBar progressBar) {
            super(progressBar);
        }

        @Override
        public void onSuccessfulResponse(Response response, SaveRequest body) {
            Toast.makeText(TrainingActivity.this, "Saved successfully",
                Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(Response response) {
            Toast.makeText(TrainingActivity.this, "Save Failed",
                Toast.LENGTH_LONG).show();

        }
    }
}
