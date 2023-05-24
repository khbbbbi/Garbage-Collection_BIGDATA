# -가비지 컬렉션(Garbage Collection) in Java-
## 목차
#### 1. 가비지 컬렉션이란?
 (1) 정의<br>
 (2) 장단점(필요성)<br>
 (3) 가비지 컬렉션(GC) 대상<br>
 (4) 가비지 컬렉션(GC) 청소 방식(메커니즘)<br>
#### 2. 가비지 컬렉션 동작 과정
 (1) heap 메모리 구조<br>
 (2) Minor GC 과정<br>
 (3) Major GC 과정<br>
 
<br>

## 1. 가비지 컬렉션이란?
### (1) 정의
- 자바의 메모리 관리 방법 중 하나로 JVM(자바 가상 머신)의 Heap 영역에서 동적으로 할당했던 메모리 중 필요 없게 된 메모리 객체(garbage)를 모아 주기적으로 제거하는 프로세스를 말한다.
- C/C++ 언어에서는 이러한 가비지 컬렉션이 없어 수동으로 메모리 할당과 해제를 해줘야한다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/95fc3fa1-be2c-49f2-ad14-8eaca44ccd19" width="50%"></p>

<br>

### (2) 장단점(필요성)
<장점(필요성)>
Java는 가비지 컬렉터가 메모리 관리를 대행해주기 때문에 Java 프로세스가 한정된 메모리를 효율적으로 사용할 수있게 하고, 개발자 입장에서 메모리 관리, 메모리 누수 문제에 대해 관리하지 않아도 된다는 장점이 있다.

ex) <br>

```java
for (int i = 0; i < 10000; i++) {
  NewObject obj = new NewObject();  
  obj.doSomething();
}
```

위 코드를 보면 for문에 의해 10000건의 NewObject 객체가 생성되고 사용되지만, loop가 끝나면 루프 밖에서는 사용할 일이 없다.<br><br>
만약 이 객체들이 메모리에 계속 남아있다면 다른 코드를 실행하기 위한 메모리 자원은 줄어들 것이다.<br><br>
하지만 이런 경우에 <b>가비지 컬렉션(GC)이 한번쓰이고 버려지는 객체들을 주기적으로 비워줌으로써 한정된 메모리를 효율적으로 사용할 수 있게 해주기 때문에</b> 우린 별다른 작업을 하지 않아도 된다.<br>

<br>
<단점><br>
자동으로 처리해준다고 해도 메모리가 언제 해제되는지 정확하게 알 수 없어 제어하기 힘들며, GC가 동작하는 동안 Stop The World가 일어나 오버헤드가 발생되는 문제점이 있다.<br><br>
- <b>Stop The World란?</b><br>
GC를 수행하기 위해 JVM이 프로그램 실행을 멈추는 현상을 의미한다. GC가 작동하는 동안 GC 관련 thread를 제외한 모든 thread는 멈추게 되어 서비스 이용에 차질이 생길 수 있다. 따라서 이 시간을 최소화 시키는 것이 쟁점이다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/0703d03d-6203-4a72-b136-a7c15449e1dc" width="50%"></p><br>
위 단점들로 인해 GC가 너무 자주 실행되면 소프트웨어 성능 하락의 문제가 되기도 한다.<br>

```
✏️ ex) 익스플로러 : 가비지 컬렉션을 너무 자주 실행해 성능 문제를 일으키는 것으로 악명이 높았다.
```

<br>

### (3) 가비지 컬렉션(GC) 대상
- GC는 특정 객체가 garbage인지 아닌지 판단하기 위해 도달성, 도달능력이라는 개념을 적용한다.
- 객체에 레퍼런스가 있다면 Reachable로, 객체에 유효한 레퍼런스가 없다면 UnReachable로 구분해버리고 수거해버린다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/6b6ea876-5980-4280-b930-9cb3f0ebf156" width="70%"></p><br>

<br>

