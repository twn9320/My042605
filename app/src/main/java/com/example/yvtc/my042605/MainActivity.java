package com.example.yvtc.my042605;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String outFilename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outFilename = getFilesDir().getAbsolutePath()+ File.separator+"phone.sqlite";
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click1(View v){
        SQLiteDatabase db;
        db= SQLiteDatabase.openDatabase(outFilename,null,MODE_PRIVATE);
        Cursor c = db.rawQuery("Select * from students where Name=?",new String[]{"dd"});
        c.moveToFirst();
        String n = c.getString(1);
        Toast.makeText(MainActivity.this,n,Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void click2(View v){
        SQLiteDatabase db;
        db= SQLiteDatabase.openDatabase(outFilename,null,MODE_PRIVATE);

        //db.execSQL("insert into students (Name, Addr, Tel) Values ('dd', '4545', '5566')");
        db.execSQL("insert into students (Name, Addr, Tel) Values (?, ?,?)", new Object[] {"ee", "5555", "556565"});
        Toast.makeText(MainActivity.this,"finish",Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void copyDatabase() throws IOException{
        OutputStream outputDatabase = new FileOutputStream(outFilename);
        byte[] buffer = new byte[1024];
        InputStream is  = getResources().openRawResource(R.raw.phone);
        int length;
        while ((length=is.read(buffer))>0){
            outputDatabase.write(buffer,0,length);
        }
        is.close();
        outputDatabase.flush();
        outputDatabase.close();
    }
}
