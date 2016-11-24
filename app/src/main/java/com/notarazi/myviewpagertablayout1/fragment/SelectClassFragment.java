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
import com.notarazi.myviewpagertablayout1.db.ClassDAO;
import com.notarazi.myviewpagertablayout1.adapter.ClassAdapter;
import com.notarazi.myviewpagertablayout1.model.ClassModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SelectClassFragment extends Fragment implements OnItemClickListener,
        OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "class_list";

    Activity activity;
    ListView classListView;
    ArrayList<ClassModel> classes;

    ClassAdapter classListAdapter;
    ClassDAO classDAO;

    private GetEmpTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        classDAO = new ClassDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_class, container,
                false);
        findViewsById(view);

        task = new GetEmpTask(activity);
        task.execute((Void) null);

        classListView.setOnItemClickListener(this);
        classListView.setOnItemLongClickListener(this);
        // ClassModel e = classDAO.getEmployee(1);
        // Log.d("classModel e", e.toString());
        return view;
    }

    private void findViewsById(View view) {
        classListView = (ListView) view.findViewById(R.id.listClass);
    }

    @Override
    public void onResume() {
//        getActivity().setTitle(R.string.app_name);
//        getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
        ClassModel classModel = (ClassModel) list.getItemAtPosition(position);

        if (classModel != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedEmployee", classModel);
//            CustomEmpDialogFragment customEmpDialogFragment = new CustomEmpDialogFragment();
//            customEmpDialogFragment.setArguments(arguments);
//            customEmpDialogFragment.show(getFragmentManager(),
//                    CustomEmpDialogFragment.ARG_ITEM_ID);
            Toast.makeText(activity, classModel.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ClassModel classModel = (ClassModel) parent.getItemAtPosition(position);

        // Use AsyncTask to delete from database
        //**classDAO.delete(classModel);
        //**classListAdapter.remove(classModel);
        return true;
    }

    public class GetEmpTask extends AsyncTask<Void, Void, ArrayList<ClassModel>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetEmpTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<ClassModel> doInBackground(Void... arg0) {
            ArrayList<ClassModel> classModelList = classDAO.getClasses();
            return classModelList;
        }

        @Override
        protected void onPostExecute(ArrayList<ClassModel> classList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                Log.d("classes", classList.toString());
                classes = classList;
                if (classList != null) {
                    if (classList.size() != 0) {
                        classListAdapter = new ClassAdapter(activity,
                                classList);
                        classListView.setAdapter(classListAdapter);
                    } else {
                        Toast.makeText(activity, "No ClassModel Records",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }

    /*
     * This method is invoked from MainActivity onFinishDialog() method. It is
     * called from CustomEmpDialogFragment when an classModel record is updated.
     * This is used for communicating between fragments.
     */
    public void updateView() {
        task = new GetEmpTask(activity);
        task.execute((Void) null);
    }
}