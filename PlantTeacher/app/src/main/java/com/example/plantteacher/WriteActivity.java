package com.example.plantteacher;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class WriteActivity extends Fragment implements onBackPressedListener{
    MainActivity mainActivity;
    EditText title;
    EditText contents;
    ImageView image;
    Button Camera;
    Bundle bundle;
    Button back;
    Button save;
    byte[] array;
    Cursor community_cursor;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.write, container, false);


        title = (EditText) rootView.findViewById(R.id.title);
        contents = (EditText) rootView.findViewById(R.id.contents);
        image = (ImageView) rootView.findViewById(R.id.camera);
        Camera = (Button) rootView.findViewById(R.id.taking);
        bundle = MainActivity.mbundle;
        title.setText("");
        contents.setText("");

        if(bundle!=null)
        {
            Bitmap bitmap;
            array = bundle.getByteArray("photo");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            bitmap = BitmapFactory.decodeByteArray(array,0,array.length);
            image.setImageBitmap(bitmap);
            MainActivity.mbundle = null;
        }
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(4,null);
            }
        });
        save = (Button) rootView.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = title.getText().toString();
                String c = contents.getText().toString();
                if(t.equals("") || c.equals(""))
                    Toast.makeText(getContext(),"You should write a title and contents",Toast.LENGTH_LONG).show();
                else
                {

                    CommunityTable.openDatabase(getContext(),"community");
                    CommunityTable.createTable("community");
                    community_cursor =CommunityTable.selectData("community");

                    for(int i = 0;i<community_cursor.getCount();i++)
                        community_cursor.moveToNext();
                    CommunityTable.insert(mainActivity.id,t,c,array);
                    mainActivity.onChangedFragment(2,null);
                }
            }
        });

        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(2,null);
            }
        });
        return rootView;
    }

    @Override
    public void onBackPressed() {
        mainActivity.onChangedFragment(2,null);
    }
}
