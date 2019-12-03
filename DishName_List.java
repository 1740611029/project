package com.example.helloworld.SQLiteOpenHelper;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class DishName_List extends AppCompatActivity {
    private ListView lv_dish;
    private String[] arrCity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishname_listactivity);
        lv_dish=findViewById(R.id.lv_dish);
        Resources resources=getResources();//类似于引用资源的说法
        arrCity=resources.getStringArray(R.array.arr_city);//把R.array.arr_city传给arrCity
        //传过来的数据不能直接使用，要用适配器，就比如220v的电不能直接充，要有个充电器，就类似于适配器
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrCity);/*第一个参数是上下文，就是当前的Activity，
        第二个参数是布局，可以是android中的布局，也可以是自己写的布局，这里面的参数的布局是只有一个TextView，第三个参数是要显示的数据*/
        lv_dish.setAdapter(adapter);
    }

    public void back(View view){
        Intent intent=new Intent(DishName_List.this,MainActivity_sqliteopenhelper.class);
        startActivity(intent);
    }
}
