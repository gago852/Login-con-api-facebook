package com.gago.login_facebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {

    TextInputEditText edUser, edNombre, edPassword;
    Button btRegistrar, btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edUser = findViewById(R.id.edUser);
        edNombre = findViewById(R.id.edNombre);
        edPassword = findViewById(R.id.edPassword);
        btRegistrar = findViewById(R.id.btRegistrar);
        btCancelar = findViewById(R.id.btCancelar);


        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                Bundle bundle = new Bundle();

                if (!TextUtils.isEmpty(edUser.getText()) && !TextUtils.isEmpty(edNombre.getText()) && !TextUtils.isEmpty(edPassword.getText())) {
                    bundle.putString("usuario", edUser.getText().toString());
                    bundle.putString("nombre", edNombre.getText().toString());
                    bundle.putString("password", edPassword.getText().toString());
                    i.putExtras(bundle);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "llene los campos", Toast.LENGTH_LONG).show();
                    Log.e("campo vacio", "campo vacio");
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
