package com.example.helloworld.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_sqliteopenhelper extends AppCompatActivity {
    private static final String NAME = "test.db";
    private static final int VERSION = 1;
    private SQLiteDatabase db;

    private Integer stuId;

    private EditText et_name1,et_name2,et_phone,et_num;
    private ListView lv;

    private ArrayAdapter adapter;
    private List<Student> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteopenhelper_main_activity);
        lv = findViewById(R.id.lv);
        et_name1 = findViewById(R.id.et_name1);
        et_phone=findViewById(R.id.et_phone);
        et_num=findViewById(R.id.et_num);
        et_name2 = findViewById(R.id.et_name2);

        MyHelper myHelper = new MyHelper(this,NAME,VERSION);
        db = myHelper.getWritableDatabase();//创建或打开数据库，如果数据库不存在则创建数据库，若存在则打开

        query();

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

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showpopupwindow(view);
                stuId = 0;
                return true;//如果不写true写false，他先会触发长按事件再触发短按事件，写true就不会触发短按事件
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
            return;//当他无输入时点击add按钮时，弹出提示，然后return，就是什么都没做直接跳出add方法
        }
        //Toast.makeText(this,String.valueOf(stuId),Toast.LENGTH_SHORT).show();//测试性代码

        ContentValues values = new ContentValues();//用来存储value
        values.put("name1",et_name1.getText().toString());//put（插入的字段名，插入的值）
        values.put("phone",et_phone.getText().toString());
        values.put("num",et_num.getText().toString());
        values.put("name2",et_name2.getText().toString());
        db.insert("student",null,values);//这里返回一个long类型的值表示插入数据的列数，需要时可以定义一个变量来接收
        clearET();
        list.clear();//清除list里面内容
        query();//里面其中有步是添加内容，是重新添加
        adapter.notifyDataSetChanged();//更新ListView
        stuId = 0;
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

        //update（表名，键为String类型的hashmap，修改条件，修改条件的占位符）
        //这是修改条件不知道，用？先占位，然后后面用一个字符串类型数组把修改的值写过来
        //String.valueOF()是将基本数据类型转换成String
        db.update("student",values,"id=?",new String[]{String.valueOf(stuId)});//同样，他也会返回一个int类型的数据表示修改的条数，需要时可以定义一个变量来接收
        /**
         * 这是直接把值写过来
         *         db.update("student",values,"id=1",null);
         */
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

    public void showpopupwindow(View view){
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow,null);//这是将xml文件转成View
        TextView tv_delete=contentView.findViewById(R.id.tv_delete);
        PopupWindow popupWindow=new PopupWindow();
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 点击长按弹出的tv_delete触发删除的逻辑代码
                 */
            }
        });
        popupWindow.setContentView(contentView);
        //设置popupwindow宽高
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setOutsideTouchable(true);//设置点击外部popupwindow是否消失
        popupWindow.setFocusable(true);//设置是否获取焦点，比如true时点击返回键先回到activity，不会退出app

        int[] location = new int[2];
        //获取控件在屏幕的位置的x,y坐标,使用的时候需要传入一个int [] 型的参数，int[0]位x轴 同样也可以视为矩形的Left边界int[1]为y轴,同样可以视为矩形Top的边界
        view.getLocationOnScreen(location);
        //设置popupwindow位置
        popupWindow.showAtLocation(view, Gravity.TOP|Gravity.RIGHT,0,location[1]+view.getMeasuredHeight());
    }


    public void query(){

        //查询表,然后通过add，把新数据重新添加到list
        Cursor cursor = db.query("student",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                //取数据：cursor.getXXX()是表示要取出的数据的类型，然后cursor.getColumnIdex是获得类名
                Student student = new Student(
                        cursor.getInt(cursor.getColumnIndex("id")),
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