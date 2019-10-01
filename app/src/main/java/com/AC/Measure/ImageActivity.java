package com.AC.Measure;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ImageActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        init();
    }

    @Override
    protected void onDestroy() { // When the activity showing the image ends is released
        super.onDestroy();
        ImageView.reset(); // 重新設定 ImageView 有關靜態部份共用的變數
    }
    private void init() {
        Bundle bundle = getIntent().getExtras();
        TabHost tabhost = getTabHost();
        Intent intent = new Intent(this, CannyActivity.class);
        intent.putExtras(bundle);
        tabhost.addTab(tabhost.newTabSpec("Canni edge detection").setIndicator("Canni edge detection").setContent(intent)); // 設定第一個 Tab

        intent = new Intent(this, BinaryActivity.class);
        intent.putExtras(bundle);
        tabhost.addTab(tabhost.newTabSpec("Binary image processing").setIndicator("Binary image processing").setContent(intent)); // 設定第二個 Tab

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView.clearPoint(); // Clear the selected point on the ImageView
                Display display = getWindowManager().getDefaultDisplay();
                // Get the screen height
                int height = display.getHeight();
                Toast toast = Toast.makeText(ImageActivity.this, "Measurement point has been cleared !", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, height / 10);
                toast.show();
            }
        });
    }
}
