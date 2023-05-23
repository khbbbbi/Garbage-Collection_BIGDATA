# -가비지 컬렉션 (Garbage Collection)-
## 목차
#### 1. 가비지 컬렉션이란?
## 1. 가비지 컬렉션이란?
### (1) 정의 
- 가비지 컬렉션(Garbage Collection, GC) 말 그대로 쓰레기 수집이란 뜻으로 메모리 관리 기법 중 하나이다.<br>
- 가비지(Garbage)는 앞으로 사용되지 않는 객체의 메모리를 말한다. 이런 가비지(Garbage)를 정해진 스케줄에 의해 정리해주는 것을 가비지 컬렉션(Garbage Collection, GC)이라 부른다.
### (2) 자바(Java)에서의 GC
- 자바의 메모리 관리 기법으로 어플리케이션이 동적으로 할당했던 메모리 영역 중 더이상 사용하지 않는 영역을 정리하는 기능
- GC는 Heap 메모리에서 활동하며, JVM(자바 메모리 구조)에서 GC의 스케줄링을 담당하며 개발자가 직접 관여하지 않아도 더이상 사용되지 않는 점유된 메모리를 제거해주는 역할을 담당
 
 - JVM(자바 메모리 구조)
<p align = "center"><img src = "https://github.com/khbbbbi/Garbage-Collection_BIGDATA/assets/102509150/514d4f80-c06f-4b90-853c-4c5443647c17" width="80%"></p>
 - Execution Engine에 가비지 컬렉터가 존재한다.
 가비지 컬렉터
 - 더이상 참조되지 않는 메모리 객체를 모아 제거하는 역할을 수행
 - 일반적으로 자동으로 실행, 수동으로 실행하기 위해 system.gc()를 사용할 수 있음(but 안쓰는게 베스트)
 
GC 에 대해 조사
왜 필요하고, 어떤 매커니즘으로 동작되는지 (본인이 확실히 이해한 내용을 기술)
GC 가 제대로 동작되도록 코드를 어떻게 작성해야 하는지, 어떻게 하면 GC 로도 메모리 leak 이 발생되는지 예제 코드와 함께 설명
- 언어는 Python, Java, JavaScript, C# 중 택1

> 출처
> https://mangkyu.tistory.com/118
> https://www.youtube.com/watch?v=jXF4qbZQnBc
> https://namu.wiki/w/%EC%93%B0%EB%A0%88%EA%B8%B0%20%EC%88%98%EC%A7%91
> https://ko.wikipedia.org/wiki/%EC%93%B0%EB%A0%88%EA%B8%B0_%EC%88%98%EC%A7%91_(%EC%BB%B4%ED%93%A8%ED%84%B0_%EA%B3%BC%ED%95%99)
