package com.mgnoid.basiclogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.mgnoid.basiclogin.MainActivity.global_mem;

public class UserInfo extends AppCompatActivity {

    private Button btn_camera;
    private Button btn_back;

    private TextView txt_id;
    private TextView txt_name;
    private TextView txt_phone;

    private ImageView img_me;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btn_camera = (Button)findViewById(R.id.btn_camera);
        txt_id=(TextView)findViewById(R.id.txt_id);
        txt_name=(TextView)findViewById(R.id.txt_name);
        txt_phone=(TextView)findViewById(R.id.txt_phone);

        img_me=(ImageView)findViewById(R.id.img_me);


        btn_camera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        txt_id.setText(global_mem.getId());
        txt_name.setText(global_mem.getName());
        txt_phone.setText(global_mem.getPhone());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 1) // 1 은 위에서 startActivityForResult(intent, 1);
            {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                img_me.setImageBitmap(bm);
            }
        }

    }


}
