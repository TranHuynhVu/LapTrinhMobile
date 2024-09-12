package com.example.chodoidoute;

import static com.example.chodoidoute.R.id.*;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DangNhapActivity extends AppCompatActivity {
    private boolean isPasswordVisible = false;
    private EditText edtMatKhau;
    private EditText edtMSV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangnhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtMSV = findViewById(R.id.edtMSV);
        Button btnDangNhap = findViewById(R.id.btn_DangNhap);

        // sự kiện nhấp mắt
        // Lấy drawable bên phải của EditText (icon mắt)
        Drawable eye_hidden = getResources().getDrawable(R.drawable.eye_password_hidden);
        Drawable eye_show = getResources().getDrawable(R.drawable.eye_password_show);

        edtMatKhau.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Kiểm tra xem sự kiện có phải là ACTION_UP (người dùng đã nhấc ngón tay lên sau khi chạm) không
                if(event.getAction() == MotionEvent.ACTION_UP){
                    // Kiểm tra xem người dùng có chạm vào icon bên phải của EditText không
                    // getRawX() trả về tọa độ X (theo đơn vị pixel) của vị trí mà người dùng chạm vào màn hình, tính theo hệ tọa độ toàn màn hình.
                    // getRight() trả về tọa độ X của cạnh phải của EditText, tính theo hệ tọa độ của màn hình.
                    // getCompoundDrawables()[2] trả về một mảng chứa các đối tượng Drawable bên trái, trên, phải, dưới của EditText, 2 là bên phải.
                    // getBounds().width() trả về chiều rộng của Drawable.
                    if(event.getRawX() >= (edtMatKhau.getRight() - edtMatKhau.getCompoundDrawables()[2].getBounds().width()) - edtMatKhau.getPaddingRight() - edtMatKhau.getPaddingLeft()){
                        // Nếu mật khẩu đang hiển thị (isPasswordVisible = true)
                        if(isPasswordVisible){
                            edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            edtMatKhau.setCompoundDrawablesWithIntrinsicBounds(null, null, eye_hidden, null);

                        }else {
                            edtMatKhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            edtMatKhau.setCompoundDrawablesWithIntrinsicBounds(null, null, eye_show, null);
                        }
                        isPasswordVisible = !isPasswordVisible;
                        return true;
                    }
                }
                return false;
            }
        });
        // Kết thúc sự kiện nhấp mắt

        // sự kiện click quên mật khẩu
        TextView tvQuenMatKhau = findViewById(R.id.tvQuenMK);
        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tvQuenMatKhau.setBackgroundResource(R.drawable.edittext_radius);
               Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
               startActivity(intent);
            }
        });
        // kết thúc sự kiện click quên mật khẩu

        // sự kiện click vào điều khoản và điều kiện
        TextView tvDK = findViewById(R.id.tvDieuKhoanVaDieuKien);

            // Tạo SpannableString cho toàn bộ câu
        SpannableString spannableString = new SpannableString("Bằng việc đăng nhập, bạn đã đồng ý với Điều khoản & Điều kiện của chúng tôi");

            // Tạo ClickableSpan cho phần "Điều khoản & Điều kiện"
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
            // Đặt ClickableSpan cho đoạn "Điều khoản & Điều kiện"
        spannableString.setSpan(clickableSpan, 41, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // Gán SpannableString vào TextView
        tvDK.setText(spannableString);
        tvDK.setMovementMethod(android.text.method.LinkMovementMethod.getInstance()); // Để xử lý sự kiện click
        // kết thúc sự kiện click vào điều khoản và điều kiện


        // Sự kiện click đăng ký
        TextView tvDangKy = findViewById(R.id.tvDangKy);
        tvDangKy.setOnClickListener(view -> {
            Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
            startActivity(intent);
        });

        // Sự kiện click đăng nhập
//        btnDangNhap.setOnClickListener(view -> {
//            Log.d("DangNhap", "Button đăng nhập được click");
//            String msv = edtMSV.getText().toString();
//            String password = edtMatKhau.getText().toString();
//
//            if(msv.isEmpty() || password.isEmpty()){
//                Log.d("DangNhapActivity", "Thông tin không đầy đủ, hiển thị Toast");
//                Toast.makeText(DangNhapActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                Log.d("DangNhapActivity", "Thông tin không đầy đủ, hiển thị Toast");
//                return;
//            };
//            checkDangNhap(msv, password);
//        });
//
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DangNhap", "Button đăng nhập được click");
                String msv = edtMSV.getText().toString();
                String password = edtMatKhau.getText().toString();

                if(msv.isEmpty() || password.isEmpty()){
                    Log.d("DangNhapActivity", "Thông tin không đầy đủ, hiển thị Toast");
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    Log.d("DangNhapActivity", "Thông tin không đầy đủ, hiển thị Toast");
                    return;
                };
                checkDangNhap(msv, password);
            }
        });
    }

    public void checkDangNhap(String msv, String password) {
        String url = "http://192.168.32.1:8080/api/user";
        //http://localhost:8080/api/user

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", msv);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("DangNhap", "Gửi yêu cầu POST đến URL: " + url);
        Log.d("DangNhap", "Dữ liệu gửi: " + jsonObject.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("DangNhap", "Nhận phản hồi từ server: " + response.toString());
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("DangNhap", "Lỗi khi gửi yêu cầu: " + error.toString());
                        Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


}