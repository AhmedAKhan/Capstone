package td.techjam.tangoclient;

import android.content.Context;
import android.content.Intent;

import td.techjam.tangoclient.training.LettersActivity;

public class NavigationManager {

    public static void startTraining(Context context) {
        context.startActivity(new Intent(context, LettersActivity.class));
    }
}
