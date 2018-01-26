package ch.hesso.parkingspot.android;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lavdrim.myapplication.backend.parkingspotApi.ParkingspotApi;
import com.example.lavdrim.myapplication.backend.parkingspotApi.model.Parkingspot;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 12.05.2017
 */

public class EndPointAsyncTaskParkingspot extends AsyncTask<Void, Void, List<Parkingspot>> {
    private static ParkingspotApi parkingspotApi = null;
    private static final String TAG = EndPointAsyncTaskParkingspot.class.getName();
    private Context context;
    private Parkingspot parkingspot;
    private Long id;

    private boolean insert = false;
    private boolean getall= false;
    private boolean get = false;
    private boolean update = false;
    private boolean delete = false;

    EndPointAsyncTaskParkingspot(){ getall = true;}


    EndPointAsyncTaskParkingspot(Long id, Parkingspot parkingspot) {
        update = true;
        this.id = id;
        this.parkingspot = parkingspot;
    }

    EndPointAsyncTaskParkingspot(Long id, String s) {
        this.id = id;
        if (s.equals("get")){
            get=true;
        }else if (s.equals("delete")){
            delete = true;
        }
    }

    EndPointAsyncTaskParkingspot(Parkingspot parkingspot) {
        insert = true;
        this.parkingspot = parkingspot;
    }

    @Override
    protected  List<Parkingspot> doInBackground(Void... params){
        if(parkingspotApi == null) { // Only do this once
            ParkingspotApi.Builder builder = new ParkingspotApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://parkingspot.android-1492529578093.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            parkingspotApi = builder.setApplicationName("ParkingLibrary").build();
            parkingspotApi = builder.build();
        }
        try {
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(parkingspot != null && insert == true){
                parkingspotApi.insert(parkingspot).execute();
            }

            if(parkingspot != null && getall == true){
                parkingspotApi.list().execute().getItems();
            }

            if(parkingspot != null && get == true){
                parkingspotApi.get(id).execute(); //id einfügen
            }

            if(parkingspot != null && update == true){
                parkingspotApi.update(id, parkingspot).execute(); //id einfügen
            }

            if(delete == true){
                parkingspotApi.remove(id).execute();
            }

            insert = false;
            getall= false;
            get = false;
            update = false;
            delete = false;
            // and for instance return the list of all users
            return parkingspotApi.list().execute().getItems();

        } catch (IOException e){
            return new ArrayList<Parkingspot>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Parkingspot> result){

        if(result != null) {
            for (Parkingspot parkingspot : result) {
                Log.i(TAG, "Address: " + parkingspot.getAddress() + " Location: "
                        + parkingspot.getLocation());

            }
        }
    }
}