package com.notarazi.myviewpagertablayout1.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmployeeDBDAO {

    protected SQLiteDatabase database;
    private DatabaseManager dbHelper;
    private Context mContext;

    public EmployeeDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DatabaseManager.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseManager.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	/*public void close() {
		dbHelper.close();
		database = null;
	}*/
}