package com.example.task1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

class Databasehelper extends SQLiteOpenHelper {
   public static final String databasename="Dance.db";
   public static final String tablename="Dancer_table";
   public static final String col1="NAME";
   public static final String col2="AGE";
   public static final String col3="GENDER";
    public Databasehelper(Context context) {
        super(context,databasename,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tablename+"(ID integer primary key autoincrement ,NAME TEXT,AGE integer,GENDER text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tablename);
        onCreate(db);

    }
    public boolean insertData(String name, String age, String gender){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,name);
        contentValues.put(col2,age);
        contentValues.put(col3,gender);
        long result=db.insert(tablename,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+tablename,null);
        return res;
    }


    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(tablename,"ID=?",new String[]{id});

    }

    public boolean updateData(String name, String age, String gender) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(col1,name);
        c.put(col2,age);
        c.put(col3,gender);
        db.update(tablename,c,"NAME=?",new String[]{name});
        return true;

    }
}
