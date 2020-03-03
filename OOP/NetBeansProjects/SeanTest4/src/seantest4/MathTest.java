package seantest4;

public class MathTest {
    
    
    public int getFactorial(int number) {
        System.out.println("Number is: " + number);
        if (number == 0)
            return 1;
        else 
            return number * getFactorial(--number);
    }
    
}
