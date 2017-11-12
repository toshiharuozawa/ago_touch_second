package collection.qzawa.com.ago_touch_second;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

//public class MainActivity extends AppCompatActivity
 public class MainActivity extends Activity
        implements View.OnClickListener{  //クリックリスナーを実装

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);  //リスナーをボタンに登録

    }

    //ボタンが押された時の処理
    public void onClick(View view){
        //ここに遷移するための処理を追加する

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}
