package com.example.th2android.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2android.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "dabase.db";
    private static int DATABASE_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE books("+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT, author TEXT, date TEXT, phamvi TEXT, doituong TEXT, rating TEXT)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public void addItem(Item i){
//        String sql = "INSERT INTO items(name, author, date, phamvi, doituong, rating)" +
//                "VALUES(?,?,?,?,?,?)";
//        String[] args = {i.getName(), i.getAuthor(), i.getDate(), i.getPhamvi(), i.getDoituong(), i.getRating()};
//        SQLiteDatabase st = getWritableDatabase();
//        st.execSQL(sql, args);
//    }

    public long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("author", i.getAuthor());
        values.put("date", i.getDate());
        values.put("phamvi", i.getPhamvi());
        values.put("doituong", i.getDoituong());
        values.put("rating", i.getRating());;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("books", null, values);
    }
    public List<Item> getAll(){
        List list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("books", null, null, null, null, null,null
        );
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String author = rs.getString(2);
            String date = rs.getString(3);
            String phamvi = rs.getString(4);
            String doituong = rs.getString(5);
            String rating= rs.getString(6);

            list.add(new Item(id,name, author, date, phamvi, doituong, rating));

        }
        return list;
    }
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("author", i.getAuthor());
        values.put("date", i.getDate());
        values.put("phamvi", i.getPhamvi());
        values.put("doituong", i.getDoituong());
        values.put("rating", i.getRating());;

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "ID = ?";
        String[] whereArgs ={String.valueOf(i.getId())};
        System.out.println("Update Item" + String.valueOf(i.getId()));
        return sqLiteDatabase.update("books", values, whereClause, whereArgs);
    }
    //delete
    public int delete(int id){
        String whereClause = "ID = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        System.out.println("Delete Item" + String.valueOf(id));
        return sqLiteDatabase.delete("books", whereClause, whereArgs);
    }

//    public List<Item> searchByCategory(String category) {
//        List<Item> list= new ArrayList<>();
//        String whereClause = "category like ?";
//        String[] whereArgs = {"%"+category+"%"};
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id= rs.getInt(0);
//            String title = rs.getString(1);
//            String c = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id,title,c,price,date));
//        }
//        return list;
//    }
//    public List<Item> getByDateFromTo(String from,String to) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date BETWEEN ? AND ?";
//        String[] whereArgs = { from.trim(),to.trim()};
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id= rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id,title,category,price,date));
//        }
//        return list;
//    }

//    public List<Item> getByDate(String date) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[] whereArgs = {date};
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id= rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            list.add(new Item(id,title,category,price,date));
//        }
//        return list;
//    }


//    public List<Item> getByDateFromTo(String from,String to) {
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date BETWEEN ? AND ?";
//        String[] whereArgs = { from.trim(),to.trim()};
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        Cursor rs = sqLiteDatabase.query("items",
//                null, whereClause, whereArgs,
//                null, null, null);
//        while ((rs != null) && (rs.moveToNext())) {
//            int id= rs.getInt(0);
//            String title = rs.getString(1);
//            String category = rs.getString(2);
//            String price = rs.getString(3);
//            String date = rs.getString(4);
//            list.add(new Item(id,title,category,price,date));
//        }
//        return list;
//    }

        public List<Item> searchByPhamvi(String phamvi) {
        List<Item> list= new ArrayList<>();
        String whereClause = "phamvi like ?";
        String[] whereArgs = {"%"+phamvi+"%"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("books",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String name = rs.getString(1);
            String author = rs.getString(2);
            String date = rs.getString(3);
            String pv = rs.getString(4);
            String doituong = rs.getString(5);
            String rating= rs.getString(6);
            list.add(new Item(id,name, author, date, pv, doituong, rating));
        }
        return list;
    }
}
