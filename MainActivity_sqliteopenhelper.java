package com.example.helloworld.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_sqliteopenhelper extends AppCompatActivity {
    private static final String NAME = "test.db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;
    private EditText et_name1,et_name2,et_phone,et_num;
    private ListView lv;
    private ArrayAdapter adapter;
    private List<Student> list = new ArrayList<>();
    private Integer stuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteopenhelper_main_activity);
        MyHelper myHelper = new MyHelper(this,NAME,VERSION);
        db = myHelper.getWritableDatabase();
        query();
        lv = findViewById(R.id.lv);
        et_name1 = findViewById(R.id.et_name1);
        et_phone=findViewById(R.id.et_phone);
        et_num=findViewById(R.id.et_num);
        et_name2 = findViewById(R.id.et_name2);
        adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = list.get(i);
                stuId = student.getId();
                et_name1.setText(student.getName1());
                et_num.setText(student.getNum());
                et_phone.setText(student.getPhone());
                et_name2.setText(student.getName2());
            }
        });
    }

    public void dishname(View view){
        Intent intent=new Intent(MainActivity_sqliteopenhelper.this,DishName_List.class);
        startActivity(intent);
    }

    public void add(View view){
        if (!check()){
            Toast.makeText(this,"NULL",Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put("name1",et_name1.getText().toString());
        values.put("phone",et_phone.getText().toString());
        values.put("num",et_num.getText().toString());
        values.put("name2",et_name2.getText().toString());
        db.insert("student",null,values);
        clearET();
        list.clear();
        query();
        adapter.notifyDataSetChanged();
    }
    public void update(View view){
        if (!check()){
            Toast.makeText(this,"NULL",Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put("name1",et_name1.getText().toString());
        values.put("phone",et_phone.getText().toString());
        values.put("num",et_num.getText().toString());
        values.put("name2",et_name2.getText().toString());
        db.update("student",values,"id=?",new String[]{String.valueOf(stuId)});
        clearET();
        list.clear();
        query();
        adapter.notifyDataSetChanged();
        stuId = 0;
    }
    public void delete(View view){
        if (stuId == 0){
            Toast.makeText(this,"select",Toast.LENGTH_SHORT).show();
            return;
        }
        db.delete("student","id=?",new String[]{String.valueOf(stuId)});
        list.clear();
        query();
        adapter.notifyDataSetChanged();
    }
    public void query(){
        Cursor cursor = db.query("student",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                Student student = new Student(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name1")),
                        cursor.getString(cursor.getColumnIndex("phone")),
                        cursor.getString(cursor.getColumnIndex("num")),
                        cursor.getString(cursor.getColumnIndex("name2")));
                list.add(student);
            }while(cursor.moveToNext());
        }
    }
    public boolean check(){
        if (et_name1.getText().toString().trim().equals("")
                ||et_phone.getText().toString().trim().equals("")
                ||et_num.getText().toString().trim().equals("")
                ||et_name2.getText().toString().trim().equals("")){
            return false;
        }
        return true;
    }
    public void clearET(){
        et_name1.setText("");
        et_phone.setText("");
        et_num.setText("");
        et_name2.setText("");
    }

}