### (4) 가비지 컬렉션(GC) 청소 방식(메커니즘)
- Mark And Sweep : GC에서 사용되는 객체를 골라내는 내부 알고리즘<br>
GC 대상 객체를 식별(Mark)하고 제거(Sweep)하며 객체가 제거되어 파편화된 메모리 영역을 앞에서부터 채워나가는 작업(Compaction)을 수행하게 된다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/63e0236f-d3b5-425b-8899-e0c5a1720511" width="70%"></p><br>

 - Mark 과정 : 먼저 Root Space로부터 그래프 순회를 통해 연결된 객체들을 찾아내어 각각 어떤 객체를 참조하고 있는지 찾아서 마킹한다.
 - Sweep 과정 : 참조하고 있지 않은 객체 즉 Unreachable 객체들을 Heap에서 제거한다.
 - Compact 과정 : Sweep 후에 분산된 객체들을 Heap의 시작 주소로 모아 메모리가 할당된 부분과 그렇지 않은 부분으로 압축한다. (가비지 컬렉터 종류에 따라 하지 않는 경우도 있다.)
<br>
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/a2886ba9-428a-430c-a153-bd9af4230698" width = "70%"></p>

<br>

## 2. 가비지 컬렉션 동작 과정
### (1) heap 메모리 구조
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/c92e6136-dee1-4e78-a83b-cf402d19165e" width = "70%"></p><br>
JVM의 힙(heap) 영역은 동적으로 레퍼런스 데이터가 저장되는 공간으로, 가비지 컬렉션에 대상이 되는 공간이다.<br>
<br>
Heap 영역은 처음 설계될 때 다음 2가지를 전제로 설계되었다.
> 1. 대부분의 객체는 금방 접근 불가능한 상태(Unreachable)가 된다.
> 2. 오래된 객체에서 새로운 객체로의 참조는 아주 적게 존재한다.
<br>
즉, 객체는 대부분 일회성이며, 메모리에 오랫동안 남아있는 경우는 드물다는 것이다.<br>
<br>
이러한 특성을 이용해 Heap 영역을 Young과 Old 총 2가지 영역으로 설계하였다.<br>
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/83f47f4f-c23c-4108-8683-d861a743a302" width = "70%"></p><br>

```
✏️ Old 영역이 Young 영역보다 크게 할당되는 이유는 Young 영역의 수명이 짧은 객체들은 큰 공간을 필요로 하지 않으며 큰 객체들은 Young 영역이 아니라 바로 Old 영역에 할당되기 때문!!
```

<br>

#### < Young 영역 >
- 새롭게 생성된 객체가 할당(Allocation)되는 영역
- 대부분의 객체가 금방 Unreachable 상태가 되기 때문에, 많은 객체가 Young 영역에 생성되었다가 사라진다.
- Young 영역에 대한 가비지 컬렉션(Garbage Collection)을 <b>Minor GC</b>라고 부른다.

<br>

#### < Old 영역 >
- Young영역에서 Reachable 상태를 유지하여 살아남은 객체가 복사되는 영역
- Young 영역보다 크게 할당되며, 영역의 크기가 큰 만큼 가비지는 적게 발생한다.
- Old 영역에 대한 가비지 컬렉션(Garbage Collection)을 <b>Major GC 또는 Full GC</b>라고 부른다.
<br>
또 다시 힙 영역은 더욱 효율적인 GC를 위해 Young 영역을 3가지 영역(Eden, survivor 0, survivor 1) 으로 나눈다.<br>
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/d57286a1-08d7-40df-a5ac-88e307b1561f" width = "70%"></p><br>
이렇게 하나의 힙 영역을 세부적으로 쪼갬으로서 객체의 생존 기간을 면밀하게 제어하여 가비지 컬렉터(GC)를 보다 정확하게 불필요한 객체를 제거하는 프로세스를 실행하도록 한다.<br>
<br>

### (2) Minor GC 과정


### (3) Major GC 과정

> [ 출처 ]<br>
> 유튜브 : <a href = "https://youtu.be/jXF4qbZQnBc">자바의 메모리 관리 방법! 가비지 컬렉션[자바 기초 강의]</a><br>
> 티스토리 : <a href = "https://coding-factory.tistory.com/829">[Java] 가비지 컬렉션(GC, Garbage Collection) 총정리</a><br>
