package td.techjam.tangoclient.training;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import td.techjam.tangoclient.R;

public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.LettersViewHolder> {

    private char[] letters;
    private LettersFragment.LetterItemClickListener letterItemClickListener;

    public LettersAdapter(char[] letters, LettersFragment.LetterItemClickListener letterItemClickListener) {
        this.letters = letters;
        this.letterItemClickListener = letterItemClickListener;
    }

    @Override
    public LettersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_letter, parent, false);
        return new LettersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LettersViewHolder holder, int position) {
        String letter = Character.toString(letters[position]);
        holder.btnLetter.setText(letter);
    }

    @Override
    public int getItemCount() {
        return letters.length;
    }

    class LettersViewHolder extends RecyclerView.ViewHolder {

        private Button btnLetter;

        LettersViewHolder(final View itemView) {
            super(itemView);

            btnLetter = (Button)itemView.findViewById(R.id.btn_letter);
            btnLetter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String letter = Character.toString(letters[getAdapterPosition()]);
                    if (letterItemClickListener != null && letter != null) {
                        letterItemClickListener.onLetterClicked(letter);
                    }
                }
            });
        }
    }
}
