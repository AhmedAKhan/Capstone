package td.techjam.tangoclient;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalProgressBarView extends RelativeLayout {

    @BindView(R.id.horizontal_progress_bar)
    ProgressBar horizontalProgressBar;

    @BindView(R.id.tv_status)
    TextView tvStatus;

    public HorizontalProgressBarView(Context context) {
        super(context);
        initView(context, null);
    }

    public HorizontalProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public HorizontalProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            View view = inflater.inflate(R.layout.view_progress_bar, this);

            ButterKnife.bind(view);

            if (attrs != null) {
                extractAttributes(context, attrs);
            }
        }
    }

    private void extractAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBarView);
        String status = typedArray.getString(R.styleable.HorizontalProgressBarView_status);
        tvStatus.setText(status);
        typedArray.recycle();
    }

    public void setProgress(int progress) {
        horizontalProgressBar.setProgress(progress);
    }

    public void setStatus(String status) {
        tvStatus.setText(status);
    }
}
