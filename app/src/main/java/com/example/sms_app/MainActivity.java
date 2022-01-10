package com.example.sms_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;
    EditText Phone,Message;
    Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Phone=findViewById(R.id.phn);
        Message=findViewById(R.id.msg);
        Send=findViewById(R.id.btn);
        Send.setEnabled(false);

        if(checkPermission(Manifest.permission.SEND_SMS)){
            Send.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    public void onSend(View v){
        String Pho=Phone.getText().toString();
        String Msg=Message.getText().toString();

        if(Pho==null || Pho.length()==0 || Msg.length()==0 || Msg==null){
            return;
        }
        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(Pho, null, Msg, null, null);
            Toast.makeText(this, "Message Sucessfully sent...",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Message Not Sent", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return(check==PackageManager.PERMISSION_GRANTED);
    }
}