package codigo.parkingfree;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView tvRegistar, etDocumento, etClave;
    Button btnAcceder;

    public static String documento, tipoUsuario;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0,0,0,0);

        etDocumento = findViewById(R.id.etDocumento);
        etClave = findViewById(R.id.etClave);

        tvRegistar = findViewById(R.id.tvRegistar);
        tvRegistar.setOnClickListener(this);

        btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tvRegistar:

                startActivity(new Intent(this, RegistrarUsuario.class));

                break;

            case R.id.btnAcceder:

                documento = etDocumento.getText().toString().trim();
                final String clave = etClave.getText().toString().trim();

                if(!("".equals(documento)) &&  !("".equals(clave))){

                    myRef = mDatabase.getReference("Usuario");

                    myRef.orderByChild("Documento")
                            .equalTo(documento)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.i("User", "cantidad " + dataSnapshot.getChildrenCount());

                            if(dataSnapshot.getChildrenCount() > 0) {

                                for (DataSnapshot child : dataSnapshot.getChildren()) {

                                    Log.i("User", " " + child.child("Clave").getValue());

                                    if(clave.equals(child.child("Clave").getValue())){

                                        // Se debe obtener el documento y la clave del editText en la interfaz, comparar en la base de datos y de acuerdo al perfil (1- solo cliente, 2- cliente y administrador) remitirlo a la vista

                                        tipoUsuario = String.valueOf(child.child("TipoUsuario").getValue());

                                        switch (tipoUsuario){

                                            case "1":
                                                startActivity(new Intent(getApplicationContext(), PrincipalCliente.class));
                                                break;

                                            case "2":
                                                startActivity(new Intent(getApplicationContext(), PrincipalAdministrador.class));
                                                break;
                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(), "El documento o la clave no conincide.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "El usuario no existe.", Toast.LENGTH_SHORT).show();
                            }

                            etDocumento.setText(null);
                            etClave.setText(null);
                            etClave.clearFocus();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else{

                    Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
