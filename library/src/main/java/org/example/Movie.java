package org.example;

public class Movie extends LibraryItem{
    private final String director;
    private final int duration;
    private static int count;

    public Movie(String title, String director, int duration) {
        super(title);
        this.director = director;
        this.duration = duration;
        count++;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("Movie: %s directed by %s. duration: %d", title, director, duration);
    }
}
