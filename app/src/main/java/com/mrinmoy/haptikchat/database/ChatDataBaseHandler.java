package com.mrinmoy.haptikchat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mrinmoy.haptikchat.model.FavAndCount;

import java.util.ArrayList;
import java.util.List;

public class ChatDataBaseHandler extends SQLiteOpenHelper {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PUBLICNAME = "publicname";
    public static final String KEY_FAV_COUNT = "favcount";
    public static final String KEY_MESSAGE_COUNT = "msgCount";
    public static final String KEY_IMAGE = "image";
    public static final int KEY_USERNAME_IDX = 0;
    public static final int KEY_PUBLICNAME_IDX = 1;
    public static final int KEY_FAV_COUNT_IDX = 2;
    public static final int KEY_MESSAGE_COUNT_IDX = 3;
    public static final int KEY_IMAGE_IDX = 4;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favManager";
    private static final String TABLE_FAV = "favtable";

    public ChatDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_favS_TABLE = "CREATE TABLE " + TABLE_FAV + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY,"
                + KEY_PUBLICNAME + " TEXT,"
                + KEY_FAV_COUNT + " TEXT,"
                + KEY_MESSAGE_COUNT + " TEXT,"
                + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_favS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);

        onCreate(db);
    }


    public void addFavAndCount(FavAndCount fav) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, fav.getUserName()); // FavAndCount Name
        values.put(KEY_PUBLICNAME, fav.getPublicName()); // FavAndCount Phone
        values.put(KEY_FAV_COUNT, fav.getFavCount()); // FavAndCount Name
        values.put(KEY_MESSAGE_COUNT, fav.getMsgCount()); // FavAndCount Phone
        values.put(KEY_IMAGE, fav.getImg()); // FavAndCount Phone
        // Inserting Row
        db.insert(TABLE_FAV, null, values);
        db.close();
    }

    public FavAndCount getFavAndCount(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAV, new String[]{KEY_USERNAME, KEY_PUBLICNAME,
                        KEY_FAV_COUNT, KEY_MESSAGE_COUNT,KEY_IMAGE}, KEY_USERNAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        FavAndCount fav = new FavAndCount(cursor.getString(0),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),cursor.getString(4));

        return fav;
    }



    public List<FavAndCount> getAllFavAndCounts() {
        List<FavAndCount> favList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_FAV;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FavAndCount fav = new FavAndCount();
                fav.setUserName(cursor.getString(KEY_USERNAME_IDX));
                fav.setPublicName(cursor.getString(KEY_PUBLICNAME_IDX));
                fav.setFavCount(Integer.parseInt(cursor.getString(KEY_FAV_COUNT_IDX)));
                fav.setMsgCount(Integer.parseInt(cursor.getString(KEY_MESSAGE_COUNT_IDX)));
                fav.setImg(cursor.getString(KEY_IMAGE_IDX));
                // Adding fav to list
                favList.add(fav);
            } while (cursor.moveToNext());
        }


        return favList;
    }

    public int updateFavAndCount(FavAndCount fav) {
        int isUpdated = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, fav.getUserName());
        values.put(KEY_PUBLICNAME, fav.getPublicName());
        values.put(KEY_FAV_COUNT, fav.getFavCount());
        values.put(KEY_MESSAGE_COUNT, fav.getMsgCount());
        values.put(KEY_IMAGE, fav.getImg());

        isUpdated = db.update(TABLE_FAV, values, KEY_USERNAME + " = ?",
                new String[]{String.valueOf(fav.getUserName())});
        if (isUpdated == 0)
            addFavAndCount(fav);

        return isUpdated;
    }

    public int updateFav(FavAndCount fav, int favcount) {
        int isUpdated = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FAV_COUNT, favcount);

        isUpdated = db.update(TABLE_FAV, values, KEY_USERNAME + " = ?",
                new String[]{String.valueOf(fav.getUserName())});


        return isUpdated;
    }

}