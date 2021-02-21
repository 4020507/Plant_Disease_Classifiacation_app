package com.example.plantteacher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TipFragment extends Fragment implements onBackPressedListener{

    MainActivity mainActivity;
    Button tip1;
    Button tip2;
    Button back;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity =null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.tip_fragment, container, false);
        tip1 = (Button) rootView.findViewById(R.id.tip1);
        tip2 = (Button) rootView.findViewById(R.id.tip2);
        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(13,null);
            }
        });

        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(13,null);
            }
        });
        return rootView;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
