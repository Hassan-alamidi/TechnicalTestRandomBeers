package com.example.hassan.technicaltestrandombeers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hassan on 23/05/2017.
 */

public interface RequestInterface {
    @GET("beer/random?")
    Call<Beer> getBeerFromServer(@Query("hasLabels") String hasLabels,@Query("key") String key,@Query("format") String format);
}
