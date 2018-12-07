package codigo.parkingfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class PrincipalCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_cliente);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0, 0, 0, 0);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
    public void onBackPressed() {

        moveTaskToBack(true);
    }
}