package collection.qzawa.com.ago_touch_second;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

//public class SecondActivity extends AppCompatActivity {
    public class SecondActivity extends Activity{

    private int mCount;
    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundOne, soundTwo;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //admobの実装
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();

        audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(2)
                .build();

        // one.wav をロードしておく
        soundOne = soundPool.load(this, R.raw.kiss1, 1);
        soundTwo = soundPool.load(this, R.raw.kiss2, 1);


        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });

    }

    public void onBackButtonClick(View view) {  // （3）
        finish();  // （4）
    }

    private void updateCountView() {
        TextView countView = (TextView) findViewById(R.id.textView1);
        countView.setText(String.valueOf(mCount));
    }

    public void plus(View view) {

        mCount++;
        updateCountView();
        if (mCount%5 == 0) {
            soundPool.play(soundTwo, 1.0f, 1.0f, 0, 0, 1);
        }else
            soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
    }

    public void clear(View view) {

        mCount = 0;
        updateCountView();

    }


}

