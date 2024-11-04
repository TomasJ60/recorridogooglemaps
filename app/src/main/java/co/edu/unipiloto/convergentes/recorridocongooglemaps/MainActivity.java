package co.edu.unipiloto.convergentes.recorridocongooglemaps;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Recorrido de origen a destino
    public void onSendMaps(View view){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("origin", 4.0323397 + ", " + -74.9653500)
                .appendQueryParameter("destination", 4.632339710 + ", " + -74.065350);
        String url = builder.build().toString();
        Log.d("Directions", url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    //Mostrar unicacion con longitud y latitud
    public void onSendMaps1(View view){
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Log.d("Directions", gmmIntentUri.toString());
        if (mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent);
        }
    }

    //Recorrer varias ubicaciones
    public void onSendMaps2(View view){
        final String uri =
                "https://www.google.com/maps/dir/12.9747,77.6094/12.9365,77.5447" +
                        "/12.9275,77.5906/12.9103,77.645/@12.9433289,77.5217216,12z" +
                        "/data=!3m1!4b1!4m2!4m1!3e0";
        final Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.apps.maps");
        if (intent.resolveActivity(MainActivity.this.getPackageManager()) != null){
            startActivity(intent);
        }
    }

    //cargar una parte del mapa
    public void onSendMaps3(View view){
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //restaurantes cercanos a la ubicacion
    public void onSendMaps4(View view){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //mostar ubicacion a partir de una direccion
    public void onSendMaps5(View view){
        //señale ese punto en particular
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=1600 Amphitheatre Parkway, Mountain+View, California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);

    }

    //lugar cercano a la ubicacion
    public void onSendMaps6(View view){
        //Main Street
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=101+main+street");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //ir a una direccion determinada
    public void onSendMaps7(View view){
        //Se una la navegacion de google
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Salt+Mines,+Zipaquira+Colombia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //ir a una ubicacion determinada
    public void onSendMaps8(View view){
        Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia&avoid=tf");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    //origen a destino con varias ubiaciones
    public void onSendMaps9(View view){
        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&" +
                "origin=18.519513,73.868315&destination=18.518496,73.879259&" +
                "waypoints=18.520561,73.872435|18.519254,73.876614|18.52152,73.877327|18.52019,73.8799&" +
                "travelmode=driving");
        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        intent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(unrestrictedIntent);
            } catch (ActivityNotFoundException innerEx) {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void navigateToProjectLocation(View view) {
        // Coordenadas del proyecto
        double projectLatitude = 4.664839;
        double projectLongitude = -74.069620;

        // Construir la URI para Google Maps con alternativas de ruta
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", projectLatitude + "," + projectLongitude)
                .appendQueryParameter("travelmode", "driving") // Cambia a "walking" o "bicycling" si es necesario
                .appendQueryParameter("alternatives", "true");


        String url = builder.build().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage("com.google.android.apps.maps");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Google Maps no está disponible", Toast.LENGTH_LONG).show();
        }
    }

}