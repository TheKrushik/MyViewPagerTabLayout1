package com.notarazi.myviewpagertablayout1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ParseException;

import com.notarazi.myviewpagertablayout1.model.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EmployeeDAO extends EmployeeDBDAO {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public EmployeeDAO(Context context) {
        super(context);
    }

    //...

    public long save(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(DatabaseManager.NAME_COLUMN, employee.getName());
        values.put(DatabaseManager.EMPLOYEE_DOB, formatter.format(employee.getDateOfBirth()));
        values.put(DatabaseManager.EMPLOYEE_SALARY, employee.getSalary());

        return database.insert(DatabaseManager.EMPLOYEE_TABLE, null, values);
    }

    //Get all records from the database
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();

        Cursor cursor = database.query(DatabaseManager.EMPLOYEE_TABLE,
                new String[] { DatabaseManager.ID_COLUMN,
                        DatabaseManager.NAME_COLUMN,
                        DatabaseManager.EMPLOYEE_DOB,
                        DatabaseManager.EMPLOYEE_SALARY }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setName(cursor.getString(1));
            try {
                employee.setDateOfBirth(formatter.parse(cursor.getString(2)));
            } catch (ParseException e) {
                employee.setDateOfBirth(null);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            employee.setSalary(cursor.getDouble(3));

            employees.add(employee);
        }
        return employees;
    }
}