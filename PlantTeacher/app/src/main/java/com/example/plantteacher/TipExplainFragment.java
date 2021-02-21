package com.example.plantteacher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TipExplainFragment extends Fragment implements onBackPressedListener{
    Bundle bundle;
    MainActivity mainActivity;
    TextView title;
    TextView contents;
    ImageView image;
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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.tip_explain, container, false);
        bundle = MainActivity.mbundle;
        String t = bundle.getString("title");
        String c = bundle.getString("contents");
        byte[] im = bundle.getByteArray("image");

        title = (TextView) rootView.findViewById(R.id.title);
        contents = (TextView) rootView.findViewById(R.id.contents);
        title.setText(t);
        contents.setText(c);

        image = (ImageView) rootView.findViewById(R.id.image);
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeByteArray(im,0,im.length);
        image.setImageBitmap(bitmap);

        MainActivity.mbundle = null;

        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(13,null);
            }
        });


        return rootView;
    }

    @Override
    public void onBackPressed() {
        mainActivity.onChangedFragment(13,null);
    }
}
