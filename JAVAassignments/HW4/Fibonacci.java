//Joshua Mayhugh
//431004527
import static java.lang.System.out;
import java.util.Scanner;

class SubsetOutputFib {

public static void main(String[] args) 
{
    Scanner input = new Scanner(System.in);
    //ask for a number from command linw to begin fib sequence
    out.println("Enter a number to begin the Fibonacci sequence");
    int be = input.nextInt();
    //ask for a number from command line to end fib sequence
    out.println("Enter a number to end the Fibonacci sequence");
    int en = input.nextInt();

    //if be is negative take absolute value
    if (be < 0)
    {
        be = Math.abs(be);
        out.println("The beginning number is negative it is now " + be);
    }
    //if en is negative take absolute value
    if (en < 0)
    {
        en = Math.abs(en);
        out.println("The ending number is negative it is now " + en);
    }
    //if be is 0 then set it to 1
    if (be == 0)
    {
        be = 1;
        out.println("The beginning number is 0 it is now 1" );
    }
    // if be > en then swap value of be and en so that b <= n
    if (be > en)
    {
        int temp = be;
        be = en;
        en = temp;
        out.println("The beginning number is greater than the ending number so they have to be swapped");
    }

    int lo = 1;
       int hi = 1;
        String mark;
        if(be == 1)
        System.out.println("1: " + lo);

        for (int i = 2; i <= en; i++) {
            if (hi % 2 == 0)
                mark = " *";
            else
                mark = "";
            if (i >= be)
            System.out.println(i + ": " + hi + mark);
            hi = lo + hi;
            lo = hi - lo;
        }
    

//close scanner
input.close();


}

}
// Modify the ImprovedFibonacci application to store its sequence in an array. 
// Do this by creating a new class to hold both the value and a boolean value that says whether the value is even, 
// and then having an array of object references to objects of that class.
class ImprovedFibonacci{
    static final int MAX_INDEX = 9;
    // private values one that holds the value and one that holds the boolean value
    private int value;
    private boolean even;
    // constructor
    public ImprovedFibonacci(int value){
        this.value = value;
        //if it is even then even is true
        if(value % 2 == 0){
            even = true;
        }
        else{
            even = false;
        }
    }
    public static void main(String[] args) {
        // array of object references
        ImprovedFibonacci[] fib = new ImprovedFibonacci[MAX_INDEX];
        // initialize the array
        //setting the first two values to 1
        fib[0] = new ImprovedFibonacci(1);
        fib[1] = new ImprovedFibonacci(1);
        for (int i = 2; i < MAX_INDEX; i++) {
            fib[i] = new ImprovedFibonacci(fib[i-1].value + fib[i-2].value);
        }
        
        // print the array
        for (int i = 0; i < MAX_INDEX; i++) {
            System.out.println("f[" + i + "] = " + fib[i].value + " " + fib[i].even);
        }
}
}
