package com.jmhd.wml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class WriteActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 49;

    private Uri imageURI;
    private ImageButton btn_save;
    private ImageButton btn_back;
    private EditText input_title;
    private EditText input_content;
    private LinearLayout image_box;
    private TextView text_write_date;
    private DateInfo dateInfo;
    private ImageView user_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // 누른 날짜 알기 위해서 DateInfo 클래스 받아옴
        Intent intent = getIntent();
        dateInfo = (DateInfo) intent.getSerializableExtra("dateInfo");

        text_write_date = (TextView) findViewById(R.id.text_write_date);
        text_write_date.setText(dateInfo.toString());

        btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(save);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(backCalendar);

        image_box = (LinearLayout) findViewById(R.id.image_box);
        image_box.setOnClickListener(addImage);

        user_image = (ImageView) findViewById(R.id.user_image);
    }

    private void getScreenSize() {

    }

    private void checkImagePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE);
        } else {
            getImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PICK_IMAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "갤러리 권한 승인 완료", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "갤러리 권한 승인 거절", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                imageURI = data.getData();
                setImage(imageURI);
            }
        }
    }

    private void setImage(Uri uri) {
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            user_image.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Button.OnClickListener addImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkImagePermission();
        }
    };

    Button.OnClickListener backCalendar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            WriteActivity.this.finish();
        }
    };

    Button.OnClickListener save = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            input_title = (EditText)findViewById(R.id.input_title);
            input_content = (EditText)findViewById(R.id.input_content);
            if(input_title == null || input_title.equals("")){
                Log.d("Onclick", "if");
            }else{
                Log.d("Onclick", "else");
                DBHelper helper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = helper.getWritableDatabase();
                db.execSQL("INSERT INTO DIARY(date, title, content) VALUES ('"+ dateInfo.toString() + "', '" + input_title.getText().toString() + "', '" + input_content.getText().toString()+"')");
                Log.d("insert", "insert");
                db.close();

                finish();

            }
        }
    };


}