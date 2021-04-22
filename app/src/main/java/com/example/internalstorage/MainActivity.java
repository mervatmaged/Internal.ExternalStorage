package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "example.txt";
    EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.UN);
    }

    public void Login(View view) {
        String text = user.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            user.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILENAME, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }


    public void Show(View view) {
        FileInputStream fis =null;
        try {
            fis=openFileInput(FILENAME);
            InputStreamReader isr =new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text= br.readLine()) != null){
                sb.append(text).append("\n");
            }
            user.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis !=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void Go(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }


}