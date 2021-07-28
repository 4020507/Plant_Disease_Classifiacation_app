package com.example.plantteacher;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import com.example.plantteacher.MainActivity;
import com.example.plantteacher.R;
import com.example.plantteacher.onBackPressedListener;

import java.io.ByteArrayOutputStream;

public class FertilizerFragment extends Fragment implements onBackPressedListener {
    MainActivity mainActivity;
    ImageView product1;
    TextView product1_name;
    TextView product1_prize;
    ImageView product2;
    TextView product2_name;
    TextView product2_prize;
    ImageView product3;
    TextView product3_name;
    TextView product3_prize;
    ImageView product4;
    TextView product4_name;
    TextView product4_prize;
    ImageView product5;
    TextView product5_name;
    TextView product5_prize;
    Button back;
    Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.fertilizer_fragment, container, false);

        //show products
        product1 = (ImageView) rootView.findViewById(R.id.product1);
        product1_name = (TextView) rootView.findViewById(R.id.product1_name);
        product1_prize = (TextView) rootView.findViewById(R.id.product1_prize);
        product2 = (ImageView) rootView.findViewById(R.id.product2);
        product2_name = (TextView) rootView.findViewById(R.id.product2_name);
        product2_prize = (TextView) rootView.findViewById(R.id.product2_prize);
        product3 = (ImageView) rootView.findViewById(R.id.product3);
        product3_name = (TextView) rootView.findViewById(R.id.product3_name);
        product3_prize = (TextView) rootView.findViewById(R.id.product3_prize);
        product4 = (ImageView) rootView.findViewById(R.id.product4);
        product4_name = (TextView) rootView.findViewById(R.id.product4_name);
        product4_prize = (TextView) rootView.findViewById(R.id.product4_prize);
        product5 = (ImageView) rootView.findViewById(R.id.product5);
        product5_name = (TextView) rootView.findViewById(R.id.product5_name);
        product5_prize = (TextView) rootView.findViewById(R.id.product5_prize);
        product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                Drawable d = product1.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                bundle.putByteArray("image",bitMapData);

                String name = product1_name.getText().toString();
                bundle.putString("name",name);

                String prize = product1_prize.getText().toString();
                bundle.putString("prize",prize);
                bundle.putInt("back",8);

                mainActivity.onChangedFragment(11,bundle);
            }
        });
        product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                Drawable d = product2.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                bundle.putByteArray("image",bitMapData);

                String name = product2_name.getText().toString();
                bundle.putString("name",name);

                String prize = product2_prize.getText().toString();
                bundle.putString("prize",prize);
                bundle.putInt("back",8);

                mainActivity.onChangedFragment(11,bundle);
            }
        });
        product3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                Drawable d = product3.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                bundle.putByteArray("image",bitMapData);

                String name = product3_name.getText().toString();
                bundle.putString("name",name);

                String prize = product3_prize.getText().toString();
                bundle.putString("prize",prize);
                bundle.putInt("back",8);

                mainActivity.onChangedFragment(11,bundle);
            }
        });
        product4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                Drawable d = product4.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                bundle.putByteArray("image",bitMapData);

                String name = product4_name.getText().toString();
                bundle.putString("name",name);

                String prize = product4_prize.getText().toString();
                bundle.putString("prize",prize);
                bundle.putInt("back",8);

                mainActivity.onChangedFragment(11,bundle);
            }
        });
        product5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();

                Drawable d = product5.getDrawable();
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bitMapData = stream.toByteArray();
                bundle.putByteArray("image",bitMapData);

                String name = product5_name.getText().toString();
                bundle.putString("name",name);

                String prize = product5_prize.getText().toString();
                bundle.putString("prize",prize);
                bundle.putInt("back",8);

                mainActivity.onChangedFragment(11,bundle);
            }
        });

        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(7,null);
            }
        });
        return rootView;
    }

    @Override
    public void onBackPressed() {
        mainActivity.onChangedFragment(7,null);
    }
}
