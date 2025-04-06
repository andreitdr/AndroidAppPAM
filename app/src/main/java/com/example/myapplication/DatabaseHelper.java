package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "group.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MEMBERS = "members";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_SUBTITLE = "subtitle";
    private static final String COL_SHORT_DESC = "short_desc";
    private static final String COL_FULL_DESC = "full_desc";
    private static final String COL_IMAGE_NAME = "image_name";
    private static final String COL_WEB_URL = "web_url";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MEMBERS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_SUBTITLE + " TEXT, " +
                COL_SHORT_DESC + " TEXT, " +
                COL_FULL_DESC + " TEXT, " +
                COL_IMAGE_NAME + " TEXT, " +
                COL_WEB_URL + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        onCreate(db);
    }

    // Add a member
    public long insertMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, member.getName());
        values.put(COL_SUBTITLE, member.getSubtitle());
        values.put(COL_SHORT_DESC, member.getShortDescription());
        values.put(COL_FULL_DESC, member.getFullDescription());
        values.put(COL_IMAGE_NAME, member.getImageName());
        values.put(COL_WEB_URL, member.getWebUrl());
        return db.insert(TABLE_MEMBERS, null, values);
    }

    // Get all members
    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEMBERS, null);
        if (cursor.moveToFirst()) {
            do {
                Member member = new Member(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                members.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return members;
    }

    // Get single member
    public Member getMember(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEMBERS, null, COL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Member member = new Member(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
            return member;
        }
        return null;
    }

    // Update member
    public int updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, member.getName());
        values.put(COL_SUBTITLE, member.getSubtitle());
        values.put(COL_SHORT_DESC, member.getShortDescription());
        values.put(COL_FULL_DESC, member.getFullDescription());
        values.put(COL_IMAGE_NAME, member.getImageName());
        values.put(COL_WEB_URL, member.getWebUrl());
        return db.update(TABLE_MEMBERS, values, COL_ID + "=?", new String[]{String.valueOf(member.getId())});
    }

    // Delete member
    public void deleteMember(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMBERS, COL_ID + "=?", new String[]{String.valueOf(id)});
    }
}