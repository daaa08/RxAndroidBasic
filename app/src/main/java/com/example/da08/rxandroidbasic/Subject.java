package com.example.da08.rxandroidbasic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Da08 on 2017. 7. 18..
 */

/*
  옵저버 패턴 이해하기
  1초 한번씩 등록된 옵저버들에게 메세지를 날린다
*/
public class Subject extends Thread{

    List<Observer> observers = new ArrayList<>();   // 옵저버를 등록하는 저장소
    Boolean run = true;

    @Override
    public void run() {
        while (run) {
            try {
                Thread.sleep(1000);
                for (Observer observer : observers) {
                    observer.notification("hello");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 옵저버를 등록하는 함수
    public void addObserver(Observer observer){
        observers.add(observer);
    }

    // 옵저버를 공지하는 함수
    public interface Observer{
        public void notification(String msg);
    }
}
