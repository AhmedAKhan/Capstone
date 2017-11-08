package td.techjam.tangoclient;

import android.content.Context;
import android.content.Intent;

import td.techjam.tangoclient.training.TrainingActivity;

public class NavigationManager {

    public static void startTrainingActivity(Context context) {
        context.startActivity(new Intent(context, TrainingActivity.class));
    }
}
