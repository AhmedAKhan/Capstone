package td.techjam.tangoclient.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import td.techjam.tangoclient.NavigationManager;
import td.techjam.tangoclient.R;

public class LettersActivity extends AppCompatActivity implements LetterItemClickListener {

    private final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final int NUM_COLS = 4;

    @BindView(R.id.rv_letters)
    RecyclerView lettersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters);

        // Boilerplate code needed for ButterKnife to work
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        LettersAdapter lettersAdapter = new LettersAdapter(LETTERS, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_COLS);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lettersRecyclerView.setAdapter(lettersAdapter);
        lettersRecyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onLetterClicked(String letter) {
        NavigationManager.startTrainingActivity(this, letter);
    }
}
