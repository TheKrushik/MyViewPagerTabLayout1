package com.notarazi.myviewpagertablayout1.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.notarazi.myviewpagertablayout1.R;
import com.notarazi.myviewpagertablayout1.db.ClassDAO;
import com.notarazi.myviewpagertablayout1.model.ClassModel;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClassAddFragment extends Fragment implements OnClickListener {

    // UI references
    private EditText empNameEtxt;
    private EditText empSalaryEtxt;
    private EditText empDobEtxt;
    private Button addButton;
    private Button resetButton;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    ClassModel classModel = null;
    private ClassDAO classDAO;
    private AddEmpTask task;

    public static final String ARG_ITEM_ID = "emp_add_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classDAO = new ClassDAO(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_emp, container, false);

        findViewsById(rootView);

        setListeners();

        //For orientation change.
        if (savedInstanceState != null) {
            dateCalendar = Calendar.getInstance();
            if (savedInstanceState.getLong("dateCalendar") != 0)
                dateCalendar.setTime(new Date(savedInstanceState.getLong("dateCalendar")));
        }

        return rootView;
    }

    private void setListeners() {
//        empDobEtxt.setOnClickListener(this);
//        Calendar newCalendar = Calendar.getInstance();
//        datePickerDialog = new DatePickerDialog(getActivity(),
//                new OnDateSetListener() {
//
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        dateCalendar = Calendar.getInstance();
//                        dateCalendar.set(year, monthOfYear, dayOfMonth);
//                        empDobEtxt.setText(formatter.format(dateCalendar
//                                .getTime()));
//                    }
//
//                }, newCalendar.get(Calendar.YEAR),
//                newCalendar.get(Calendar.MONTH),
//                newCalendar.get(Calendar.DAY_OF_MONTH));

        addButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    protected void resetAllFields() {
        empNameEtxt.setText("");
        empSalaryEtxt.setText("");
        empDobEtxt.setText("");
    }

    private void setEmployee() {
        classModel = new ClassModel();
        classModel.setName(empNameEtxt.getText().toString());
//        classModel.setSalary(Double.parseDouble(empSalaryEtxt.getText()
//                .toString()));
//        if (dateCalendar != null)
//            classModel.setDateOfBirth(dateCalendar.getTime());
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.add_emp);
        //getActivity().getActionBar().setTitle(R.string.add_emp);
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (dateCalendar != null)
            outState.putLong("dateCalendar", dateCalendar.getTime().getTime());
    }

    private void findViewsById(View rootView) {
        empNameEtxt = (EditText) rootView.findViewById(R.id.etxt_name);
//        empSalaryEtxt = (EditText) rootView.findViewById(R.id.etxt_salary);
//        empDobEtxt = (EditText) rootView.findViewById(R.id.etxt_dob);
//        empDobEtxt.setInputType(InputType.TYPE_NULL);

        addButton = (Button) rootView.findViewById(R.id.button_add);
        resetButton = (Button) rootView.findViewById(R.id.button_reset);
    }

    @Override
    public void onClick(View view) {
        if (view == empDobEtxt) {
            datePickerDialog.show();
        } else if (view == addButton) {
            setEmployee();

            task = new AddEmpTask(getActivity());
            task.execute((Void) null);
        } else if (view == resetButton) {
            resetAllFields();
        }
    }

    public class AddEmpTask extends AsyncTask<Void, Void, Long> {

        private final WeakReference<Activity> activityWeakRef;

        public AddEmpTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {
            long result = classDAO.saveClass(classModel);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "ClassModel Saved",
                            Toast.LENGTH_LONG).show();
            }
        }
    }
}