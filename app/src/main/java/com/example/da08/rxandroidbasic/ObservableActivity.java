package com.example.da08.rxandroidbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;


/*
 - 사용 전 그래들에 디펜던시 추가 compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
 - reactivex의 observable을 import해야 create 메소드 사용 가능
*/

public class ObservableActivity extends AppCompatActivity {
    // 문자열을 발행하는 Observable
    Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable);
    }


    private void createObservable(){

//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {   // observer의 subject 역할
//                e.onNext("Hello Rx");
//                e.onNext("Yoyo");
//                e.onComplete();  // 종료
//            }
//        });

        // 위의 내요을 람다식으로 바꿈
        observable = observable.create(emitter -> {
            emitter.onNext("Hellr Rx");
            emitter.onNext("Yoyo");
            emitter.onComplete();
        });
    }

    // 옵저버 등록
    private void bindObserver(){
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
