package com.example.plantteacher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentitemView extends LinearLayout {
    TextView comment;
    TextView id;

    public CommentitemView(Context context) {
        super(context);
        init(context);
    }

    public CommentitemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item,this,true);
        id = (TextView) findViewById(R.id.id);
        comment = (TextView) findViewById(R.id.comment);
    }
    public void setComment(String t){comment.setText(t);}
    public void setId(String t){id.setText(t);}
}
