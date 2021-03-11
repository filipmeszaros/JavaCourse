package org.designpatterns.observer;

/**
 * The interface for Observer only has one method, that will force observer to implement it.
 * In our example, observer (subscriber of playlist) needs to implement method update(),
 * that will be executed when new song is added to a playlist.
 */
public interface ISubscriber {

    //function of observer that will be called by observable
    //by making this function being in interface, observer is forced to implement it
    void update(int numberOfSongs);
}
