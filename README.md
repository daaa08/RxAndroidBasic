## RxJava
- ReactiveX
- 비동기 및 이벤트 기반 프로그램을 작성하기위한 라이브러리
- __Observable__
: 데이터,이벤트 스트림을 보내는 클래스
: 여러개의 Subscriber를 가질 수 있음
- __Subscriber__
: 받는 데이터에따라 행동하는 클래스
: 받은 데이터는 onNext()메소드에 의해 처리가 됨
: 완료시에는 onCompleted()
: 에러시에는 onError()

> 사용시 Gradle에 추가
```java
compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
```
> Observable 생성
```java
private void createObservable(){
       Observable.create(new ObservableOnSubscribe<String>() {
           @Override
           public void subscribe(ObservableEmitter<String> e) throws Exception {   // observer의 subject 역할
               e.onNext("Hello Rx");
               e.onNext("Yoyo");
               e.onComplete();  // 종료
           }
       });

       // 위의 내용을 람다식으로 바꿈
    observable = observable.create(emitter -> {
           emitter.onNext("Hellr Rx");
           emitter.onNext("Yoyo");
           emitter.onComplete();
     });
```

> Observable 등록
```java
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
```
```java
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

```

```java
// 옵저버 등록 3번 형태 - 람다식으로
        observable.subscribe(
                str  -> Log.e("onNext", "======================="+str),
                throwable  -> Log.e("onError", "======================="+throwable.getMessage()),
                ()  -> Log.e("onComplete", "======================= complete")
        );
```
![enter image description here](https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/SQo/image/rRJfoEWA1zixjuCJQbaIF_qvBRc.png)

![enter image description here](http://apprize.info/google/asynchronous/asynchronous.files/image027.jpg)
