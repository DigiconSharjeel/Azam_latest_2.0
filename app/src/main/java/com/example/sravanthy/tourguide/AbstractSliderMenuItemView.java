package com.example.sravanthy.tourguide;

/**
 * Created by sandeep on 8/28/2017.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public abstract class AbstractSliderMenuItemView extends FrameLayout {

    SliderMenuItem item;

    public AbstractSliderMenuItemView(Context context) {
        super(context);
        init(context);
    }

    public AbstractSliderMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(getLayoutRes(), this);
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (item != null && item.getClickHandler() != null) {
                    item.getClickHandler().handleMenuClick();
                }
            }
        });
    }

    public abstract int getLayoutRes();

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public SliderMenuItem getItem() {
        return item;
    }

    public void setItem(SliderMenuItem item) {
        this.item = item;
        ((TextView) findViewById(R.id.sliderItemTitle)).setText(item.getTitle());
        ImageView img= (ImageView) findViewById(R.id.im);
        img.setImageResource(item.getIconImageId());

        View viewProgress = findViewById(R.id.sliderItemProgress);
        ImageView imStatus = (ImageView) findViewById(R.id.sliderItemState);

        if (viewProgress != null && imStatus != null) {

            switch (item.getStatus()) {
                case REFRESHING:
                    viewProgress.setVisibility(View.VISIBLE);
                    imStatus.setVisibility(View.GONE);
                    break;

                case ERROR_IN_REFRESH:
                    viewProgress.setVisibility(View.GONE);
                    imStatus.setVisibility(View.VISIBLE);
                    break;

                default:
                    viewProgress.setVisibility(View.GONE);
                    imStatus.setVisibility(View.GONE);
                    break;
            }
        }
    }

}

