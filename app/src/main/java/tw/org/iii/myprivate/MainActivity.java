package tw.org.iii.myprivate;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

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

            }if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}
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
    public  void txt1(View v){
        ContentResolver contentResolver = getContentResolver();
        // GET 共用資料
        String name =ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String num =ContactsContract.CommonDataKinds.Phone.NUMBER;
        //挑出欄位名稱
        Cursor c =contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                ,new String[]{name,num},null,null,name);
        //資料表QRERY
        while (c.moveToNext()){
           String dname = c.getString(c.getColumnIndex(name));
            String dnum = c.getString(c.getColumnIndex(num));
            Log.d("brad",dname+dnum);
        }

    }
    public  void txt2(View v){

    }
    public  void txt3(View v){

    }
}
