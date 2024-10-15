package com.geeks.myapplication2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText1 = findViewById(R.id.first_editText);
        EditText editText2 = findViewById(R.id.second_editText);
        Button button = findViewById(R.id.button);

        button.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_grey));
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text1 = editText1.getText().toString().trim();
                String text2 = editText2.getText().toString().trim();
                if (!text1.isEmpty() && !text2.isEmpty()) {
                    button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.brown));
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.dark_grey));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        editText1.addTextChangedListener(textWatcher);
        editText2.addTextChangedListener(textWatcher);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }

        EditText editTextLogin = findViewById(R.id.first_editText);
        EditText editTextPassword = findViewById(R.id.second_editText);
        Button buttonSubmit = findViewById(R.id.button);
        TextView textView = findViewById(R.id.first_textView);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView secondText = findViewById(R.id.second_textView);

        buttonSubmit.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (login.equals("admin") && password.equals("12345")) {
                Snackbar.make(v, "Вы успешно зарегистрировались", Snackbar.LENGTH_LONG).show();

                textView.setVisibility(View.VISIBLE);
                editTextLogin.setVisibility(View.GONE);
                editTextPassword.setVisibility(View.GONE);
                buttonSubmit.setVisibility(View.GONE);
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                secondText.setVisibility(View.GONE);
            } else {
                Snackbar.make(v, "Неправильный логин и пароль", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}