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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogInActivity extends Fragment {
    MainActivity mainActivity;
    Handler hander = new Handler();
    Cursor cursor;
    EditText id_input, password_input;
    Button login;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.login, container, false);

        id_input = (EditText) rootView.findViewById(R.id.id_input);
        password_input = (EditText) rootView.findViewById(R.id.password_input);
        login = (Button) rootView.findViewById(R.id.login_button);
        setData(rootView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = id_input.getText().toString();
                String password = password_input.getText().toString();
                if(id.equals(cursor.getString(0)) && password.equals(cursor.getString(1)))
                {
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    Toast.makeText(getContext(),"Login success",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return rootView;
    }

    public void setData(ViewGroup rootView)
    {
        LogInTable.openDatabase(getContext(),"logIn");
        LogInTable.createTable("logIn");
        cursor =LogInTable.selectData("logIn");
        if(cursor.getCount() == 0)
        {
            LogInTable.insert("homeschooling","1234",0);
        }
        cursor = LogInTable.selectData("logIn");
        cursor.moveToNext();
        mainActivity.id = cursor.getString(0);

    }
}
