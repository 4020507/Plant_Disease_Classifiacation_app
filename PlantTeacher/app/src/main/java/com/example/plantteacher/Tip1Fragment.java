package com.example.plantteacher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class Tip1Fragment extends Fragment implements onBackPressedListener{

    MainActivity mainActivity;
    Button tip1;
    ImageView tip1_image;
    Button tip2;
    Bundle bundle;
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
        ViewGroup rootView = (ViewGroup) getLayoutInflater().inflate(R.layout.tip1, container, false);

        //각 팁을 눌렀을 때 동작하는 코드. 팁을 추가할 때 그대로 복사 붙여넣기 하면 됨.
        //tip1.xml 보고 하기

        //tip1 버튼에 대한 id를 받아옴
        tip1 = (Button) rootView.findViewById(R.id.tip1);
        tip1.setText("tomato bacterial_spot");
        //눌렀을 때 동작
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //병명 이름을 title에 씀
                String title = "tomato bacterial_spot";
                tip1.setText(title);
                //contents에 title에 대한 정보를 입력함
                String contents = "Worldwide distribution. In tropical and temperate countries. On tomato, capsicum, chillies and  weeds in the potato family. An important disease.\n" +
                        "\n" +
                        "Spots are numerous, small (3 mm), sunken on upper surface, slightly raised below, merging at leaf tips and margins. Leaves turn yellow and fall. Spots also on leaf stalks and stems. Fruits infected via wounds.\n" +
                        "\n" +
                        "Spreads by seed, rain splash, handling wet plants.\n" +
                        "\n" +
                        "Cultural control: 2-3-year rotation; weed, resistant varieties (capsicum); healthy seed (hot water 50°C 25 mins.) and seedlings; trickle irrigation; mulch; weed; collect and burn trash after harvest.\n" +
                        "\n" +
                        "Chemical control: copper or mancozeb at 7-10 day intervals.\n";

                //bundle을 통해 tip_explain에 title과 contents에 대한 내용을 전달.
                bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("contents",contents);

                //사진을 전송하기 위해서는 bitmap을 byte[]로 바꾸어 주어야 함.
                //R.drawable에 사진 이름을 적고 bitmap 형태로 불러옴
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bacterial_spot);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                //이런식으로 byte array로 변환 후 bundle로 통해 관련 데이터를 보냄
                byte[] b = baos.toByteArray();
                bundle.putByteArray("image",b);
                //onChangedFragment는 창 전환하기 위한 함수. MainActivity에 있음. 번호 13은 tipExplainFragment로 전환하기 위한 번호
                //bundle은 각 title, contents, image를 tipExpainFragment로 전송하기 위해
                mainActivity.onChangedFragment(14,bundle);

                //tip3,tip4등 더 추가가 필요할 경우, drawable만 바꾸고 위의 코드를 그대로 복사 붙인다면 버튼을 눌렀을 때 관련 정보가 그대로 나타나게 됨
            }
        });

        tip1_image = (ImageView) rootView.findViewById(R.id.tip1_image);
        //눌렀을 때 동작
        tip1_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //병명 이름을 title에 씀
                String title = "tomato bacterial_spot";
                //contents에 title에 대한 정보를 입력함
                String contents = "Worldwide distribution. In tropical and temperate countries. On tomato, capsicum, chillies and  weeds in the potato family. An important disease.\n" +
                        "\n" +
                        "Spots are numerous, small (3 mm), sunken on upper surface, slightly raised below, merging at leaf tips and margins. Leaves turn yellow and fall. Spots also on leaf stalks and stems. Fruits infected via wounds.\n" +
                        "\n" +
                        "Spreads by seed, rain splash, handling wet plants.\n" +
                        "\n" +
                        "Cultural control: 2-3-year rotation; weed, resistant varieties (capsicum); healthy seed (hot water 50°C 25 mins.) and seedlings; trickle irrigation; mulch; weed; collect and burn trash after harvest.\n" +
                        "\n" +
                        "Chemical control: copper or mancozeb at 7-10 day intervals.\n";

                //bundle을 통해 tip_explain에 title과 contents에 대한 내용을 전달.
                bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("contents",contents);

                //사진을 전송하기 위해서는 bitmap을 byte[]로 바꾸어 주어야 함.
                //R.drawable에 사진 이름을 적고 bitmap 형태로 불러옴
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bacterial_spot);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                //이런식으로 byte array로 변환 후 bundle로 통해 관련 데이터를 보냄
                byte[] b = baos.toByteArray();
                bundle.putByteArray("image",b);
                //onChangedFragment는 창 전환하기 위한 함수. MainActivity에 있음. 번호 13은 tipExplainFragment로 전환하기 위한 번호
                //bundle은 각 title, contents, image를 tipExpainFragment로 전송하기 위해
                mainActivity.onChangedFragment(14,bundle);

                //tip3,tip4등 더 추가가 필요할 경우, drawable만 바꾸고 위의 코드를 그대로 복사 붙인다면 버튼을 눌렀을 때 관련 정보가 그대로 나타나게 됨
            }
        });

        tip2 = (Button) rootView.findViewById(R.id.tip2);
        tip2.setText("late_blight");
        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "late_blight";
                String contents = "설명설명";

                bundle = new Bundle();
                bundle.putString("title",title);
                bundle.putString("contents",contents);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.late_blight);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                bundle.putByteArray("image",b);

                mainActivity.onChangedFragment(14,bundle);
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
        return rootView;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}