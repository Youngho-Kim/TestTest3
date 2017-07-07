package com.kwave.android.testtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwave.android.testtest.domain.UserInformation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.kwave.android.testtest.R.id.textFood;

public class InformationActivity extends AppCompatActivity {
    private List<UserInformation> data = new ArrayList<>();
    ImageView sun,mon,tue,wen,thi,fri,sat,imageView4;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setView();
        today();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);  // 인플레이트 된 객체에 위젯의 주소값을 넘겨준다.


        // 1. 데이터
//        ArrayList<Data> datas = new ArrayList<>();        // 데이터를 생성하기 위해 ArrayList를 Data의 형식으로 만든다.
//        datas = Loader.getData(this);                     // Data 타입으로 만들어진 객체에 MainActivity 클래스에 생성된 변수들이 들어간다 떄로는 그 자신이 들어갈 때도 있다..
        ArrayList<Data> datas = Loader.getData(this);
        // 2. 어댑터
        CustomRecycler adapter = new CustomRecycler(datas,this);

        // 3. 연결
        recyclerView.setAdapter(adapter);

        // 4. 레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, TimerActivity.class);
                startActivity(intent);

            }
        });

    }

    public void setView(){
        sun = (ImageView) findViewById(R.id.imageSun);
        mon = (ImageView) findViewById(R.id.imageMon);
        tue = (ImageView) findViewById(R.id.imageTue);
        wen = (ImageView) findViewById(R.id.imageWen);
        thi = (ImageView) findViewById(R.id.imageThi);
        fri = (ImageView) findViewById(R.id.imageFri);
        sat = (ImageView) findViewById(R.id.imageSat);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        sun.setVisibility(View.GONE);
        mon.setVisibility(View.GONE);
        tue.setVisibility(View.GONE);
        wen.setVisibility(View.GONE);
        thi.setVisibility(View.GONE);
        fri.setVisibility(View.GONE);
        sat.setVisibility(View.GONE);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


    }

    public void today(){
        Calendar cal = Calendar.getInstance();
        String strWeek = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(nWeek == 1){
            sun.setVisibility(View.VISIBLE);
            sat.setVisibility(View.GONE);
        }
        else if(nWeek == 2){
            mon.setVisibility(View.VISIBLE);
            sun.setVisibility(View.GONE);
        }
        else if(nWeek == 3){
            tue.setVisibility(View.VISIBLE);
            mon.setVisibility(View.GONE);
        }
        else if(nWeek == 4){
            wen.setVisibility(View.VISIBLE);
            tue.setVisibility(View.GONE);
        }
        else if(nWeek == 5){
            thi.setVisibility(View.VISIBLE);
            wen.setVisibility(View.GONE);
        }
        else if(nWeek == 6){
            fri.setVisibility(View.VISIBLE);
            thi.setVisibility(View.GONE);
        }
        else if(nWeek == 7){
            sat.setVisibility(View.VISIBLE);
            fri.setVisibility(View.GONE);
        }
    }
}



class CustomRecycler extends RecyclerView.Adapter<CustomRecycler.Holder>  { // 리사이클러 뷰에는 반드시 오버라이드 해야하는 것들이 있음
    // 제네릭 자리에 뷰홀더를 집어 넣어준다.(강제사항)
    ArrayList<Data> datas;
    Context context;

    //1. 생성자 만들기
    public CustomRecycler(ArrayList<Data> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }


    // List View에서 convertView == null 일때 처리
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);      // XML을 inflater를 통해서 객체로 생성
        // 위와 같이 설정할 경우 부모xml의 형식을 따라각 리스트가 설정이 되기 때문에 1개의 리스트의 크기가 전체 크기로 나오게 된다.
        // 이때 해결할 방법은 아래처럼 하던가 item_list.xml에서 android:layout_height="match_parent"를 android:layout_height="wrap_parent"으로 바꿔주면된다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null);      // XML을 inflater를 통해서 객체로 생성
//        Holder holder = new Holder(view);

        return new Holder(view);        // 만들어진 객체를 홀더로 넘겨줌
    }


    // 각 데이터 셀이 나타날때 호출되는 함수
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 1. 데이터를 꺼내고
        Data data = datas.get(position);
        // 2. 데이터를 세팅
        holder.setImage(data.resId);
        holder.setNo(data.no);
        holder.setTitle(data.title);

    }


    // 데이터의 전체 개수
    @Override
    public int getItemCount() {
        return datas.size();
    }


    class Holder extends RecyclerView.ViewHolder { // 내부 클래스는 해당하는 어댑터나 클래스에서만 사용하고자 할때 만든다.
        ImageView image;
        TextView no;
        TextView title;

        public Holder(View itemView) {               // 또한, Holder는 ViewHolder를 상속해야한다.
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageFood);
            no = (TextView) itemView.findViewById(textFood);
            title = (TextView) itemView.findViewById(R.id.textExplain);

        }

        public void setImage(int resId) {
            image.setImageResource(resId);
        }

        public void setNo(String no) {
            this.no.setText(no);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }
    }
}






class Loader {
    public static ArrayList<Data> getData(Context context){
        ArrayList<Data> result = new ArrayList<>();
        for(int i=1 ; i<=5 ; i++){
            Data data = new Data();
            if(i == 1){
                data.setNo("계란");
                data.setTitle("맛없음");
                data.setImage("food"+i, context);
            }
            else if(i == 2){
                data.setNo("치즈");
                data.setTitle("많이 비쌈");
                data.setImage("food"+i, context);
            }
            else if(i == 3){
                data.setNo("아몬드");
                data.setTitle("만원");
                data.setImage("food"+i, context);
            }
            else if(i == 4){
                data.setNo("사과");
                data.setTitle("이시림");
                data.setImage("food"+i, context);
            }
            else if(i == 5){
                data.setNo("소세지");
                data.setTitle("비쌈");
                data.setImage("food"+i, context);
            }



            result.add(data);
        }
        return result;
    }
}

class Data {
    public String no;
    public String title;
    public String image;
    public int resId;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String str, Context context) {
        image = str;
        // 문자열로 리소스 아이디 가져오기
        resId = context.getResources().getIdentifier(image, "mipmap", context.getPackageName());
    }

}
