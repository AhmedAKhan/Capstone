package td.techjam.tangoclient.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import td.techjam.tangoclient.R;

public class LettersFragment extends Fragment {

    private final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final int NUM_COLS = 4;
    private RecyclerView lettersRecyclerView;

    public LettersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_letters, container, false);
        lettersRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_letters);

        initView();
        return rootView;
    }

    private void initView() {
        LetterItemClickListener letterItemClickListener;
        try {
            letterItemClickListener = (LetterItemClickListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                + " must implement LetterItemClickListener");
        }

        LettersAdapter lettersAdapter = new LettersAdapter(LETTERS, letterItemClickListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), NUM_COLS);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lettersRecyclerView.setAdapter(lettersAdapter);
        lettersRecyclerView.setLayoutManager(gridLayoutManager);
    }

    public interface LetterItemClickListener {
        void onLetterClicked(String letter);
    }
}
