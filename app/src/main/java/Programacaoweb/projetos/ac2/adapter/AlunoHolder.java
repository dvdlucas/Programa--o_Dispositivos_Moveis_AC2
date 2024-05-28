package facens.Programacaoweb.ac2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import Programacaoweb.projetos.ac2.R;

public class AlunoHolder extends RecyclerView.ViewHolder {
    public TextView ra, nome, cep, logradouro, complemento, bairro, cidade, uf;
    public ImageView btnexcluir;
    public ImageView btnEditar;

    public AlunoHolder(View itemView) {
        super(itemView);
        ra = (TextView) itemView.findViewById(R.id.txtRa);
        nome = (TextView) itemView.findViewById(R.id.txtNome);
        cep = (TextView) itemView.findViewById(R.id.txtCEP);
        logradouro = (TextView) itemView.findViewById(R.id.txtLogradouro);
        complemento = (TextView) itemView.findViewById(R.id.txtComplemento);
        bairro = (TextView) itemView.findViewById(R.id.txtBairro);
        cidade = (TextView) itemView.findViewById(R.id.txtCidade);
        uf = (TextView) itemView.findViewById(R.id.txtUf);
        btnexcluir = (ImageView) itemView.findViewById(R.id.btnExcluir);
        btnEditar = (ImageView) itemView.findViewById(R.id.btnEditar);
    }
}
