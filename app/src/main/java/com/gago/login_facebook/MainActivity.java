package com.gago.login_facebook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private String user, pass, nombre;

    TextInputEditText edUsuario, edPass;
    Button btRegistrarse, btlogueo;
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        callbackManager = CallbackManager.Factory.create();

        edUsuario = findViewById(R.id.edUsuario);
        edPass = findViewById(R.id.edPass);
        btlogueo = findViewById(R.id.btLogin);
        btRegistrarse = findViewById(R.id.btRegistrarse);
        loginButton = findViewById(R.id.login_button);

        Intent i = getIntent();
        Log.i("intent", i.toString());
        Bundle retorno = i.getExtras() == null ? new Bundle() : i.getExtras();
        user = retorno.getString("usuario", "");
        nombre = retorno.getString("nombre", "");
        pass = retorno.getString("password", "");


        btlogueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
                    if (!TextUtils.isEmpty(edUsuario.getText()) && !TextUtils.isEmpty(edPass.getText())) {
                        if (user.contentEquals(edUsuario.getText()) && pass.contentEquals(edPass.getText())) {
                            Toast.makeText(getApplicationContext(), "usuario correcto bienvenido señor " + nombre, Toast.LENGTH_LONG).show();
                            Log.i("usuarioCorrecto", "correcto");
                        } else {
                            Toast.makeText(getApplicationContext(), "usuario incorrecto", Toast.LENGTH_LONG).show();
                            Log.e("usuarioIncorrecto", "incorrecto");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "escribe un usuario y contraseña", Toast.LENGTH_LONG).show();
                        Log.e("no digitado", "no digitado");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "tienes que registrarte primero", Toast.LENGTH_LONG).show();
                    Log.e("no registrado", "no registrado");
                }
            }
        });

        loginButton.setPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLogFace = accessToken != null && !accessToken.isExpired();
                if (isLogFace) {
                    Toast.makeText(getApplicationContext(), "logueado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });


        /*if (isLogFace){
            Bundle params=new Bundle();
            params.putString("name","email");

            GraphRequest.Callback callback=new GetUs
            GraphRequest request=new GraphRequest(accessToken,"/me",params, HttpMethod.GET,)
        }*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
