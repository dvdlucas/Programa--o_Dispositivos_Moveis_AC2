package facens.projetos.ac2.ViaCep;

import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepClient {

    public interface CepResponseListener {
        void onCepResponse(JsonObject endereco);
        void onCepError(String error);
    }

    public static void getEnderecoPorCep(String cep, CepResponseListener listener) {
        new AsyncTask<String, Void, JsonObject>() {

            @Override
            protected JsonObject doInBackground(String... params) {
                String cep = params[0];
                String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    StringBuilder jsonOutput = new StringBuilder();
                    String output;
                    while ((output = br.readLine()) != null) {
                        jsonOutput.append(output);
                    }
                    conn.disconnect();

                    // Parse JSON response
                    JsonObject jsonObject = JsonParser.parseString(jsonOutput.toString()).getAsJsonObject();

                    // Handle case where the CEP is not found
                    if (jsonObject.has("erro")) {
                        return null;
                    }

                    return jsonObject;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(JsonObject result) {
                if (result != null) {
                    listener.onCepResponse(result);
                } else {
                    listener.onCepError("CEP não encontrado ou erro na requisição.");
                }
            }
        }.execute(cep);
    }
}