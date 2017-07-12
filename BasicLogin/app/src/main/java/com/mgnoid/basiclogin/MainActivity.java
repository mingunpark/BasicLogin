package com.mgnoid.basiclogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DBHelper dbHelper;
    public ArrayList<Member> memInfo;

    private Button btn_in;
    private Button btn_up;

    private EditText edt_id;
    private EditText edt_pass;

    public static Member global_mem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,SplashActivity.class));


        dbHelper = new DBHelper(getApplicationContext(), DBHelper.DBNAME,null,1);

        edt_id=(EditText)findViewById(R.id.edt_id);
        edt_pass=(EditText)findViewById(R.id.edt_pass);

        btn_up=(Button)findViewById(R.id.btn_Up);

        btn_in=(Button)findViewById(R.id.btn_In);
        btn_in.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                memInfo=dbHelper.getResult();
                String id=edt_id.getText().toString();
                String pass=edt_pass.getText().toString();
                int chk=-1;

                for(int i=0;i<memInfo.size();i++) {

                    if (id.equals(memInfo.get(i).getId()) && pass.equals(memInfo.get(i).getPassword())) {
                        chk = i;
                        break;
                    }
                }

                if(chk>=0){
                    Toast.makeText(getApplicationContext(), memInfo.get(chk).getName()+"님 환영합니다!", Toast.LENGTH_SHORT).show();

                    global_mem=memInfo.get(chk);



                    Intent in = new Intent(MainActivity.this,MainMenu.class);

                    startActivity(in);

                }
            }


        });

    }

    public void toSignUp(View v){

        Intent intent=new Intent(MainActivity.this,SignUp.class);

        startActivityForResult(intent, 1000);
    }

    public void toMain(View v){

        Intent intent=new Intent(MainActivity.this,MainMenu.class);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Member temp=new Member();

        Log.d("RESULT", requestCode+"");
        Log.d("RESULT", resultCode+"");
        Log.d("RESULT", data+"");

        if(requestCode == 1000 && resultCode==RESULT_OK){
            edt_id.setText(data.getStringExtra("id"));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        edt_id.setText("");
        edt_pass.setText("");
    }

    public ArrayList<Member> getMemInfo() {
        return memInfo;
    }

    public void setMemInfo(ArrayList<Member> memInfo) {
        this.memInfo = memInfo;
    }
}
