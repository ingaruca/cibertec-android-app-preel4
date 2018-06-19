package com.example.ingarukal11.preel4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

                LimpiarCampos();
            }
        });

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        Cliente c = new Cliente(txtID.getText().toString(), txtApellidos.getText().toString(), txtNombres.getText().toString(),
                                Integer.parseInt(txtEdad.getText().toString()), txtSexo.getText().toString());

                        EditarCliente(c);
                    }
                };
                tr.start();

                Toast.makeText(getApplicationContext(), "Se han grabado los cambios!", Toast.LENGTH_LONG).show();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Thread tr = new Thread(){
                    @Override
                    public void run() {
                        EliminarCliente(txtID.getText().toString());
                    }
                };
                tr.start();

                Toast.makeText(getApplicationContext(), "Registro eliminado!", Toast.LENGTH_LONG).show();

                LimpiarCampos();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (txtID.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Debe ingresar un ID para buscar un cliente!", Toast.LENGTH_LONG).show();
                    txtID.requestFocus();
                }else{
                    Thread tr = new Thread(){
                        @Override
                        public void run() {
                            final String res = BuscarCliente( txtID.getText().toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MostrarDataBusqueda(res);
                                }
                            });
                        }
                    };
                    tr.start();
                }

            }
        });
    }

    public void LimpiarCampos(){
        txtID.setText("");
        txtID.requestFocus();
        txtApellidos.setText("");
        txtNombres.setText("");
        txtEdad.setText("");
        txtSexo.setText("");
    }

    public void RegistrarCliente(Cliente cliente){
        URL url = null;

        try{
            url = new URL("http://192.168.1.17/serviciosAndroid/registrarCliente.php?id="+cliente.getIdCliente()+"&apellidos="+
                    cliente.getApellidos()+"&nombres="+cliente.getNombres()+"&edad="+cliente.getEdad()+"&sexo="+cliente.getSexo());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.getResponseCode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void EditarCliente(Cliente cliente){
        URL url = null;

        try{
            url = new URL("http://192.168.1.17/serviciosAndroid/editarCliente.php?id="+cliente.getIdCliente()+"&apellidos="+
                    cliente.getApellidos()+"&nombres="+cliente.getNombres()+"&edad="+cliente.getEdad()+"&sexo="+cliente.getSexo());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.getResponseCode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void EliminarCliente(String id){
        URL url = null;

        try{
            url = new URL("http://192.168.1.17/serviciosAndroid/eliminarCliente.php?id="+id);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.getResponseCode();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String BuscarCliente(String id){
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try{
            url = new URL("http://192.168.1.17/serviciosAndroid/consultaCliente.php?id="+id);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            respuesta = connection.getResponseCode();

            resul = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null){
                    resul.append(linea);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return resul.toString();
    }

    public void MostrarDataBusqueda(String rpta){
        try{
            JSONArray json = new JSONArray(rpta);

            JSONObject cliente = json.getJSONObject(0);

            String apellidos = cliente.getString("Apellidos");
            String nombres = cliente.getString("Nombres");
            String edad = cliente.getString("Edad");
            String sexo = cliente.getString("Sexo");

            txtApellidos.setText(apellidos);
            txtNombres.setText(nombres);
            txtEdad.setText(edad);
            txtSexo.setText(sexo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
