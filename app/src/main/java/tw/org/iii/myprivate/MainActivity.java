package tw.org.iii.myprivate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager tmgr ;
            //system sever


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Here, thisActivity is the current activity
        if(Build.VERSION.SDK_INT>=23){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}
                    ,1);

        }if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS}
                        ,1);

            }
        tmgr =(TelephonyManager)getSystemService(TELECOM_SERVICE);
            String linenum = tmgr.getLine1Number();
            String imei =tmgr.getDeviceId();
            String imsi =tmgr.getSubscriberId();
            if(linenum!=null){
                Log.d("brad",linenum);
            }

            Log.d("brad",imei);
            Log.d("brad",imsi);
    }}
    public  void txt1(){

    }
    public  void txt2(){

    }
    public  void txt3(){

    }
}
