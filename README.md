# -가비지 컬렉션(Garbage Collection) in Java-
## 목차
#### 1. 가비지 컬렉션이란?
 (1) 정의<br>
 (2) 장단점<br>
 (3) 가비지 컬렉션(GC) 대상<br>
 (4) 가비지 컬렉션(GC) 청소 방식<br>
#### 2. 가비지 컬렉션 동작 과정
 (1) JVM(Java Virtual Machine)<br>
 (2) 자바에서의 GC<br>

<br>

## 1. 가비지 컬렉션이란?
### (1) 정의
- 자바의 메모리 관리 방법 중 하나로 JVM(자바 가상 머신)의 Heap 영역에서 동적으로 할당했던 메모리 중 필요 없게 된 메모리 객체(garbage)를 모아 주기적으로 제거하는 프로세스를 말한다.
- C/C++ 언어에서는 이러한 가비지 컬렉션이 없어 수동으로 메모리 할당과 해제를 해줘야한다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/95fc3fa1-be2c-49f2-ad14-8eaca44ccd19" width="50%"></p>

<br>

### (2) 장단점
<장점>
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

### (4) 가비지 컬렉션(GC) 청소 방식
- Mark And Sweep : GC에서 사용되는 객체를 골라내는 내부 알고리즘<br>
GC 대상 객체를 식별(Mark)하고 제거(Sweep)하며 객체가 제거되어 파편화된 메모리 영역을 앞에서부터 채워나가는 작업(Compaction)을 수행하게 된다.
<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/63e0236f-d3b5-425b-8899-e0c5a1720511" width="70%"></p><br>

 - Mark 과정 : 먼저 Root Space로부터 그래프 순회를 통해 연결된 객체들을 찾아내어 각각 어떤 객체를 참조하고 있는지 찾아서 마킹한다.
 - Sweep 과정 : 참조하고 있지 않은 객체 즉 Unreachable 객체들을 Heap에서 제거한다.
 - Compact 과정 : Sweep 후에 분산된 객체들을 Heap의 시작 주소로 모아 메모리가 할당된 부분과 그렇지 않은 부분으로 압축한다. (가비지 컬렉터 종류에 따라 하지 않는 경우도 있다.)
<br>

<p align="center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/0d1073a0-cf6e-4fa4-b29f-929bc0b84420" width="70%"></p><br>

<br>

![image](https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/7cba337e-d071-4d06-a89c-26298a1d8e3c)

![image](https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/087f48af-259c-44b6-810d-d1caca05f126)


## 2. 가비지 컬렉션 장단점
 
GC 에 대해 조사
왜 필요하고, 어떤 매커니즘으로 동작되는지 (본인이 확실히 이해한 내용을 기술)
GC 가 제대로 동작되도록 코드를 어떻게 작성해야 하는지, 어떻게 하면 GC 로도 메모리 leak 이 발생되는지 예제 코드와 함께 설명
- 언어는 Python, Java, JavaScript, C# 중 택1

> [ 출처 ]<br>
> 유튜브 : <a href = "https://youtu.be/jXF4qbZQnBc">자바의 메모리 관리 방법! 가비지 컬렉션[자바 기초 강의]</a><br>
> 티스토리 : <a href = "https://coding-factory.tistory.com/829">[Java] 가비지 컬렉션(GC, Garbage Collection) 총정리</a><br>
