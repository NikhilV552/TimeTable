package com.narendra.timetable.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import static com.narendra.timetable.Database.TineTableContract.*;

public class TimeTableHelper extends SQLiteOpenHelper {

    String TABLE_NAME = TableEntry.TABLE_NAME;

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "TimeTable.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public TimeTableHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TableEntry.TABLE_NAME + " ("
                + TableEntry.COLUMN_TIMETABLE_NAME+ " VARCHAR, "
                + TableEntry.COLUMN_NO_OF_PERIODS_NUMBER + " INTEGER, "
                + TableEntry.COLUMN_NO_OF_ROWS + " INTEGER);";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

    }




}
