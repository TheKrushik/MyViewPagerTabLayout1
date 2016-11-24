package com.notarazi.myviewpagertablayout1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;

import com.notarazi.myviewpagertablayout1.model.ClassModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ClassDAO extends ClassDBDAO {

//    private static final SimpleDateFormat formatter = new SimpleDateFormat(
//            "yyyy-MM-dd", Locale.ENGLISH);

    public ClassDAO(Context context) {
        super(context);
    }

    //...

    public long saveClass(ClassModel classModel) {
        ContentValues values = new ContentValues();
        values.put(DatabaseManager.COLUMN_CLASS_NAME, classModel.getName());
//        values.put(DatabaseManager.EMPLOYEE_DOB, formatter.format(classModel.getDateOfBirth()));
//        values.put(DatabaseManager.EMPLOYEE_SALARY, classModel.getSalary());

        return database.insert(DatabaseManager.TABLE_CLASS, null, values);
    }

    //Get all records from the database
    public ArrayList<ClassModel> getClasses() {
        ArrayList<ClassModel> classes = new ArrayList<ClassModel>();

        Cursor cursor = database.query(DatabaseManager.TABLE_CLASS,
                new String[]{DatabaseManager.COLUMN_CLASS_ID,
                        DatabaseManager.COLUMN_CLASS_NAME/*,
                        DatabaseManager.EMPLOYEE_DOB,
                        DatabaseManager.EMPLOYEE_SALARY*/},
                        null, null, null, null, null);

        while (cursor.moveToNext()) {
            ClassModel classModel = new ClassModel();
            classModel.setId(cursor.getInt(0));
            classModel.setName(cursor.getString(1));
//            try {
//                classModel.setDateOfBirth(formatter.parse(cursor.getString(2)));
//            } catch (ParseException e) {
//                classModel.setDateOfBirth(null);
//            } catch (java.text.ParseException e) {
//                e.printStackTrace();
//            }
//            classModel.setSalary(cursor.getDouble(3));

            classes.add(classModel);
        }
        return classes;
    }
}