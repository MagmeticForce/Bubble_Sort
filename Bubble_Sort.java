import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Bubble_Sort {

    public static void swap (int[] array_argument, int index_to_swap_places_with_1, int index_to_swap_places_with_2) {
        int temp = array_argument[index_to_swap_places_with_1];
        array_argument[index_to_swap_places_with_1] = array_argument[index_to_swap_places_with_2];
        array_argument[index_to_swap_places_with_2] = temp;
    }


    public static boolean is_sorted (int[] array_argument) {
        for (int a = 0; a < array_argument.length - 1; a++) {
            if (array_argument[a] > array_argument[a+1]) {
                return false;
            }
        }
        return true;
        //this cannnot be put in an "else" statement or there will be an error
        //however, it is unecessary to put it in an else stateemnt because...
        //...if the "if" statement is true, the function will stop and output "false". ...
        //...It won't ever re-evaluate to "true"
    }

    //makes it so that you simply need to specify an array whenever you call bubble_sort
    public static void bubble_sort (int[] array_argument) {
        bubble_sort (array_argument, 0, array_argument.length);
    }

    public static void bubble_sort (int[] array_argument, int start_argument, int end_argument) {
        
        //Terminating condition.
        if (end_argument - start_argument <= 1) {return;}
        
        //Take two neighboring values in the array and compare them
        //If the lefthand value is bigger than the righthand value, swap the values' places
        //Now the righthand value should be the bigger value
        //Repeat this for every neighboring two values in the array
        //This makes it so that the biggest number in the array is moved to the end
        //Also, in the loop teerminating condition, it must be end_argument - 1...
        //...because if it reaches the last index, there will be no right-hand...
        //...value to compare it to.
        for (int a = start_argument; a < end_argument - 1; a++) {
            if (array_argument[a] > array_argument[a+1]) {
                swap (array_argument, a, a+1);
            }
        }

        //A recursive call of bubble_sort
        //Makes it so that the function keeps calling itself, forming a loop
        //Except notice the "end - 1". This makes it so that every iteration of...
        //...bubble sorting, the array size is 1 smaller. This is necessary because...
        //...it makes it so that it keeps moving the largest numbers to the end while...
        //...not affcting the numbers at the end that are already in order
        bubble_sort(array_argument, start_argument, end_argument - 1);
    }

    public static int[] create_a_random_array (int length_argument) {
        int[] random_array = new int [length_argument];
        Random random_object = new Random(length_argument);

        //give each array index a random number between 0 and 100
        for (int a = 0; a < random_array.length; a++) {
            random_array[a] = random_object.nextInt(0, 100);
        }

        return random_array;
    }

    
    public static void write_array_to_file (int[] array_argument, String file_name_argument) throws IOException {
        FileWriter file_writer = new FileWriter(file_name_argument);

        //goes through each element of thre array argument and writes it on a new line of the file
        //the "array_argument.length - 1" and the extra "file_writer.write" after the loop prevents...
        //...an extra empty line from being printed
        for (int a = 0; a < array_argument.length; a++) {
            if (a < array_argument.length - 1) {
                file_writer.write(array_argument[a] + "\n");
            }
            else {
                file_writer.write(array_argument[a] + ""); //the + "" is necessary or else it writes "a" to the file for some reason
            }
        }

        file_writer.close();

    }

    public static int[] read_array_from_file (String file_name_argument) throws FileNotFoundException {
        Scanner file_reader = new Scanner (new File (file_name_argument));
        ArrayList<Integer> array_list = new ArrayList<>();
        while (file_reader.hasNextLine()) {
            array_list.add(file_reader.nextInt());
        }
        file_reader.close();
        //System.out.println (array_list.get(9));
        int[] array_to_return = new int [array_list.size()];
        for (int a = 0; a < array_list.size(); a++) {
            array_to_return[a] = array_list.get(a);
        }
        return array_to_return;

    }

    public static void main (String[] args) throws Exception {
        int[] array_to_sort = create_a_random_array(10);

        System.out.println("Welcome!");
        System.out.println("Type 'print to [your filename here]' to print a list of random numbers to a file.");
        System.out.println("Please note that your filename cannot have spaces. You can use underscores as an alternative.");
        System.out.println("Type 'sort [your filename here] and store in [your other filename here]' to sort a list of numbers from a file and");
        System.out.println("put the sorted numbers into another file.");
        System.out.println("Type 'exit' to exit.");
        System.out.println("Please make sure to give file extensions in your file names or else it won't work properly.");
        System.out.println ("So for example, adding a '.txt' at the end of your file name.");

        Scanner input_detector = new Scanner(System.in);
        String user_input = "0";
        String[] user_input_in_an_array;
        int[] new_array;

        while (1==1) {

            System.out.println("Enter what you would like to do:");
            
            user_input = input_detector.nextLine();

            if (user_input.equals("exit")) {
                input_detector.close();
                break;
            }

            user_input_in_an_array = user_input.split (" ");
            
            if (user_input_in_an_array[0].equals("print") && user_input_in_an_array[1].equals("to")) {
                write_array_to_file(array_to_sort, user_input_in_an_array[2]);
                System.out.println ("Successfully printed to file!");
            }
            else if (user_input_in_an_array[0].equals("sort") && user_input_in_an_array[2].equals("and") && user_input_in_an_array[3].equals("store") && user_input_in_an_array[4].equals("in")) {
                //1 isa file name to sort, 5 is file to store in
                new_array = read_array_from_file (user_input_in_an_array[1]);
                bubble_sort(new_array);
                write_array_to_file(new_array, user_input_in_an_array[5]);
                System.out.println ("Successfully sorted and printed to file!");
            }
            else {
                System.out.println("Sorry, your input could not be processed.");
                System.out.println("To fix this, please ensure that your input matches what was given in the instructions at the very top.");
                continue;
            }
        }

        /*
        System.out.println(array_to_sort[array_to_sort.length-1]);
        System.out.println(Arrays.toString(array_to_sort)); //unsorted array
        System.out.println(is_sorted(array_to_sort));
        bubble_sort(array_to_sort);
        System.out.println(Arrays.toString(array_to_sort)); //sorted array
        System.out.println(is_sorted(array_to_sort));
        write_array_to_file(array_to_sort, "sorted_array.txt");
        
        int[] new_array = read_array_from_file("sorted_array.txt");
        System.out.println (Arrays.toString(new_array));
        System.out.println (is_sorted(new_array));
        */
    }
}