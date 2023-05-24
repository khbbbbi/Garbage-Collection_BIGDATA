// 1. Object 클래스의 두 개의 객체(obj1과 obj2)를 생성
// 2. 이 객체들의 참조를 해제
// 3. 이후 메모리 부족 상황을 유도하기 위해 많은 수의 임시 객체를 생성
// 4. 마지막으로 System.gc()를 호출하여 GC 실행

public class GCExample1 {
    public static void main(String[] args) {
        // 객체 생성
        Object obj1 = new Object();
        Object obj2 = new Object();

        // obj1과 obj2에 대한 참조 해제
        obj1 = null;
        obj2 = null;

        // GC 실행을 유도하기 위해 메모리 부족 상황 생성
        for (int i = 0; i < 1000000; i++) {
            new Object();
        }

        // GC 호출
        System.gc();
    }
}