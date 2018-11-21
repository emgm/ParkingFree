package codigo.parkingfree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView tvRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar my_ToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(my_ToolBar);
        my_ToolBar.setPadding(0,0,0,0);

        tvRegistar = findViewById(R.id.tvRegistar);
        tvRegistar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tvRegistar:

                startActivity(new Intent(this, RegistrarUsuario.class));

                break;

        }
    }
}
