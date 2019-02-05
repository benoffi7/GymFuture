package com.example.dami_.gymfuture.app;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import com.example.dami_.gymfuture.Database.DatabaseApp;
import com.example.dami_.gymfuture.SplashActivity;
import com.google.firebase.FirebaseApp;

public class app extends Application
{
    //https://github.com/square/picasso
    //https://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en
//http://jakewharton.github.io/butterknife/
    public static DatabaseApp getDatabaseBuilder(Activity activity)
    {
       return  Room.databaseBuilder(activity,
                DatabaseApp.class, "gym_future_db").fallbackToDestructiveMigration().build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    /*

    esto va en una actividad/fragmento

     AlimentosAdapter alimentosAdapter = new AlimentosAdapter(context,list , new AlimentosAdapter.ItemSeleccionInterface() {
            @Override
            public void onClickItem(Object item)
            {

            }
        });
        //esto es un recycler view
        rv_marcas.setAdapter(alimentosAdapter);
     */


    /*
    String URL = "http://"+imagen.getUrl();
                Picasso.get().cancelRequest(ivImagenMascota);//es un imageview
                Picasso.get().load(URL).placeholder(R.drawable.ic_pet).resize(100,100).memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE).into(ivImagenMascota);
     */
}
