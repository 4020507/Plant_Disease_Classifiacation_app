package com.example.plantteacher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommunityitemView extends LinearLayout {
    TextView title;
    public CommunityitemView(Context context) {
        super(context);
        init(context);
    }

    public CommunityitemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.community_item,this,true);
        title = (TextView) findViewById(R.id.title);
    }
    public void setTitle(String t){title.setText(t);}
}
