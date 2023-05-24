// 1. 무한 반복문을 실행하면서 Object 객체들을 생성하고 objectList에 추가
// 2. objectList의 크기가 일정 개수(여기서는 1000)를 넘어가면 처음부터 일부 객체에 대한 참조를 해제
// 3. GC가 필요 없는 객체들을 회수

import java.util.ArrayList;
import java.util.List;

public class GCExample3 {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            // 객체 생성 및 objectList에 추가
            Object obj = new Object();
            objectList.add(obj);

            // 일부 객체에 대한 참조 해제
            if (objectList.size() > 1000) {
                objectList.subList(0, 500).clear();
            }
        }
    }
}
