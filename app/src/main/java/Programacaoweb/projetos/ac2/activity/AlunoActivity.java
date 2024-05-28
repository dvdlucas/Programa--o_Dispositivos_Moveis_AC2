package Programacaoweb.projetos.ac2.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonObject;

import Programacaoweb.projetos.ac2.R;
import Programacaoweb.projetos.ac2.ViaCep.ViaCepClient;
import Programacaoweb.projetos.ac2.api.AlunoService;
import Programacaoweb.projetos.ac2.api.ApiClient;
import Programacaoweb.projetos.ac2.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoActivity extends AppCompatActivity {
    Button btnSalvar, searchButton;
    AlunoService apiService;
    TextView txtra, txtnome, txtcep, txtlogradouro, txtbairro, txtcidade, txtcomplemento, txtuf;
    int id;

    private void inserirAluno(Aluno aluno) {
        Call<Aluno> call = apiService.postUsuario(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Aluno createdPost = response.body();
                    Toast.makeText(AlunoActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // A solicitação falhou
                    Log.e("Inserir", "Erro ao criar: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Inserir", "Erro ao criar: " + t.getMessage());
            }
        });
    }

    private void editarAluno(Aluno aluno) {
        Call<Aluno> call = apiService.putAluno(id, aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Aluno createdPost = response.body();
                    Toast.makeText(AlunoActivity.this, "Editado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // A solicitação falhou
                    Log.e("Editar", "Erro ao editar: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Editar", "Erro ao editar: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        apiService = ApiClient.getAlunoService();
        txtra = findViewById(R.id.txtRA);
        txtnome = findViewById(R.id.txtNome);
        txtcep = findViewById(R.id.txtCEP);
        txtlogradouro = findViewById(R.id.txtLogradouro);
        txtcomplemento = findViewById(R.id.txtComplemento);
        txtbairro = findViewById(R.id.txtBairro);
        txtcidade = findViewById(R.id.txtCidade);
        txtuf = findViewById(R.id.txtUf);
        searchButton = findViewById(R.id.searchButton);
        id = getIntent().getIntExtra("id", 0);
        if (id > 0) {
            apiService.getAlunoPorId(id).enqueue(new Callback<Aluno>() {
                @Override
                public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                    if (response.isSuccessful()) {
                        txtra.setText(response.body().getRa());
                        txtnome.setText(response.body().getNome());
                        txtcep.setText(response.body().getCep());
                        txtlogradouro.setText(response.body().getLogradouro());
                        txtcomplemento.setText(response.body().getComplemento());
                        txtbairro.setText(response.body().getBairro());
                        txtcidade.setText(response.body().getCidade());
                        txtuf.setText(response.body().getUf());
                    }
                }

                @Override
                public void onFailure(Call<Aluno> call, Throwable t) {
                    Log.e("Obter usuario", "Erro ao obter usuario");
                }
            });
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = new Aluno();
                aluno.setRa(Integer.parseInt(txtra.getText().toString()));
                aluno.setNome(txtnome.getText().toString());
                aluno.setCep(txtcep.getText().toString());
                aluno.setLogradouro(txtlogradouro.getText().toString());
                aluno.setComplemento(txtcomplemento.getText().toString());
                aluno.setBairro(txtbairro.getText().toString());
                aluno.setCidade(txtcidade.getText().toString());
                aluno.setUf(txtuf.getText().toString());
                if (id == 0)
                    inserirAluno(aluno);
                else {
                    aluno.setRa(id);
                    editarAluno(aluno);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = txtcep.getText().toString();
                if (cep.isEmpty()) {
                    Toast.makeText(AlunoActivity.this, "Por favor, insira um CEP.", Toast.LENGTH_LONG).show();
                    return;
                }

                ViaCepClient.getEnderecoPorCep(cep, new ViaCepClient.CepResponseListener() {
                    @Override
                    public void onCepResponse(JsonObject endereco) {
                        txtlogradouro.setText(endereco.get("logradouro").getAsString());
                        txtcomplemento
                                .setText(endereco.has("complemento") ? endereco.get("complemento").getAsString() : "");
                        txtbairro.setText(endereco.get("bairro").getAsString());
                        txtcidade.setText(endereco.get("localidade").getAsString());
                        txtuf.setText(endereco.get("uf").getAsString());
                    }

                    @Override
                    public void onCepError(String error) {
                        Toast.makeText(AlunoActivity.this, error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}