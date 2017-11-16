package collection.qzawa.com.ago_touch_second;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

//public class MainActivity extends AppCompatActivity
public class MainActivity extends Activity
        implements View.OnClickListener{  //クリックリスナーを実装

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        soundOne = soundPool.load(this, R.raw.crrect_answer, 1);


        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug","sampleId="+sampleId);
                Log.d("debug","status="+status);
            }
        });

        findViewById(R.id.button).setOnClickListener(this);  //リスナーをボタンに登録

    }

    //ボタンが押された時の処理
    public void onClick(View view){
        //ここに遷移するための処理を追加する

        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}
