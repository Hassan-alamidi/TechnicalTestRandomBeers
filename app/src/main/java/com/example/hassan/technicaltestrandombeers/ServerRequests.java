package com.example.hassan.technicaltestrandombeers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;

import java.net.InetAddress;
import java.net.Proxy;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.util.Log.getStackTraceString;

/**
 * Created by hassan on 23/05/2017.
 */

public class ServerRequests {

    private static ServerRequests Instance;
    private ActivityCallback callback;
    private static final String key ="f732ee5e807f63b6f5ba16a2bedef20d";

    public void RequestBeer(Context context, final ActivityCallback callback){

            if(checkInternet(context)) {
                Retrofit.Builder rtBuilder = new Retrofit.Builder().baseUrl("http://api.brewerydb.com/v2/").addConverterFactory(GsonConverterFactory.create());
                Retrofit retro = rtBuilder.build();
                RequestInterface beer = retro.create(RequestInterface.class);
                Call<Beer> call = beer.getBeerFromServer("Y",key,"json");

                call.enqueue(new Callback<Beer>() {
                    @Override
                    public void onResponse(Call<Beer> call, Response<Beer> response) {
                        if (response.code() == 200) {
                            Beer randomBeer = response.body();
                            callback.onResponse(randomBeer);
                        } else {
                            Log.e("ServerRequest, Line 39", "Something went wrong, Error code: " + response.code());
                        }

                    }

                    @Override
                    public void onFailure(Call<Beer> call, Throwable t) {
                        Log.e("ServerRequest, Line 46", t.getMessage());
                        if (t.getCause() != null) {
                            Log.e("The Cause of error:", t.getCause().toString());
                        }
                        callback.OnFailure("Request Failure");

                    }
                });
            }else{
                callback.OnFailure("No Internet Connection");
            }

    }

    public boolean checkInternet(Context context){
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return connectionManager.getActiveNetworkInfo() != null && connectionManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static ServerRequests getInstance(){
        //check if instance is null
        if(Instance == null){
            //Lock the code to one thread
            //kind of pointless making this synchronized as this application is not really making use of multithreading
            // but if this was a real application multithreading would more than likely be implemented at some stage so this is more like future proofing
            synchronized (ServerRequests.class){
                //make sure the instance is still null before creating a new instance
                if(Instance == null){
                    Instance = new ServerRequests();
                }
            }
        }
        return Instance;
    }
}
