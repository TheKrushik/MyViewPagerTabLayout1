package com.notarazi.myviewpagertablayout1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notarazi.myviewpagertablayout1.R;
import com.notarazi.myviewpagertablayout1.model.ClassModel;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<ClassModel> {

    private Context context;
    List<ClassModel> classes;

//    private static final SimpleDateFormat formatter = new SimpleDateFormat(
//            "yyyy-MM-dd", Locale.ENGLISH);

    public ClassAdapter(Context context, List<ClassModel> classes) {
        super(context, R.layout.item_list_class, classes);
        this.context = context;
        this.classes = classes;
    }

    private class ViewHolder {
        TextView txtIdClass;
        TextView txtNameClass;
//        TextView empDobTxt;
//        TextView empSalaryTxt;
    }

    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public ClassModel getItem(int position) {
        return classes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_class, null);
            holder = new ViewHolder();

            holder.txtIdClass = (TextView) convertView.findViewById(R.id.txtIdClass);
            holder.txtNameClass = (TextView) convertView.findViewById(R.id.txtNameClass);
//            holder.empDobTxt = (TextView) convertView.findViewById(R.id.txt_emp_dob);
//            holder.empSalaryTxt = (TextView) convertView.findViewById(R.id.txt_emp_salary);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ClassModel classModel = (ClassModel) getItem(position);
        holder.txtIdClass.setText(classModel.getId() + "");
        holder.txtNameClass.setText(classModel.getName());
//        holder.empSalaryTxt.setText(classModel.getSalary() + "");
//
//        holder.empDobTxt.setText(formatter.format(classModel.getDateOfBirth()));

        return convertView;
    }

    @Override
    public void add(ClassModel classModel) {
        classes.add(classModel);
        notifyDataSetChanged();
        super.add(classModel);
    }

    @Override
    public void remove(ClassModel classModel) {
        classes.remove(classModel);
        notifyDataSetChanged();
        super.remove(classModel);
    }
}