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
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/cfe25013-4587-463e-af73-e7e8ce5439a5" width = "70%"></p><br>

Young Generation 영역은 짧게 살아남는 메모리들이 존재하는 공간이다.<br>
모든 객체는 처음에는 Young Generation에 생성되게 된다.<br>
Young Generation의 공간은 Old Generation에 비해 상대적으로 작기 때문에 메모리 상의 객체를 찾아 제거하는데 적은 시간이 걸린다.(작은 공간에서 데이터를 찾으니까)<br><br>
이 때문에 Young Generation 영역에서 발생되는 GC를 Minor GC라 불린다.<br>
<br>
① 처음 생성된 객체는 Young Generation 영역의 일부인 Eden 영역에 위치<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/0c49cedd-a6e9-4ab3-b016-43d7491b6cf2" width = "70%"><br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/bf193d9a-f5af-41d1-a67d-553b37cb3198" width = "70%"><br>

② 객체가 계속 생성되어 Eden 영역이 꽉차게 되고 Minor GC가 실행<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/8a53fcc1-95dc-4fb1-86a7-87c91f2788bc" width = "70%"><br>

③ Mark 동작을 통해 reachable 객체를 탐색<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/cde63103-f51d-42b6-a120-9ee2a469ead7" width = "70%"><br>

④ Eden 영역에서 살아남은 객체는 1개의 Survivor 영역으로 이동<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/55a6fa69-f1bd-4b2d-8387-07554d1a109b" width = "70%"><br>

⑤ Eden 영역에서 사용되지 않는 객체(unreachable)의 메모리를 해제(sweep)<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/998874ec-0c68-4689-8e77-ca2b501d110e" width = "70%"><br>

⑥ 살아남은 모든 객체들은 age값이 1씩 증가<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/4a0acd32-f038-42a3-8bf7-44ec57c609bd" width = "70%"><br>

⑦ 또다시 Eden 영역에 신규 객체들로 가득 차게 되면 다시한번 minor GC 발생하고 mark 한다<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/cea2ebe1-834b-45dc-a212-acb658230d9f" width = "70%"><br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/07cc73a4-4ce3-4f41-9046-aca1ced675af" width = "70%"><br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/236edaf2-c973-4c15-a4fe-fb50e5060b51" width = "70%"><br>

⑧ marking 한 객체들을 비어있는 Survival 1으로 이동하고 sweep<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/4d9ed381-f833-46db-97f1-489e55a13c99" width = "70%"><br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/13368630-d5e8-41f1-a90d-19d7ab3efc90" width = "70%"><br>

⑨ 다시 살아남은 모든 객체들은 age가 1씩 증가<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/c3a4c875-82b7-4ffd-94be-a88b9d85061d" width = "70%"><br>

이러한 과정을 반복<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/5b069d78-b2ac-46e3-8d7f-575a35b21fff" width = "70%"><br>

<br>

### (3) Major GC 과정
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/29a13510-23cc-4add-80d6-9d1e6823e782" width = "70%"></p><br>
Old Generation은 길게 살아남는 메모리들이 존재하는 공간이다.<br>
Old Generation의 객체들은 거슬러 올라가면 처음에는 Young Generation에 의해 시작되었으나, GC 과정 중에 제거되지 않은 경우 age 임계값이 차게되어 이동된 녀석들이다.<br>
그리고 Major GC는 객체들이 계속 Promotion되어 Old 영역의 메모리가 부족해지면 발생하게 된다.<br>
<br>
+Minor GC와 Major GC의 차이점<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/0aa65461-162e-47c7-b4b9-27f95b2e58e1" width = "50%"><br>
<br>
①객체의 age가 임계값(여기선 8로 설정)에 도달하게 되면,<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/56d67790-19f9-490f-b56e-33a2e172c63f" width = "70%"><br>

② 이 객체들은 Old Generation 으로 이동된다. 이를 promotion 이라 부른다.<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/304a5536-eb6f-49fc-ae1d-aee381513e36" width = "70%"><br>

③ 위의 과정이 반복되어 Old Generation 영역의 공간(메모리)가 부족하게 되면 Major GC가 발생되게 된다.<br>
<img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/c79f75b5-b133-46b7-86fe-b7b053611a8c" width = "70%"><br>

<br>

### (4) 일반적인 GC 과정
① 맨 처음 객체가 생성되면 Eden 영역에 생성<br>

② 그리고 GC의 일종인 Minor GC가 발생하면 미사용 객체의 제거와 함께 아직 사용되고 있는 객체는 Survivor1, Survivor2 영역으로 이동시킴<br>

단, 객체의 크기가 Survivor 영역의 크기보다 클 경우에는 바로 Old Generation으로 이동<br>

③ 운영 특성 상, Survivor1과 Survivor2 영역은 둘 중 한 곳에만 객체가 존재하게끔 운영되며, 다른 한 곳은 비워져 있어야 함.<br>

보통 From, To로 구분을 하며, 객체가 존재하는 Survivor영역(From)이 가득 차면 다른 Survivor 영역(To)로 보내고 기존의 Survivor 영역(From)을 비우는 작업을 진행.<br>

④ 위 과정 (1~3번)을 반복하면서 Survivor 영역에서 계속 살아남은 객체들에게 일정 score가 누적이 되어 기준치 이상이 되면 Old Generation 영역으로 이동하게 됨(Promotion)<br>

⑤ Old Generation 영역에서 살아남았던 객체들이 일정 수준 쌓이게 되면, 미사용으로 판단된 객체들을 제거해주는 Full GC가 발생. 이 과정에서 STW(Stop the World)가 발생하게 됨.<br>

> [ 출처 ]<br>
> 유튜브 : <a href = "https://youtu.be/jXF4qbZQnBc">자바의 메모리 관리 방법! 가비지 컬렉션[자바 기초 강의]</a><br>
> 티스토리 : <a href = "https://coding-factory.tistory.com/829">[Java] 가비지 컬렉션(GC, Garbage Collection) 총정리</a><br>
