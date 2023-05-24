import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Object obj = new Object();
            objectList.add(obj);
        }
    }
}