package com.jmhd.wml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    private ImageButton btn_save;
    private ImageButton btn_back;
    private EditText input_title;
    private EditText input_content;
    private LinearLayout image_box;
    private TextView text_write_date;
    private DateInfo dateInfo;

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
    }

    private void checkImagePermission() {
        // 권한이 승인인지 거절인지 확인
        int permission = ContextCompat.checkSelfPermission(WriteActivity.this, Manifest.permission.CAMERA);

        if (permission == PackageManager.PERMISSION_DENIED) { // 권한이 없다면
            // 권한 요청
            ActivityCompat.requestPermissions(WriteActivity.this, new String[] {Manifest.permission.CAMERA}, 0);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "카메라 권한 승인 완료", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "카메라 권한 승인 거절", Toast.LENGTH_SHORT).show();
            }
        }
    }

    Button.OnClickListener addImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
            getMenuInflater().inflate(R.menu.image_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_camera:
//                            startCamera();
                            break;

                        case R.id.menu_gallery:
//                            getAlbum();
                    }
                    return true;
                }
            });

            popupMenu.show();
            checkImagePermission();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

                db.execSQL("INSERT INTO DIARY(date, title, content) VALUES (?, ?, ?)",
                        new String[]{String.valueOf(text_write_date), String.valueOf(input_title), String.valueOf(input_content)});
                db.close();

                finish();

            }
        }
    };


}