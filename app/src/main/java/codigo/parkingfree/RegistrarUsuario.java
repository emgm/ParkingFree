package codigo.parkingfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener {

    EditText etNombres, etApellidos, etDocumento, etTelefono;
    Button btnAcceder;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0,0,0,0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etDocumento = findViewById(R.id.etDocumento);
        etTelefono = findViewById(R.id.etTelefono);


        btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnAcceder:

                myRef = mDatabase.getReference("Usuario").push();

                myRef.child("Nombres").setValue(etNombres.getText().toString());
                myRef.child("Apellidos").setValue(etApellidos.getText().toString());
                myRef.child("Documento").setValue(etDocumento.getText().toString());
                myRef.child("Documento").setValue(etDocumento.getText().toString());
                myRef.child("Telefono").setValue(etTelefono.getText().toString());
                myRef.child("TipoUsuario").setValue("1");

                break;
        }
    }
}
