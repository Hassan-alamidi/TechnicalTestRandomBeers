package com.example.hassan.technicaltestrandombeers;

/**
 * Created by hassan on 23/05/2017.
 */

public interface ActivityCallback {
    //This interface is used more as a callback rather than the traditional usage for interfaces
    void onResponse(Beer randomBeer);
    void OnFailure(String e);
}
