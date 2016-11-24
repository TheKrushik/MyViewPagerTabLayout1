package com.notarazi.myviewpagertablayout1.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.notarazi.myviewpagertablayout1.R;
import com.notarazi.myviewpagertablayout1.db.EmployeeDAO;
import com.notarazi.myviewpagertablayout1.adapter.EmpListAdapter;
import com.notarazi.myviewpagertablayout1.model.Employee;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class EmpListFragment extends Fragment implements OnItemClickListener,
        OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "employee_list";

    Activity activity;
    ListView employeeListView;
    ArrayList<Employee> employees;

    EmpListAdapter employeeListAdapter;
    EmployeeDAO employeeDAO;

    private GetEmpTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        employeeDAO = new EmployeeDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emp_list, container,
                false);
        findViewsById(view);

        task = new GetEmpTask(activity);
        task.execute((Void) null);

        employeeListView.setOnItemClickListener(this);
        employeeListView.setOnItemLongClickListener(this);
        // Employee e = employeeDAO.getEmployee(1);
        // Log.d("employee e", e.toString());
        return view;
    }

    private void findViewsById(View view) {
        employeeListView = (ListView) view.findViewById(R.id.list_emp);
    }

    @Override
    public void onResume() {
//        getActivity().setTitle(R.string.app_name);
//        getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
        Employee employee = (Employee) list.getItemAtPosition(position);

        if (employee != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedEmployee", employee);
//            CustomEmpDialogFragment customEmpDialogFragment = new CustomEmpDialogFragment();
//            customEmpDialogFragment.setArguments(arguments);
//            customEmpDialogFragment.show(getFragmentManager(),
//                    CustomEmpDialogFragment.ARG_ITEM_ID);
            Toast.makeText(activity, employee.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Employee employee = (Employee) parent.getItemAtPosition(position);

        // Use AsyncTask to delete from database
        //**employeeDAO.delete(employee);
        //**employeeListAdapter.remove(employee);
        return true;
    }

    public class GetEmpTask extends AsyncTask<Void, Void, ArrayList<Employee>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetEmpTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Employee> doInBackground(Void... arg0) {
            ArrayList<Employee> employeeList = employeeDAO.getEmployees();
            return employeeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Employee> empList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("employees", empList.toString());
                employees = empList;
                if (empList != null) {
                    if (empList.size() != 0) {
                        employeeListAdapter = new EmpListAdapter(activity,
                                empList);
                        employeeListView.setAdapter(employeeListAdapter);
                    } else {
                        Toast.makeText(activity, "No Employee Records",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }

    /*
     * This method is invoked from MainActivity onFinishDialog() method. It is
     * called from CustomEmpDialogFragment when an employee record is updated.
     * This is used for communicating between fragments.
     */
    public void updateView() {
        task = new GetEmpTask(activity);
        task.execute((Void) null);
    }
}