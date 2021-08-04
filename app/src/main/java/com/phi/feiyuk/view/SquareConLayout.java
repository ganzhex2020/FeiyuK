package com.phi.feiyuk.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.jetbrains.annotations.NotNull;

public class SquareConLayout extends ConstraintLayout {

    public SquareConLayout(@NonNull @NotNull Context context) {
        super(context);
    }

    public SquareConLayout(@NonNull @NotNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareConLayout(@NonNull @NotNull Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
