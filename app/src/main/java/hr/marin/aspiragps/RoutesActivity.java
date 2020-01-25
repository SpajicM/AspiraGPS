package hr.marin.aspiragps;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RoutesActivity extends AppCompatActivity {

    RoutesFragment routesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

         routesFragment = (RoutesFragment) getSupportFragmentManager().findFragmentById(R.id.routes_list_fragment);
    }
}
