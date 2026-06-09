package com.android.internal.hardware.rogtouchengine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Process suProcess = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

            os.writeBytes("setprop windowscheduling.touch_latency_boost 1\n");
            os.writeBytes("setprop view.touch_slop 2\n");
            os.writeBytes("setprop debug.performance.tuning 1\n");
            
            os.writeBytes("exit\n");
            os.flush();
            os.close();
            suProcess.waitFor();
            Toast.makeText(this, "ROG Touch Engine: Применено!", Toast.LENGTH_SHORT).show();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }
}

