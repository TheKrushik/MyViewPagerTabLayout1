package com.notarazi.myviewpagertablayout1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.notarazi.myviewpagertablayout1.R;

public class FragmentPage3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, null);

        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Toast", Toast.LENGTH_SHORT).show();

                VoteDialogFragment dialog = new VoteDialogFragment();
                dialog.show(getFragmentManager(), "fragmentDialog");

            }
        });
        return view;
    }
}