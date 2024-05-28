package Programacaoweb.projetos.ac2.api;

import java.util.List;

import Programacaoweb.projetos.ac2.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlunoService {
    @GET("aluno")
    Call<List<Aluno>> getAlunos();

    @POST("aluno")
    Call<Aluno> postUsuario(@Body Aluno aluno);

    @DELETE("aluno/{id}")
    Call<Void> deleteAluno(@Path("id") int idAluno);

    @GET("aluno/{id}")
    Call<Aluno> getAlunoPorId(@Path("id") int idAluno);

    @PUT("aluno/{id}")
    Call<Aluno> putAluno(@Path("id") int idAluno, @Body Aluno aluno);
}
