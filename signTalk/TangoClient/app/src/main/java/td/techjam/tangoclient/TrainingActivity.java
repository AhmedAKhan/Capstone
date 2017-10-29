package td.techjam.tangoclient;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

public class TrainingActivity extends FragmentActivity implements LettersFragment.LetterItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        if (findViewById(R.id.frame_letters) != null) {
            Fragment lettersFragment = new LettersFragment();
            getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_letters, lettersFragment)
                .commit();
        }
    }

    @Override
    public void onLetterClicked(String letter) {
        Toast.makeText(this, String.format("Letter %s clicked", letter), Toast.LENGTH_SHORT)
            .show();
    }
}
