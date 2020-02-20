package GoogleHashCode;

public class Book {
    private int id;
    private int score;
    private boolean visited;

    public Book(int id, int score) {
        this.id = id;
        this.score = score;
        this.visited = visited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}