package org.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Playlist is an observable. It notifies an observer (Subscriber) about change of its state.
 * To be able to do that, the observable (Playlist) object needs to keep references to the observers (Subscribers).
 */
public class Playlist {
    private List<String> songs = new ArrayList<>();
    private List<ISubscriber> subscribers = new ArrayList<>();

    public void addObserver(ISubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeObserver(ISubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    /**
     * Add a song to a playlist AND notify observers (subscribers) that playlist was updated
     * @param song name of song
     */
    public void addSongToPlaylist(String song) {
        this.songs.add(song);
        for (ISubscriber subscriber : this.subscribers) {
            //pass number of songs to a method that will notify subscriber about change of playlist
            subscriber.update(this.songs.size());
        }
    }
}
