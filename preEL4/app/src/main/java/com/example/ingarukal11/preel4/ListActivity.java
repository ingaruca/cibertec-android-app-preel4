package com.example.ingarukal11.preel4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import beans.Cliente;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Thread tr = new Thread(){
            @Override
            public void run() {
                final String resul = ListarClientes();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LlenarLista(objDatos(resul));
                    }
                });
            }
        };

        tr.start();

    }

    public String ListarClientes(){
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;

        try{
            url = new URL("http://192.168.1.21/serviciosAndroid/listarClientes.php");
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

    public ArrayList<String> objDatos(String resul){
        ArrayList<String> lista = new ArrayList<String>();

        try{
            JSONArray json = new JSONArray(resul);
            String texto = "";

            for (int i = 0; i < json.length(); i++){
                texto = "\n" +
                        "Cod. Cliente: " + json.getJSONObject(i).getString("IdCliente") + "\n" +
                        "Apellidos: " + json.getJSONObject(i).getString("Apellidos") + "\n" +
                        "Nombres: " + json.getJSONObject(i).getString("Nombres") + "\n" +
                        "Edad: " + json.getJSONObject(i).getString("Edad") + "\n" +
                        "Sexo: " + json.getJSONObject(i).getString("Sexo") + "\n";
                lista.add(texto);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void LlenarLista(ArrayList<String> lista){
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        ListView listClientes = (ListView)findViewById(R.id.listClientes);
        listClientes.setAdapter(adap);
    }

}
