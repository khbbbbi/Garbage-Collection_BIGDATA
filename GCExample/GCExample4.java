// 메모리 할당과 GC 호출을 통해 GC의 동작을 확인
// 1. allocateMemory 메서드는 메모리를 할당하여 objectList에 객체를 추가 -> 메모리 사용량 증가
// 2. triggerGC 메서드는 objectList를 비우고 System.gc()를 호출하여 GC를 실행
// 3. main 메서드에서는 메모리를 할당하는 allocateMemory 메서드를 호출
// 4. 일부 객체를 추가하여 GC 이후에도 일부 객체가 살아있는 상태로 유지
// 5. 마지막으로 다시 triggerGC 메서드를 호출하여 GC를 실행

import java.util.ArrayList;
import java.util.List;

public class GCExample4 {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        allocateMemory();
        triggerGC();

        // GC 이후에도 일부 객체는 살아있는 상태로 유지
        for (int i = 0; i < 1000; i++) {
            objectList.add(new Object());
        }

        // GC 호출
        triggerGC();
    }

    private static void allocateMemory() {
        for (int i = 0; i < 10000; i++) {
            objectList.add(new Object());
        }
    }

    private static void triggerGC() {
        objectList.clear();
        System.gc();
    }
}
