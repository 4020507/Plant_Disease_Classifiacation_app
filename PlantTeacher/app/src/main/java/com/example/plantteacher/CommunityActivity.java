package com.example.plantteacher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends Fragment implements onBackPressedListener{
    MainActivity mainActivity;
    Cursor community_cursor;
    Cursor login_cursor;

    ListView listView;
    CommunityAdapter adapter;
    TextView back;
    TextView write;
    Bundle bundle;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.community, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);
        adapter =new CommunityAdapter();

        bundle = mainActivity.mbundle;

        CommunityTable.openDatabase(getContext(),"community");
        CommunityTable.createTable("community");
        community_cursor =CommunityTable.selectData("community");

        LogInTable.openDatabase(getContext(),"logIn");
        LogInTable.createTable("logIn");
        login_cursor =LogInTable.selectData("logIn");
        login_cursor.moveToNext();


        if(community_cursor.getCount() !=0)
        {
            for(int i = 0;i<community_cursor.getCount();i++)
            {
                community_cursor.moveToNext();
                adapter.addItem(new Communityitem(community_cursor.getString(1),community_cursor.getString(2),community_cursor.getBlob(3)));
            }
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Communityitem i = (Communityitem) adapter.getItem(position);
                //listView.getChildAt(position).setBackgroundColor(Color.BLUE);
                bundle = new Bundle();
                bundle.putString("title",i.getTitle());
                bundle.putString("contents",i.getContents());
                bundle.putByteArray("image",i.getArray());
                bundle.putInt("position",adapter.getCount() - position - 1);
                Toast.makeText(getContext(),i.getTitle(),Toast.LENGTH_LONG).show();

                mainActivity.onChangedFragment(6,bundle);
            }
        });

        back = (TextView) rootView.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        write = (TextView) rootView.findViewById(R.id.write);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(3,null);
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

    class CommunityAdapter extends BaseAdapter
    {
        ArrayList<Communityitem>item = new ArrayList();

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
        public void addItem(Communityitem items){
            item.add(0,items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommunityitemView view = null;
            if(convertView == null)
            {
                view =new CommunityitemView(getContext());
            }
            else
            {
                view =(CommunityitemView)convertView;
            }
            Communityitem items = item.get(position);
            view.setTitle(items.title);
            return view;
        }
    }
}
