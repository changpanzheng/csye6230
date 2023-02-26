import java.util.Arrays;
import java.util.Random;

public class ArraySumParallelSerial {
    public static void main(String[] args) {
        // Initialize the array with some values
        double[] arr = new double[5];
        Arrays.fill(arr, 1.0);

        // Multiply every value in the array with a random value between 0.1 and 0.9
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= rand.nextDouble() * 0.8 + 0.1;
            // System.out.println(i + " -> " + arr[i]);
        }

        // Calculate the sum of the array in serial
        long startSerial = System.currentTimeMillis();
        double sumSerial = Arrays.stream(arr).sum();
        long endSerial = System.currentTimeMillis();
        long serialTime = endSerial - startSerial;

        // Calculate the sum of the array in parallel
        long startParallel = System.currentTimeMillis();
        double sumParallel = Arrays.stream(arr).parallel().sum();
        long endParallel = System.currentTimeMillis();
        long parallelTime = endParallel - startParallel;

        // Print the results
        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Sum in serial: " + sumSerial);
        System.out.println("Sum in parallel: " + sumParallel);
        System.out.println("Time difference: " + (parallelTime - serialTime) + " ms");
        System.out.println("Conclusion: Sum in " + (parallelTime <= serialTime ? "Parallel" : "Serial") + " cost less time.");
    }
}



// // another method: write a method to do parallel sum
// // Parallel sum of the array using multiple threads
// public static double sumArrayParallel(double[] arr) {
//     final int numThreads = Runtime.getRuntime().availableProcessors();
//     final int chunkSize = arr.length / numThreads;
//     Thread[] threads = new Thread[numThreads];
//     double[] partialSums = new double[numThreads];

//     // Create and start threads
//     for (int i = 0; i < numThreads; i++) {
//         final int start = i * chunkSize;
//         final int end = i == numThreads - 1 ? arr.length : (i + 1) * chunkSize;
//         threads[i] = new Thread(() -> {
//             double partialSum = 0.0;
//             for (int j = start; j < end; j++) {
//                 partialSum += arr[j];
//             }
//             partialSums[i] = partialSum;
//         });
//         threads[i].start();
//     }

//     // Wait for threads to finish
//     try {
//         for (int i = 0; i < numThreads; i++) {
//             threads[i].join();
//         }
//     } catch (InterruptedException e) {
//         e.printStackTrace();
//     }

//     // Combine partial sums
//     double sum = 0.0;
//     for (int i = 0; i < numThreads; i++) {
//         sum += partialSums[i];
//     }
//     return sum;
// }