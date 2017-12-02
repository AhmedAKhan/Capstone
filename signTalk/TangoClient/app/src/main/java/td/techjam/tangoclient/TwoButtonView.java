package td.techjam.tangoclient;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoButtonView extends LinearLayout {

    public enum STATE {
        SINGLE_BUTTON, DUAL_BUTTON
    }

    @BindView(R.id.btn_left)
    TextView leftButton;

    @BindView(R.id.btn_right)
    TextView rightButton;

    private TwoButtonClickListener twoButtonClickListener;
    private STATE state = STATE.SINGLE_BUTTON;

    public TwoButtonView(Context context) {
        super(context);
        initView(context);
    }

    public TwoButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TwoButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            View view = inflater.inflate(R.layout.view_two_button, this);
            ButterKnife.bind(view);

            leftButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (twoButtonClickListener != null) {
                        twoButtonClickListener.leftButtonClicked();
                    }
                }
            });

            rightButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (twoButtonClickListener != null) {
                        twoButtonClickListener.rightButtonClicked();
                    }
                }
            });
        }
    }

    public void setTwoButtonClickListener(TwoButtonClickListener twoButtonClickListener) {
        this.twoButtonClickListener = twoButtonClickListener;
    }

    public void setLeftColor(@ColorRes int color) {
        leftButton.setBackgroundColor(getResources().getColor(color));
    }

    public void setRightColor(@ColorRes int color) {
        rightButton.setBackgroundColor(getResources().getColor(color));
    }

    public void setOneButton(String text) {
        state = STATE.SINGLE_BUTTON;
        leftButton.setText(text);
        rightButton.setVisibility(GONE);
    }

    public void setTwoButton(String textLeft, String textRight) {
        state = STATE.DUAL_BUTTON;
        leftButton.setText(textLeft);
        rightButton.setVisibility(VISIBLE);
        rightButton.setText(textRight);
    }

    public STATE getState() {
        return state;
    }

    public interface TwoButtonClickListener {
        void leftButtonClicked();
        void rightButtonClicked();
    }
}
