package com.krt.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.krt.app.model.DataResponse;
import com.krt.app.model.Game;
import com.krt.app.model.LocationResponse;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rc_game_list)
    public RecyclerView recyclerview_game_list;
    @BindView(R.id.register_btn)
    public ImageView registerBtn;
    @BindView(R.id.promo_btn)
    public ImageView promoBtn;
    @BindView(R.id.chat_btn)
    public ImageView chatBtn;
    @BindView(R.id.telegram_btn)
    public ImageView telegramBtn;
    MediaPlayer mPlayer;

//    private FirebaseAnalytics mFirebaseAnalytics;

    int lastLength = 0;
    int isPlaying = 1;
    private GameListAdapter gameListAdapter;
    DataResponse dataResponse;
    LocationResponse locationResponse;

    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    private final String prefKey = "checkedInstallReferrer";


    @Override
    public void onResume() {
        super.onResume();
        if(isPlaying == 0){
            mPlayer.seekTo(lastLength);
            mPlayer.start();
        }
        isPlaying = 1;

    }


    // Tracker for Classic GA (call this if you are using Classic GA only)
//    private void trackInstallReferrer(final String referrerUrl) {
//        new Handler(getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                CampaignTrackingReceiver receiver = new CampaignTrackingReceiver();
//                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
//                intent.putExtra("referrer", referrerUrl);
//                receiver.onReceive(getApplicationContext(), intent);
//            }
//        });
//    }
//
//    // Tracker for GTM + Classic GA (call this if you are using GTM + Classic GA only)
//    private void trackInstallReferrerforGTM(final String referrerUrl) {
//        new Handler(getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                InstallReferrerReceiver receiver = new InstallReferrerReceiver();
//                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
//                intent.putExtra("referrer", referrerUrl);
//                receiver.onReceive(getApplicationContext(), intent);
//            }
//        });
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPlayer.isPlaying()){
            isPlaying = 0;
            mPlayer.pause();
            lastLength=mPlayer.getCurrentPosition();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.bg_music);
        mPlayer.setLooping(true);
        mPlayer.start();
        dataResponse = (DataResponse) getIntent().getParcelableExtra("data");
        locationResponse = (LocationResponse) getIntent().getParcelableExtra("dataLocation");

        initView();

//        DataPresenter dataPresenter = new DataPresenter(this);
//        dataPresenter.getData();
    }

    private void initView(){
//        List<Game>  gameList = new ArrayList<>();
//        gameList.add(new Game("asda","asda","Fruit Rainbow"));
//        gameList.add(new Game("asda","asda","Fruit Rainbow"));
//        gameList.add(new Game("asda","asda","Fruit Rainbow"));
//        gameList.add(new Game("asda","asda","Fruit Rainbow"));
//        gameList.add(new Game("asda","asda","Fruit Rainbow"));

//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if(dataResponse.isOffIndicator()&&!locationResponse.getCountry_code().equals("ID")){
            registerBtn.setVisibility(View.GONE);
            promoBtn.setVisibility(View.GONE);
        }
        telegramBtn.setVisibility(View.GONE);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("button_name", "whatsapp");
//                mFirebaseAnalytics.logEvent("register_clicked_slot", params);
                String url = "https://172.104.172.170/mobile/register";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        telegramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("button_name", "whatsapp");
//                mFirebaseAnalytics.logEvent("telegram_clicked_slot", params);
                String url = "https://t.me/joinchat/1WQThp3HBME2MGJl";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        promoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("button_name", "whatsapp");
//                mFirebaseAnalytics.logEvent("promotion_clicked_slot", params);
                String url = "https://172.104.172.170/mobile/promotion";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        if(displayMetrics.heightPixels>dpToPx(255)){
            height = dpToPx(255);
        }
        int width = displayMetrics.widthPixels;
        gameListAdapter = new GameListAdapter(dataResponse.getData(),this);
        recyclerview_game_list.setLayoutManager(new LinearLayoutManager(
                MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false));
        recyclerview_game_list.setNestedScrollingEnabled(false);
        recyclerview_game_list.getLayoutParams().height = height;
        recyclerview_game_list.getLayoutParams().width = width;
        recyclerview_game_list.requestLayout();


        recyclerview_game_list.setAdapter(gameListAdapter);

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("button_name", "whatsapp");
//                mFirebaseAnalytics.logEvent("whatsapp_clicked_slot", params);
                boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                if (isWhatsappInstalled) {
                    String phoneNumberWithCountryCode = "+6287891087788";
                    String message = "";
                    startActivity(
                            new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(
                                            String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                                    )
                            )
                    );

                } else {
                    Toast.makeText(MainActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onResponse(List<Game> games){
        gameListAdapter.setData(games);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
