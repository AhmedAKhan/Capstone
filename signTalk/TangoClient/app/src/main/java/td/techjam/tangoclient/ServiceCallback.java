package td.techjam.tangoclient;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ServiceCallback<T> implements Callback<T> {
    private static final String TAG = ServiceCallback.class.getSimpleName();

    private ProgressBar progressBar;

    public ServiceCallback(ProgressBar progressBar) {
        this.progressBar = progressBar;
        progressBar.setVisibility(View.GONE);
    }

    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading() {
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

    abstract void onSuccessfulResponse(Response response, T body);
    abstract void onFailure(Response response);
}
