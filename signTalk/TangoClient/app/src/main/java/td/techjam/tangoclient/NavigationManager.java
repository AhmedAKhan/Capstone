package td.techjam.tangoclient;

import android.content.Context;
import android.content.Intent;

public class NavigationManager {

    public static void startTrainingActivity(Context context) {
        context.startActivity(new Intent(context, TrainingActivity.class));
    }
}
