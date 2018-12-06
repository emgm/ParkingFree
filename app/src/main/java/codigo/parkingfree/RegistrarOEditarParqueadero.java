package codigo.parkingfree;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrarOEditarParqueadero extends AppCompatActivity implements View.OnClickListener {

    TextView etNombreParqueadero, etDireccion, etHorarioAtencion, etPuestosDisponibles;
    Button btnRegistrar, btnEditar;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_o_editar_parqueadero);

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
        btnRegistrar.setOnClickListener(this);

        btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);

        myRef = mDatabase.getReference("Parqueadero");

        myRef.orderByChild("DocumentoAdministrador")
                .equalTo(Login.documento)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.getChildrenCount() > 0) {

                            btnRegistrar.setVisibility(View.GONE);
                            btnEditar.setVisibility(View.VISIBLE);

                            for (DataSnapshot child : dataSnapshot.getChildren()) {

                                etNombreParqueadero.setText(String.valueOf(child.child("NombreParqueadero").getValue()));
                                etDireccion.setText(String.valueOf(child.child("Direccion").getValue()));
                                etHorarioAtencion.setText(String.valueOf(child.child("HorarioAtencion").getValue()));
                                etPuestosDisponibles.setText(String.valueOf(child.child("CantidadPuestos").getValue()));

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnRegistrar:

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

                etPuestosDisponibles.clearFocus();

                Toast.makeText(getApplicationContext(), "El parqueadero ha sido registrado.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), PrincipalAdministrador.class));

                break;

            case R.id.btnEditar:

                break;
        }
    }
}
