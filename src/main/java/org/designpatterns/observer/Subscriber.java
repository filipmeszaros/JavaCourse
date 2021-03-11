package org.designpatterns.observer;

/**
 * Subscriber is an observer.
 * It is forced to implement method update() which is invoked when the state of Playlist changes.
 */
public class Subscriber implements ISubscriber {
    private int numberOfSongs;

    @Override
    public void update(int numberOfSongs) {
        System.out.println("Observer (subscriber) was notified about change of value of observable (Playlist)");
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongsInPlaylist() {
        return this.numberOfSongs;
    }
}
