package codigo.parkingfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView tvRegistar;
    Button btnAcceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0,0,0,0);

        tvRegistar = findViewById(R.id.tvRegistar);
        tvRegistar.setOnClickListener(this);

        btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tvRegistar:

                startActivity(new Intent(this, RegistrarUsuario.class));

                break;

            case R.id.btnAcceder:

                // Se debe obtener el documento y la clave del editText en la interfaz, comparar en la base de datos y de acuerdo al perfil (1- solo cliente, 2- cliente y administrador) remitirlo a la vista

                String tipoUsuario = "2";

                if("1".equals(tipoUsuario)){
                    startActivity(new Intent(this, PrincipalCliente.class));
                }else{
                    startActivity(new Intent(this, PrincipalAdministrador.class));
                }

                break;

        }
    }
}
