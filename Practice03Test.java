import java.util.Random;


public class Practice03Test {

    protected int count = 0;
    protected double[] arr;

    public static long total_recursion_time = 0;

    /**
     * Constructor
     */
    public Practice03Test(String[] args) {
        try {
            count = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Defaulting array size to 20.");
            count = 20;
        }
        arr = new double[count];
        generate_array();
    }


    /**
     * print_array: prints the array of doubles... formatted so it fits
     * ... on many small screens.
     */
    public void print_array() {
        System.out.println("------------------------------------");
        System.out.println("Array contains the values:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%.2f ", arr[i]);
            if (i > 0 && i % 9 == 0)
                System.out.println();
        }
        System.out.println("\n------------------------------------");
    }


    /**
     * Fills the array with random double instances.
     */
    public void generate_array() {
        Random rand = new Random();
        double min = 1.0;
        double max = 100.0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = min + rand.nextDouble() * (max - min);
        }
    }


    public int find_min_iterative() {
        System.out.println("=======================================starting counting runtime=======================================");
        long start = 0;
        long end = 0;
        long duration = 0;
        long total = 0;
        int min_value_index = 0;
        for (int i = 1; i < arr.length; i++) {
            start = System.nanoTime();
            if (arr[i] < arr[min_value_index]) {
                min_value_index = i;
            }
            end = System.nanoTime();
            duration = end - start;
            System.out.println(String.format("time spent on %d times of iteration is :%d", i, duration));
            total += duration;
        }
        System.out.println(String.format("the total time for find_min_iterative() is :%d, average time spent on each iteration is :%d", total, total / arr.length));
        System.out.println("=======================================finished counting runtime=======================================");
        return min_value_index;
    }

    public int find_min_recursive() {
        int index = 0;
        System.out.println("=======================================starting counting runtime=======================================");
        return find_min_recursive(index, index + 1);
    }

    private int find_min_recursive(int min_index, int index) {
        long start = System.nanoTime();
        if (index < arr.length) {
            if (arr[min_index] > arr[index]) {
                min_index = index;
            }
            long end = System.nanoTime();
            long duration = end - start;
            System.out.println(String.format("time spent on %d times of recursion is :%d", index, duration));
            total_recursion_time += duration;
            return find_min_recursive(min_index, index + 1);
        }
        System.out.println(String.format("the total time for find_min_recursive() is :%d, average time spent on each iteration is :%d", total_recursion_time, total_recursion_time / arr.length));
        System.out.println("=======================================finished counting runtime=======================================");
        return min_index;
    }


    /**
     * print_min: determines the min iteratively and recursively.
     * ... and prints them both.
     */
    public void print_min() {
        System.out.println("Iteratively determined min at index " + find_min_iterative());
        System.out.println("Recursively determined min at index " + find_min_recursive());

//        find the correct answer
//        System.out.println("Correct value: " + Collections.min(new ArrayList<Double>() {{
//            for (double d : arr) {
//                add(d);
//            }
//        }}));
    }


    /**
     * main for Practice 03: print the array and determine the min.
     */
    public static void main(String[] args) {
        Practice03Test test = new Practice03Test(args);
        test.print_array();
        test.print_min();
    }

}
