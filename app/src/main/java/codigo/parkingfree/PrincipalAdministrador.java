package codigo.parkingfree;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PrincipalAdministrador extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    TextView tvName, tvTelefono;

    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    String[] letra = {"A","B","C","D","E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_administrador);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0, 0, 0, 0);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        tvName = header.findViewById(R.id.tvName);
        tvTelefono = header.findViewById(R.id.tvTelefono);

        tvName.setText(Login.nombres);
        tvTelefono.setText(Login.telefono);

        DrawerLayout myDrawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.open, R.string.close);
        myDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){

            return  true;

        }

        switch (item.getItemId()) {

            case R.id.opcion_cerrarSesion:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;

            case R.id.opcion_cuenta:
                startActivity(new Intent(getApplicationContext(), CambiarClave.class));
                break;



        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()){


            case R.id.opcionEditParqueadero:

                startActivity(new Intent(this, RegistrarOEditarParqueadero.class));

                break;

        }

        DrawerLayout dl = findViewById(R.id.drawerLayout);

        if(dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }
}
