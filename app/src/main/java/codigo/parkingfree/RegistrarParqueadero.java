package codigo.parkingfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarParqueadero extends AppCompatActivity {

    TextView etNombreParqueadero, etDireccion, etHorarioAtencion, etPuestosDisponibles;
    Button btnRegistrar;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_parqueadero);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0, 0, 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombreParqueadero = findViewById(R.id.etNombreParqueadero);
        etDireccion = findViewById(R.id.etDireccion);
        etHorarioAtencion = findViewById(R.id.etHorarioAtencion);
        etPuestosDisponibles = findViewById(R.id.etPuestosDisponibles);

        mDatabase = FirebaseDatabase.getInstance();

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef = mDatabase.getReference("Parqueadero").push();

                myRef.child("DocumentoAdministrador").setValue(Login.documento);
                myRef.child("NombreParqueadero").setValue(etNombreParqueadero.getText().toString());
                myRef.child("Direccion").setValue(etDireccion.getText().toString());
                myRef.child("PuestosDisponibles").setValue("SI");
                myRef.child("HorarioAtencion").setValue(etHorarioAtencion.getText().toString());
                myRef.child("ParqueaderoAbierto").setValue("SI");
                myRef.child("CantidadPuestos").setValue(etPuestosDisponibles.getText().toString());
                myRef.child("PuestosOcupados").setValue("0");

                etNombreParqueadero.setText(null);
                etDireccion.setText(null);
                etHorarioAtencion.setText(null);
                etPuestosDisponibles.setText(null);

                Toast.makeText(getApplicationContext(), "El parqueadero ha sido registrado.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
