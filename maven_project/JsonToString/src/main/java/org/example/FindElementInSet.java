import java.util.*;

public class FindElementInSet {
    public static void main(String[] args) {
        Set<String> stringSet = new HashSet<>();
        
        // Thêm các phần tử vào Set
        stringSet.add("A");
        stringSet.add("B");
        stringSet.add("C");
        stringSet.add("D");
        for (String element: stringSet){
            System.out.println(element);
        }
        
        // Tìm phần tử trong Set
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap phan tu can tim ");
        String elementToFind = sc.nextLine();
        
        if (stringSet.contains(elementToFind)) {
            System.out.println("Phần tử " + elementToFind + " được tìm thấy trong Set.");
        } else {
            System.out.println("Phần tử " + elementToFind + " không tồn tại trong Set.");
        }
    }
}
