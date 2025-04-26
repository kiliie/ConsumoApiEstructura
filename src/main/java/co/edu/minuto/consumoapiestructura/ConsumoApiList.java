/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.minuto.consumoapiestructura;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 *
 * @author kel2m
 */
public class ConsumoApiList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Dirección URL del servicio REST que se quiere consumir
            String url = "https://jsonplaceholder.typicode.com/todos/";

            // Crear una conexión HTTP GET con la URL
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta del API
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String inputLine;

            // Acumulamos todo el JSON en un StringBuilder
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Cerramos el flujo de entrada
            in.close();

            // Usamos la librería Gson para convertir el JSON en un arreglo de objetos `Todo`
            Gson gson = new Gson();

            // Convertir JSON directamente a una lista de objetos Todo
            Type listType = new TypeToken<List<Todo>>() {
            }.getType();
            List<Todo> todos = gson.fromJson(response.toString(), listType);

            // Recorremos la lista de tareas e imprimimos su información
            for (Todo todo : todos) {
                System.out.println("ID: " + todo.getId());
                System.out.println("Título: " + todo.getTitle());
                System.out.println("Completado: " + todo.isCompleted());
                System.out.println("------------------------");
            }

        } catch (Exception e) {
            // En caso de error, mostramos información del error
            e.printStackTrace();
        }
    }

}
