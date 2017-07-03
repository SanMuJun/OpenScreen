package com.san.openscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by San on 2017/3/17.
 */
public class AddDao {

    private Context context;

    DBHelper dbHelper;
    private SQLiteDatabase database;

    public AddDao(Context context) {
        dbHelper=new DBHelper(context);
    }

    public void insert(Dao dao){
        database = dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("text",dao.getText());
        long insert = database.insert("textload", null, values);
        dao.setId((int) insert);

        Log.e("Tag","添加的id为："+insert);
    }


    public void update(Dao dao){
        database = dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("text",dao.getText());
        int update = database.update("textload", values, "_id= " + dao.getId(), null);

        Log.e("Tag","修改的id为："+update);
    }

    public void delete(int id){
        database = dbHelper.getReadableDatabase();
        int delete = database.delete("textload", "_id= " +id, null);

        Log.e("Tag","删除的id为："+delete);

    }


    public List<Dao> query(){
        database = dbHelper.getReadableDatabase();
        List<Dao> list =new ArrayList<>();
        Cursor cursor = database.query("textload", null, null, null, null, null, "_id desc");
        while(cursor.moveToNext()){

            int cursorId = cursor.getInt(0);
            String cursorText = cursor.getString(1);
            list.add(new Dao(cursorId,cursorText));
        }

        return list;
    }

}
