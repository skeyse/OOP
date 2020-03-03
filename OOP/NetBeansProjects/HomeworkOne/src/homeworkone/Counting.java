package homeworkone;

public class Counting {
    
    public void consecutive() {
        for (int i = 1; i <= 20; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }
    
    public void evens() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i * 2);
        }
    }
    
    public void exponents() {
        for (int i = 1; i <= 10; i++) {
            int result = 1;
            for (int i2 = 1; i2 <= i; i2++) {
                result *= 2;
            }
            System.out.println(result);
        }
    }
}
