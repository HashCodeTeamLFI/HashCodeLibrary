/* initial commit for hashcode

 */
package GoogleHashCode;

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class LibraryMain {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("a_example.txt");
        Scanner input = new Scanner(inputFile);
        String firstLine = input.nextLine();
        String[] arr = firstLine.split(" ");
        int numBooks = Integer.parseInt(arr[0]);
        int numLibraries = Integer.parseInt(arr[1]);
        int numDays = Integer.parseInt(arr[2]);
        String secondLine = input.nextLine();
        String[] scores = secondLine.split(" ");
        Book[] books = new Book[numBooks];
        for (int i = 0; i < numBooks; i++) {
            int score = Integer.parseInt(scores[i]);
            books[i] = new Book(i, score);
        }
        // Library sections
        ArrayList<Library> libraries = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (line.length() == 5) {
                String[] x = line.split(" ");
                int amountBooks = Integer.parseInt(x[0]);
                int signupDays = Integer.parseInt(x[1]);
                int shippingBooks = Integer.parseInt(x[2]);
                line = input.nextLine();
                x = line.split(" ");
                Book[] bookIDs = new Book[amountBooks];
                for (int i = 0; i < amountBooks; i++) {
                    for (int j = 0; j < books.length; j++) {
                        if (books[j].getId() == Integer.parseInt(x[i])) {
                            bookIDs[i] = books[j];
                            break;
                        }
                    }
                }
                Library lib = new Library(bookIDs, signupDays, shippingBooks);
                libraries.add(lib);
            }
        }
        System.out.println("test");
    }
}
