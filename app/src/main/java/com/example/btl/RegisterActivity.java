package com.example.btl;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper1 DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.usernameDangKi);
        password = (EditText) findViewById(R.id.passwordDangKi);
        repassword = (EditText) findViewById(R.id.repasswordDangKi);
        signup = (Button) findViewById(R.id.btnsignupDangKi);
        signin = (Button) findViewById(R.id.btnsigninDangKi);
        DB = new DBHelper1(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Hãy điền hết các ô trống !", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Đăng kí thành công ! ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Đăng kí thất bại !", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại !", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không trùng khớp !", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
