package Archive;

import Archive.Ghasemi;
import Archive.Mehdi;

import java.util.ArrayList;
import java.util.List;

public class MojtabaGhasemi extends Ghasemi implements Mehdi {

    @Override
    double calculateNumbers(double a, double b) {
        return (double) (a * b);
    }

    // implement from interface
    @Override
    public double sum(double a, double b) {
        double sumation = 0;
        try {
           sumation = a + b;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sumation;
    }

    public void getList() {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(" , "+numbers.get(i));
        }
        System.out.println("");
        System.out.println(numbers.size());
    }

//    public int sum(int a, int b) {
//        if (a == 0 && b == 0) {
//            System.out.println("error");
//        }
//        return a + b;
//    }
}
