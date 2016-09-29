package tw.org.iii.myprivate;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager tmgr ;
    private AccountManager amgr;
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

            }if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.GET_ACCOUNTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.GET_ACCOUNTS}
                        ,1);

            }
        tmgr =(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
            String linenum = tmgr.getLine1Number();
            String imei =tmgr.getDeviceId();
            String imsi =tmgr.getSubscriberId();
            if(linenum!=null){
                Log.d("brad",linenum);
            }

            Log.d("brad",imei);
            Log.d("brad",imsi);


            amgr =(AccountManager)getSystemService(ACCOUNT_SERVICE);
            tmgr.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
            Account[] accounts =amgr.getAccounts();
            for(Account account :accounts){
                String accountName = account.name;
                String accountType = account.type;
                Log.d("brad","accountName"+accountName+"accountType"+accountType);
            }

        }}
    private  class  MyPhoneStateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            if(state == TelephonyManager.CALL_STATE_IDLE){
                //change to idel
                Log.d("brad","off");
            }else if(state == TelephonyManager.CALL_STATE_RINGING){
                Log.d("brad",incomingNumber);
            }else  if(state== TelephonyManager.CALL_STATE_OFFHOOK){
                Log.d("brad","talk");
            }
        }
    }
    public  void txt1(){

    }
    public  void txt2(){

    }
    public  void txt3(){

    }
}
