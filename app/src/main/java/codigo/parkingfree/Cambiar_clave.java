package codigo.parkingfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cambiar_clave extends AppCompatActivity implements View.OnClickListener {

    TextView etClaveActual, etClaveNueva, etClaveNueva2;
    Button btnModificar;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0, 0, 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etClaveActual = findViewById(R.id.etClaveActual);
        etClaveNueva =  findViewById(R.id.etClaveNueva);
        etClaveNueva2 =  findViewById(R.id.etClaveNueva2);

       btnModificar = findViewById(R.id.btnModificar);
       btnModificar.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            // Falta hacer case para el icon home

            case R.id.btnModificar:

                final String claveActual = etClaveActual.getText().toString().trim();
                final String claveNueva =  etClaveNueva.getText().toString().trim();
                String claveNueva2 = etClaveNueva2.getText().toString().trim();

            if(claveNueva.equals(claveNueva2)){

                myRef = mDatabase.getReference("Usuario");

                myRef.orderByChild("Documento")
                        .equalTo(Login.documento)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot child : dataSnapshot.getChildren()) {

                            Log.i("Clave", "User " + child.child("Clave").getValue());

                            if(child.child("Clave").getValue().equals(claveActual)){

                                String id = child.getKey();

                                myRef.child(id).child("Clave").setValue(claveNueva);

                                etClaveActual.setText(null);
                                etClaveNueva.setText(null);
                                etClaveNueva2.setText(null);

                                Toast.makeText(getApplicationContext(), "La clave ha sido cambiada", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(getApplicationContext(), "Verifique la clave actual.", Toast.LENGTH_SHORT).show();
                                etClaveActual.setText(null);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }else{
                Toast.makeText(this, "La clave nueva no coincide.", Toast.LENGTH_SHORT).show();
            }

            break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                switch (Login.tipoUsuario){

                    case "1":
                        startActivity(new Intent(getApplicationContext(), PrincipalCliente.class));
                        break;

                    case "2":
                        startActivity(new Intent(getApplicationContext(), PrincipalAdministrador.class));
                        break;
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
