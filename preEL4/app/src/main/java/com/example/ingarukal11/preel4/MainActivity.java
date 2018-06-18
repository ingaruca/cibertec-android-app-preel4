package com.example.ingarukal11.preel4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

import beans.Cliente;

public class MainActivity extends AppCompatActivity {

    EditText txtID, txtApellidos, txtNombres, txtEdad, txtSexo;
    Button btnBuscar, btnRegistrar, btnGrabar, btnEliminar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = (EditText)findViewById(R.id.txtID);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtEdad = (EditText)findViewById(R.id.txtEdad);
        txtSexo = (EditText)findViewById(R.id.txtSexo);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        btnGrabar = (Button)findViewById(R.id.btnGrabar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnListar = (Button)findViewById(R.id.btnListar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        Cliente c = new Cliente(txtID.getText().toString(), txtApellidos.getText().toString(), txtNombres.getText().toString(),
                                Integer.parseInt(txtEdad.getText().toString()), txtSexo.getText().toString());

                        RegistrarCliente(c);
                    }
                };
                tr.start();

                Toast.makeText(getApplicationContext(), "Cliente registrado con éxito!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RegistrarCliente(Cliente cliente){
        URL url = null;

        try{
            url = new URL("http://192.168.1.10:80/serviciosAndroid/insertarCliente.php?id="+cliente.getIdCliente()+"&apellidos="+
                    cliente.getApellidos()+"&nombres="+cliente.getNombres()+"&edad="+cliente.getEdad()+"&sexo="+cliente.getSexo());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.getResponseCode();
        }catch (Exception e){

        }
    }


}
