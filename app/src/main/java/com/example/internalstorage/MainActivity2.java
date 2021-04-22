package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    Button save, show;
    EditText writeText;
    TextView readText;
    String fileName = "";
    String filePath = "";
    String fileContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);
        writeText = findViewById(R.id.UN);
        readText = findViewById(R.id.UP);
        fileName = "myfile.text";
        filePath = "MyFileDir";
        if (!isExternalStorageAvailableForRW()) {
            save.setEnabled(false);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readText.setText("");
                fileContent = writeText.getText().toString().trim();
                if (!fileContent.equals("")) {
                    File myExternalFile = new File(getExternalFilesDir(filePath), fileName);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myExternalFile);
                        fos.write(fileContent.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    writeText.setText("");
                    Toast.makeText(MainActivity2.this, "Information saved to SD card " + getFilesDir() + "/" + fileName, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity2.this, "Faild", Toast.LENGTH_SHORT).show();
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileReader fr = null;
                File myExternalFile = new File(getExternalFilesDir(filePath),fileName);
                StringBuilder stringBuilder= new StringBuilder();
                try {
                    fr=new FileReader(myExternalFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while (line!=null){
                        stringBuilder.append(line).append('\n');
                        line = br.readLine();

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    String fileContents ="file contents\n"+ stringBuilder.toString();
                    readText.setText(fileContents);
                }

            }
        });

    }

    private boolean isExternalStorageAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if (extStorageState.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
