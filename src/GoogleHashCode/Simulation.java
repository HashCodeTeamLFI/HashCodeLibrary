package GoogleHashCode;

import java.util.*;

public class Simulation {
    int totalDays;
    Library[] libraries;
    Book[] allBooks;

    //constantly changing
    int day = 0;
    boolean shipping = false;


    int libraryCount = 0;
    ArrayList<Library> librariesScanning = new ArrayList<>();

    public Simulation(Library[] libraries, Book[] books, int availableDays) {
        this.libraries = libraries;
        this.allBooks = books;
        this.totalDays = availableDays;
    }


    public void start(){
        Library thatsShipping = null;
        for (int days = 0; days < 10; days++) {
            //scan all current books
            for (Library l : librariesScanning) {
                for (int i = 0; i < l.getScannedBooks(); i++) {
                    l.pq.poll();
                }
            }
            if(!shipping) { //find the library with the greatest points and start it!
                updatePossiblePoints();
                thatsShipping = pickBestLibrary();
                populateLibraryPriorityQueue(thatsShipping);
                thatsShipping.signUpTime -= 1;
            }
            else // it is currently shipping, so pop the books, make them visited, and
            {
                if(thatsShipping.signUpTime == 0) {
                    librariesScanning.add(thatsShipping);
                    shipping = false;
                }
            }

        }
    }

    /**
     * This method updates the MAXIMUM amount of points that a library can provide
     * During a given day
     * Precondition: the day is possible, the books may or may not have been scanned
     */
    public void updatePossiblePoints(){
        //go through all the libraries
        for (int library = 0; library < libraries.length; library++) {
            int score = 0;
            if(!libraries[library].isVisited()){
                Book[] bookArray = libraries[library].getBookList();
                for (int book = 0; book < bookArray.length && book < (totalDays - (day + libraries[library].getSignUpTime()) + 1); book++) {
                    if(!bookArray[book].isVisited()){
                        score += bookArray[book].getScore();
                        //bookArray[book].setVisited(true);
                    }
                }
            }
        }
    }

    /**
     * To be called AFTER update updatePossiblePoints
     * @return
     */
    public Library pickBestLibrary(){
        int max = libraries[0].getPossiblePoints();
        int library = 0;
        for(int i = 1; i < libraries.length; i++){
            if(max < libraries[i].getPossiblePoints()){
                library = i;
                max = libraries[i].getPossiblePoints();
            }
        }
        libraries[library].setVisited(true);
        return libraries[library];
    }

    public void populateLibraryPriorityQueue(Library lib){
        Book[] bookArray = lib.getBookList();
        for (int i = 0;  i < bookArray.length && i < (totalDays - (day + lib.getSignUpTime()) + 1); i++) {
            if (!bookArray[i].isVisited()){
                lib.pq.add(bookArray[i]);
                bookArray[i].setVisited(true);
            }


        }
    }
}