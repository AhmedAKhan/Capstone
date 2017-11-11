package td.techjam.tangoclient;

import android.content.Context;
import android.content.Intent;

import td.techjam.tangoclient.training.LettersActivity;
import td.techjam.tangoclient.training.TrainingActivity;

public class NavigationManager {

    public static void startLettersActivity(Context context) {
        context.startActivity(new Intent(context, LettersActivity.class));
    }

    public static void startTrainingActivity(Context context, String letter) {
        Intent intent = new Intent(context, TrainingActivity.class);
        intent.putExtra(TrainingActivity.LETTER, letter);
        context.startActivity(intent);
    }
}
