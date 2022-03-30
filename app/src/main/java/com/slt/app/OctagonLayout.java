package com.slt.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class OctagonLayout extends RelativeLayout {



    public OctagonLayout(Context context) {
        super(context);
    }

    public OctagonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi")
    public OctagonLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }



    @Override
    protected void onDraw(Canvas canvas) {

        Path clipPath = new Path();
        clipPath.addPath(Octagon());
        canvas.clipPath(clipPath);
        canvas.drawColor(Color.MAGENTA);

        super.onDraw(canvas);

    }


    private Path Octagon(){

        Path p = new Path();
        float midX = getWidth()/2;
        float midY = getHeight()/2;
        p.moveTo(midX, midY);
        p.lineTo(midX+150, midY+120);
        p.lineTo(midX+120, midY+150);
        p.lineTo(midX-midX-30, midY+150);
        p.lineTo(midX-midX+9, midY+120);
        p.lineTo(9, 30);
        p.lineTo(30, 9);
        p.lineTo(midX+midX-30, 9);
        p.lineTo(midX+midX-9, 30);
        p.lineTo(midX+midX-9, midY+midY-9);

        return p;

    }
}