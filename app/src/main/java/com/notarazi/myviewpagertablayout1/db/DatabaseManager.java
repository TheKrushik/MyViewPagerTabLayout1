package com.notarazi.myviewpagertablayout1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employeedb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CLASS = "classes";
    public static final String TABLE_DEPARTMENT = "department";

    public static final String COLUMN_CLASS_ID = "_id";
    public static final String COLUMN_CLASS_NAME = "CLASS_NAME";
//	public static final String EMPLOYEE_DOB = "dob";
//	public static final String EMPLOYEE_SALARY = "salary";
//	public static final String EMPLOYEE_DEPARTMENT_ID = "dept_id";

    public static final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS + " ("
            + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + COLUMN_CLASS_NAME + " TEXT NOT NULL )";
//            + EMPLOYEE_SALARY + " DOUBLE, "
//            + EMPLOYEE_DOB + " DATE, " + EMPLOYEE_DEPARTMENT_ID + " INT, "
//            + "FOREIGN KEY(" + EMPLOYEE_DEPARTMENT_ID + ") REFERENCES "
//            + TABLE_DEPARTMENT + "(id) " + ")";

    public static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "("
            + COLUMN_CLASS_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_CLASS_NAME + ")";

    private static DatabaseManager instance;

    public static synchronized DatabaseManager getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseManager(context);
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
