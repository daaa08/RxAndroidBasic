package com.example.da08.rxandroidbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // 서브젝트 생성
        subject = new Subject();
        // 서브젝트 동작 시작
        subject.start();
    }

    int count = 0;
    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(v ->{
            count++;
            subject.addObserver(new ObserverLmpi("Observer"+count));
//                String myname = "observer"+count;
//                @Override
//                public void notification(String msg) {
//                    System.out.println(myname + ":" +msg);
//                }
//            });

        });

        }

    // 옵저버 구현체
    public class ObserverLmpi implements Subject.Observer {
        String myname = "";
        public ObserverLmpi(String name){
            myname = name;
        }
        @Override
        public void notification(String msg) {
            System.out.println(myname+":"+msg);
        }
    }
}
