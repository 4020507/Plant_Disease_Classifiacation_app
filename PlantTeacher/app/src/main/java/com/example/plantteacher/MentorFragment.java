package com.example.plantteacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MentorFragment extends Fragment implements onBackPressedListener{
    MainActivity mainActivity;
    Button button;
    Button back;
    ListView listView;
    MentorAdapter adapter;
    String name;
    View v;
    int p;
    boolean choose = false;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.mentor_fragment, container, false);


        listView = (ListView) rootView.findViewById(R.id.listView);
        adapter =new MentorAdapter();

        adapter.addItem(new Mentoritem("Expert1"));
        adapter.addItem(new Mentoritem("Expert2"));
        adapter.addItem(new Mentoritem("Expert3"));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mentoritem i = (Mentoritem) adapter.getItem(position);
                name = i.getTitle();
                p = position;
                v = view;
                showMessage();
            }

        });

        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        button = (Button) rootView.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return rootView;
    }

    class MentorAdapter extends BaseAdapter
    {
        ArrayList<Mentoritem> item = new ArrayList();

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public void addItem(Mentoritem items){
            item.add(items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MentoritemView view = null;
            if(convertView == null)
            {
                view =new MentoritemView(getContext());
            }
            else
            {
                view =(MentoritemView)convertView;
            }
            Mentoritem items = item.get(position);
            view.setTitle(items.title);
            return view;
        }
    }

    private void showMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("caution");
        builder.setMessage("Are you sure to have the "+ name + " memtoring class?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!choose) {
                    v.setBackgroundColor(Color.GREEN);
                    choose = true;
                }
                else{
                    Toast.makeText(getContext(),"You can choose one expert",Toast.LENGTH_LONG).show();
                }
                //yes를 누를 때 지정되는 색깔
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                v.setBackgroundColor(Color.WHITE);
                //No를 누를 때 지정되는 색깔
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
