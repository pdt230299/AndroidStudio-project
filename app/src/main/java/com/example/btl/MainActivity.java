package com.example.btl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, url,des;
    Button insert, update, delete, view,list;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        url = findViewById(R.id.url);
        des = findViewById(R.id.des);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        list = findViewById(R.id.btnList);

        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                String urltxt = url.getText().toString();
                String desTxt = des.getText().toString();
                if(nametxt.equals("")||urltxt.equals("")||desTxt.equals("")){
                    Toast.makeText(MainActivity.this, "Hãy điền hết các ô trống !", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkinsertdata = DB.insertplanetdata(nametxt,urltxt,desTxt);
                    if(checkinsertdata == true){
                        Toast.makeText(MainActivity.this,"Thêm thành công ! ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this,"Thêm thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
                name.setText("");
                url.setText("");
                des.setText("");
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                String urltxt = url.getText().toString();
                String desTxt = des.getText().toString();

                Boolean checkupdatedata = DB.updateplanetdata(nametxt,urltxt,desTxt);
                if(checkupdatedata == true){
                    Toast.makeText(MainActivity.this," Cập nhật thành công ! ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this," Cập nhật thất bại !", Toast.LENGTH_SHORT).show();
                }
                url.setText("");
                des.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nametxt);
                if(checkdeletedata == true){
                    Toast.makeText(MainActivity.this,"Xóa thành công !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this," Xóa thất bại ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this," Trống rỗng !", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("- Tên Hành Tinh :"+res.getString(0)+"\n");
                    buffer.append("- URL :"+res.getString(1)+"\n");
                    buffer.append("- Mô tả :"+res.getString(2)+"\n");
                    buffer.append("---------------------------------------------------------"+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true); //Đặt xem hộp thoại này có thể hủy được hay không bằng phím BACK.
                builder.setTitle("DỮ LIỆU CÁC HÀNH TINH");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }
}