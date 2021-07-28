package com.example.plantteacher;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CommentDetailFragment extends Fragment implements onBackPressedListener{

    MainActivity mainActivity;
    TextView title;
    ImageView image;
    TextView contents;
    ListView listView;
    CommentAdapter adapter;
    EditText comment;
    Button confirm;
    Button back;
    Bundle bundle;
    Cursor cursor;

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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.comment_detail_fragment, container, false);

        bundle = MainActivity.mbundle;

        //bring contents from the community activity, and show them
        String t = bundle.getString("title");
        String c = bundle.getString("contents");
        byte[] a = bundle.getByteArray("image");
        int position = bundle.getInt("position");

        title = (TextView) rootView.findViewById(R.id.title);
        image = (ImageView) rootView.findViewById(R.id.image);
        contents = (TextView) rootView.findViewById(R.id.contents);

        title.setText(t);

        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeByteArray(a,0,a.length);
        image.setImageBitmap(bitmap);

        contents.setText(c);


        listView = (ListView) rootView.findViewById(R.id.listView);

        //bring comments
        CommentTable.openDatabase(getContext(),"position"+position);
        CommentTable.createTable("position"+position);
        cursor =CommentTable.selectData("position"+position);
        adapter = new CommentAdapter();
        if(cursor.getCount() != 0)
        {
            for(int i = 0;i<cursor.getCount();i++)
            {
                cursor.moveToNext();
                adapter.addItem(new Commentitem(cursor.getString(0),cursor.getString(1)));
            }

        }
        listView.setAdapter(adapter);
        cursor = CommentTable.selectData("position"+position);
        cursor.moveToNext();

        //write comments, and show with writer's id
        comment = (EditText) rootView.findViewById(R.id.comment);
        confirm = (Button) rootView.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = comment.getText().toString();
                if(!text.equals("")) {
                    CommentTable.insert("position" + position, mainActivity.id, text);
                    adapter.addItem(new Commentitem(mainActivity.id, text));
                    listView.setAdapter(adapter);
                    cursor.moveToNext();
                }
            }
        });
        MainActivity.mbundle = null;

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

    class CommentAdapter extends BaseAdapter
    {
        ArrayList<Commentitem> item = new ArrayList();

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
        public void addItem(Commentitem items){
            item.add(items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentitemView view = null;
            if(convertView == null)
            {
                view =new CommentitemView(getContext());
            }
            else
            {
                view =(CommentitemView)convertView;
            }
            Commentitem items = item.get(position);

            view.setComment(items.contents);
            view.setId(items.title);
            return view;
        }
    }
}
