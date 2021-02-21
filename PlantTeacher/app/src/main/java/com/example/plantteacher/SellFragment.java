package com.example.plantteacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.chrisbanes.photoview.PhotoView;

public class SellFragment extends Fragment implements onBackPressedListener{
    MainActivity mainActivity;
    PhotoView product;
    TextView name;
    TextView prize;
    Button purchase;
    Button back;
    Bundle bundle;
    int where;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.sell_fragment, container, false);

        product = (PhotoView) rootView.findViewById(R.id.product);
        name = (TextView) rootView.findViewById(R.id.name);
        prize = (TextView) rootView.findViewById(R.id.prize);
        bundle = MainActivity.mbundle;

        byte[] image = bundle.getByteArray("image");
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        product.setImageBitmap(bitmap);

        String name1 = bundle.getString("name");
        name.setText(name1);

        String prize1 = bundle.getString("prize");
        prize.setText(prize1);

        where = bundle.getInt("back");
        MainActivity.mbundle = null;

        purchase = (Button) rootView.findViewById(R.id.purchase);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                showMessage();
            }
        });

        back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onChangedFragment(where,null);
            }
        });


        return rootView;
    }

    private void showMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("caution");
        builder.setMessage("Are you sure to purchase this product?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = "Thank you";
                purchase.setText(message);
                purchase.setBackgroundColor(purchase.getContext().getResources().getColor(R.color.teal_700));
            }
        });

        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                String message = "purchase";
                purchase.setText(message);
                purchase.setBackgroundColor(purchase.getContext().getResources().getColor(R.color.purple_500));

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        mainActivity.onChangedFragment(where,null);
    }
}
