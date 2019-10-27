package uk.ivanc.archi;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.InputStream;

import java.security.spec.ECField;

public class login extends Activity {

    Button login;
    EditText username, password;
    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);

//        loadJson();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("user.activity_main");
                    startActivity(intent);
                } else {
                    if (counter == 0) {
                        login.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Account locked after 3 many failed attempts", Toast.LENGTH_SHORT).show();
                    } else {
                        counter--;
                        Toast.makeText(getApplicationContext(), "Wrong credentials, " + counter + " attempts remaining", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void loadJson() {

        try {
            InputStream is = getAssets().open("res.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read();
            is.close();
            String json = new String(buffer);
            System.out.println(json);
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); ++i) {
                String des = arr.getJSONObject(i).getString("Description");
                String name = arr.getJSONObject(i).getString("itemName");
                System.out.println(des + name);
            }

        } catch (JSONException err) {
            Log.e("Error", err.toString());
        } catch (Exception ex) {

        }
    }
}