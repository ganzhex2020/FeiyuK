package com.phi.feiyuk.view;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.phi.feiyuk.R;
import com.phi.feiyuk.view.roundedImage.RoundedImageView;


/**
 * Created by cxf on 2018/9/27.
 */

public class RatioRoundImageView extends RoundedImageView {

    private float mRatio;

    public RatioRoundImageView(Context context) {
        this(context, null);
    }

    public RatioRoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioRoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        mRatio = ta.getFloat(R.styleable.RatioImageView_ri_ratio, 1);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (widthSize * mRatio), View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
