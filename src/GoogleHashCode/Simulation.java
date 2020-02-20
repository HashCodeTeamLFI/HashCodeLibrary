package GoogleHashCode;

public class Simulation {
    int totalDays;
    Library[] libraries;
    Book[] allBooks;

    //constantly changing
    int day = 0;
    boolean shipping = false;

    public Simulation(Library[] libraries, Book[] books, int availableDays) {
        this.libraries = libraries;
        this.allBooks = allBooks;
        this.totalDays = availableDays;
    }


    public void start(){
        for (int days = 0; days < 10; days++) {
            updatePossiblePoints();
            if(!shipping) { //find the library with the greatest points and start it!

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

        }
    }
}