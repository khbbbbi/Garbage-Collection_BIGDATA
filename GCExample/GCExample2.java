// 1. ArrayList인 objectList를 사용하여 Object 객체들을 저장
// 2. 반복문을 통해 여러 개의 객체를 생성하고 objectList에 추가한 후, 일부 객체의 참조를 해제
// 3. GC가 더 이상 필요하지 않은 객체들을 회수
// 4. 마지막으로 System.gc()를 호출하여 GC실행

import java.util.ArrayList;
import java.util.List;

public class GCExample2 {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            // 객체 생성 및 objectList에 추가
            Object obj = new Object();
            objectList.add(obj);

            // 일부 객체에 대한 참조 해제
            if (i % 1000 == 0) {
                objectList.remove(0);
            }
        }

        // GC 호출
        System.gc();
    }
}