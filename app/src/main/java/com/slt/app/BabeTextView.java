package com.slt.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class BabeTextView extends AppCompatTextView {
    public BabeTextView(Context context) {
        super(context);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "Montserrat-Medium.ttf");
        this.setTypeface(face);
    }

    public BabeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "Montserrat-Medium.ttf");
        this.setTypeface(face);
    }

    public BabeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "Montserrat-Medium.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }
}
