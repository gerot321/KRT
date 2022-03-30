package com.slt.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.slt.app.model.Game;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Getter;


public class GameListAdapter extends RecyclerView.Adapter {
    List<Game> machineLists;
    private Context mContext;
    @Getter
    private final int VIEWTYPE_ITEM = 1;
    @Getter
    private final int VIEWTYPE_LOADER = 2;
    private boolean showLoader  = true;
    public GameListAdapter(List<Game> machineLists, Context context) {
        this.machineLists = machineLists;
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_item, parent, false);
        return new MachineListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder  holders, int position) {
        MachineListViewHolder holder = (MachineListViewHolder) holders;
        Game machine = machineLists.get(position);
//        holder.bind(position);
        holder.roomTitle.setText(machine.getName());
//        Bitmap imageTwo= BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sampel_image);
//
//        Picasso.get().load("http://museion.net/images/"+machine.getImageName()).resize(100,100).centerCrop().placeholder(R.drawable.place_holder).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                // loaded bitmap is here (bitmap)
//                holder.roomImage.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//                Bitmap imageTwo= BitmapFactory.decodeResource(mContext.getResources(), R.drawable.place_holder);
//
//                holder.roomImage.setImageBitmap(imageTwo);
//            }
//        });
        Glide.with(mContext)
                .asBitmap()
                .load("https://sltonline.s3.ap-southeast-1.amazonaws.com/asset_image_slot/"+machine.getImageName())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.roomImage.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.d("haha","haha");
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        Bitmap imageTwo= BitmapFactory.decodeResource(mContext.getResources(), R.drawable.place_holder);

                        holder.roomImage.setImageBitmap(imageTwo);
                    }
                });
        holder.platBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GameActivity.class);
                intent.putExtra("webUrl",machine.getWebUrl());
//                intent.putExtra("machineCode",machineLists.get(position).getCode());
                mContext.startActivity(intent);
            }
        });

        holder.ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GameActivity.class);
                intent.putExtra("webUrl",machine.getWebUrl());
//                intent.putExtra("machineCode",machineLists.get(position).getCode());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return machineLists.size();
    }

    public void setData(List<Game> machines) {
        this.machineLists.addAll(machines);
        notifyDataSetChanged();
    }

    public class MachineListViewHolder extends RecyclerView.ViewHolder {
        private BabeTextView roomTitle;
        private ImageView roomImage;
        private ImageView platBtn;
        private RelativeLayout ll_layout;
        public MachineListViewHolder(View view) {
            super(view);
            roomTitle = itemView.findViewById(R.id.room_title);
            roomImage = itemView.findViewById(R.id.room_image);
            platBtn = itemView.findViewById(R.id.play_btn);
            ll_layout = itemView.findViewById(R.id.ll_layout);
        }
//        public void bind(final int position) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(mContext, CoinPushGame.class);
////                    intent.putExtra("mac_addr",machineLists.get(position).getMacAddr());
////                    intent.putExtra("machineCode",machineLists.get(position).getCode());
////                    mContext.startActivity(intent);
//                }
//            });
//        }
    }

    public static Bitmap drawOctagonShapedBitmap(Bitmap src, Context context) {

        Bitmap dst =  Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        //Create an output as big as the actual bitmap.
        final float scale = context.getResources().getDisplayMetrics().density;
        float height = scale * 225;
        float widht = scale * 180;
        Path p = new Path();
        float midX = height/2;     // get center of X
        float midY =  widht/2;    // get center of Y
        p.moveTo(midX, midY);
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
        p.close();
        Canvas canvas = new Canvas(dst);
        Paint mPaint = new Paint();

        BitmapShader mBitmapShader = new BitmapShader(src, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);

        canvas.drawPath(p, mPaint);

        return dst;
    }
}

