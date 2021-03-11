package org.designpatterns.observer;

import static org.testng.Assert.assertEquals;

/**
 * Observer is a behavioural OOP design pattern.
 * It specifies communication between objects: observer and observables. (Observer monitors observables).
 * How it works: An observable is an object which notifies observers about the changes in its state.
 * How it doesn't work: Observer is a an object that will automatically notice that observable has changed.
 *
 * To be able to do that, the observable object needs to keep references to the observers.
 *
 * In our example:
 * Playlist (observable) will notify Subscribers (observers) when new song is added to a playlist.
 * Subscriber (observer) is forced to implement method update(), which is called by Playlist each time playlist is changed.
 */
public class ObserverExample {

    public static void main(String[] args) {
        Playlist observable = new Playlist();
        Subscriber observer = new Subscriber();

        //To a playlist (observable), assign subscriber (observer) that will be notified about playlist change
        observable.addObserver(observer);

        //New song is added to a Playlist (observable) -> observer (Subscriber) will be notified
        observable.addSongToPlaylist("Song Author - Song Name");

        //Assert that observer now has this information
        assertEquals(observer.getNumberOfSongsInPlaylist(), 1);
    }
}
