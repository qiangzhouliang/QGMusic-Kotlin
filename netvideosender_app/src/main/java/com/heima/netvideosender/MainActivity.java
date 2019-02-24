package com.heima.netvideosender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onclick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("http://hc.yinyuetai.com/uploads/videos/common/3F25015E4B9FF1B6A2D9B96662BCE556.mp4?sc=be6730ab286873b7&br=775&rd=Android"), "video/mp4");
        startActivity(intent);
    }

}
