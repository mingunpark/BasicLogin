package com.mgnoid.basiclogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.mgnoid.basiclogin.MainActivity.dbHelper;

public class SignUp extends AppCompatActivity {

    private EditText make_id;
    private EditText make_pass;
    private EditText make_name;
    private EditText make_mail;
    private EditText make_phone;

    private Button btn_cancel;
    private Button btn_comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        make_id=(EditText)findViewById(R.id.make_id);
        make_pass=(EditText)findViewById(R.id.make_pass);
        make_name=(EditText)findViewById(R.id.make_name);
        make_mail=(EditText)findViewById(R.id.make_mail);
        make_phone=(EditText)findViewById(R.id.make_phone);

        btn_cancel=(Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void sign_up(View v){

        ArrayList<Member> check=new ArrayList<Member>();
        int chk=0;
        check=dbHelper.getResult();


        String id=make_id.getText().toString();
        String pass=make_pass.getText().toString();
        String name=make_name.getText().toString();
        String mail=make_mail.getText().toString();
        String phone=make_phone.getText().toString();

        Member mem=new Member();

        mem.setId(id);
        mem.setPassword(pass);
        mem.setName(name);
        mem.setMail(mail);
        mem.setPhone(phone);



        for(int i=0; i<check.size();i++){
            if(id.equals(check.get(i).getId()) || pass.length()<4 || id.length()<2)
            {
                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                chk=1;
            }

        }
        if(chk==0) {
            dbHelper.insert(mem);

            Toast.makeText(getApplicationContext(), name + "님 회원가입을 축하드립니다!", Toast.LENGTH_SHORT).show();
        }

        Intent result=new Intent();
        result.putExtra("id", id);
        setResult(RESULT_OK,result);

        finish();

    }
}
