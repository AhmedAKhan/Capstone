package td.techjam.tangoclient.network;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import td.techjam.tangoclient.Utils;

public abstract class ServiceCallback<T> implements Callback<T> {
    private static final String TAG = ServiceCallback.class.getSimpleName();

    private ProgressBar progressBar;

    public ServiceCallback(ProgressBar progressBar) {
        this.progressBar = progressBar;
        progressBar.setVisibility(View.GONE);
    }

    public void startLoading() {
        Utils.LogD("malik", "startLoading");
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
        Utils.LogD("malik", "stopLoading");
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        stopLoading();

        if (response.code() == 404 || response.body() == null) {
            callFailed(call, response);
        } else {
            onSuccessfulResponse(response, response.body());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        stopLoading();

        callFailed(call, null);
        t.printStackTrace();
    }

    private void callFailed(Call<T> call, Response<T> response) {
        Log.e(TAG, "Call to " + call.request().toString() + " failed");
        if (response != null) {
            Log.e(TAG, response.raw().toString());
        }
        onFailure(response);
    }

    public abstract void onSuccessfulResponse(Response response, T body);
    public abstract void onFailure(Response response);
}
