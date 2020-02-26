package com.gago.login_facebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private String user, pass;

    TextInputEditText edUsuario, edPassword;
    Button btRegistrarse, btlogueo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edUsuario = findViewById(R.id.edUsuario);
        edPassword = findViewById(R.id.edPassword);
        btlogueo = findViewById(R.id.btLogin);
        btRegistrarse = findViewById(R.id.btRegistrarse);

        Intent i = getIntent();
        Log.i("intent", i.toString());
        Bundle retorno = i.getExtras();
        user = retorno.getString("usuario","");
        pass = retorno.getString("password","");


        btlogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                    if (!TextUtils.isEmpty(edUsuario.getText()) && !TextUtils.isEmpty(edPassword.getText())) {
                        if (user.contentEquals(edUsuario.getText()) && pass.contentEquals(edPassword.getText())) {
                            Toast.makeText(getApplicationContext(), "usuario correcto", Toast.LENGTH_LONG).show();
                            Log.i("usuarioCorrecto", "correcto");
                        } else {
                            Toast.makeText(getApplicationContext(), "usuario incorrecto", Toast.LENGTH_LONG).show();
                            Log.i("usuarioIncorrecto", "incorrecto");
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "escribe un usuario y contraseña", Toast.LENGTH_LONG).show();
                        Log.e("no digitado", "no digitado");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "tienes que registrarte primero", Toast.LENGTH_LONG).show();
                    Log.e("no registrado", "no registrado");
                }
            }
        });


        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
