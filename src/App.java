import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static int[] createRandomArray(int arrayLength){
        int[] array = new int[arrayLength];
        Random random = new Random();
        for(int i=0; i<arrayLength; i++){
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) throws IOException{
        FileWriter writer = new FileWriter(filename);
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write("%d\n".formatted(array.length));
        for(int i=0; i<array.length; i++){            
            buffer.write("%d".formatted(array[i]));    
            if (i != array.length -1){
                buffer.write("\n");
            }         
        }         
        buffer.close();
    }

    public static int[] readFileToArray(String filename) throws IOException{
        FileReader reader = new FileReader(filename);
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        int length = Integer.parseInt(line);
        
        int[] array = new int[length];
        for(int i=0; i<array.length;i++){
            line = buffer.readLine();
            array[i] = Integer.parseInt(line);
        }        
        buffer.close();

        return array;        
    }

    public static void bubbleSort(int[] array){
        for(int i=0; i<array.length; i++){
            boolean swapped = false;
            for(int j=0; j<array.length-i-1; j++){
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;                    
                    swapped = true;
                }
            }
            if (swapped != true){                    
                break;
            } 
        }
    } 

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter length of array:");
        int arrayLength = scanner.nextInt();

        int[] array = createRandomArray(arrayLength);
        System.out.println("Generated array:");
        System.out.println(Arrays.toString(array));

        System.out.println("Enter name of file to store array:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        writeArrayToFile(array, filename);

        int[] arrayFromFile = readFileToArray(filename);
        System.out.println("Array from "+filename+": ");
        System.out.println(Arrays.toString(arrayFromFile));

        bubbleSort(arrayFromFile);
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(arrayFromFile));

        scanner.close();
    }
}
