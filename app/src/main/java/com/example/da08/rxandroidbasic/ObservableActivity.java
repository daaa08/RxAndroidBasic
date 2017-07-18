package com.example.da08.rxandroidbasic;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


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


    // 옵저버블 생성
    private void createObservable(){

//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {   // observer의 subject 역할
//                e.onNext("Hello Rx");
//                e.onNext("Yoyo");
//                e.onComplete();  // 종료
//            }
//        });

        // 위의 내용을 람다식으로 바꿈
        observable = observable.create(emitter -> {
            emitter.onNext("Hellr Rx");
            emitter.onNext("Yoyo");
            emitter.onComplete();
        });
    }

    // 옵저버 등록 1번 형태 - 안에 4개의 함수가 구현되어 있음
    @TargetApi(Build.VERSION_CODES.M)
    private void bindObserver(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {   // notify에 해당 , observable의 onNext이 생성될때마다 호출
                Log.e("onNext", "======================="+value);

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", "======================="+e.getMessage());

            }

            @Override
            public void onComplete() {    // 완료
                Log.e("onComplete", "======================= complete");
            }
        };
        observable.subscribe(observer);

        // 옵저버 등록 2번 형태 - 람다식을 사용하기 전 상태로 함수 하나씩 분리
        Consumer<String> onNext = new Consumer<String>() {
            @Override
            public void accept(String str) {
                Log.e("OnNext","============="+str);
            }
        };
        Consumer<Throwable> onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
            }
        };

        // 옵저버 등록 3번 형태 - 람다식으로
        observable.subscribe(
                str  -> Log.e("onNext", "======================="+str),
                throwable  -> Log.e("onError", "======================="+throwable.getMessage()),
                ()  -> Log.e("onComplete", "======================= complete")
        );

    }
}
