package com.jordy.mapclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jordy.mapclient.Fragments.MainFragment;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.btnIngresar);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        StringRequest request = new StringRequest (Request.Method.POST, "http://192.168.1.2/wsgeoclient/login.php",
                new Response.Listener<String >() {
                    @Override
                    public void onResponse(String response) {
                        // SE EJECUTA CUANDO LA CONSULTA SALIO BIEN, SIN ERRORES
                        if (response.equals("0")){
                            Toast.makeText(LoginActivity.this, "Username o Password incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            //startActivity( new Intent(LoginActivity.this, MainFragment.class));
                            /*try {
                                JSONArray jsonArray = new JSONArray();
                                // OBTENEMOS LOS DATOS QUE DEVUELVE EL SERVIDOR
                                String ci_usuario = jsonArray.getJSONObject(0).getString("ci_usuario");
                                String nombre = jsonArray.getJSONObject(0).getString("nombre");
                                String a_paterno = jsonArray.getJSONObject(0).getString("a_paterno");
                                String a_materno = jsonArray.getJSONObject(0).getString("a_materno");
                                String foto = jsonArray.getJSONObject(0).getString("foto");
                                String celular = jsonArray.getJSONObject(0).getString("celular");
                                String email = jsonArray.getJSONObject(0).getString("email");

                                Intent intent = new Intent(loginActivity.this, NavegationActivity.class);
                                intent.putExtra("ci_usuario", ci_usuario);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("ci_usuario", ci_usuario);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "No hay Conexion a Internet", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", etEmail.getText().toString());
                params.put("pass",etPass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
