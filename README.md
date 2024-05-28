# Aplicativo de Cadastro de Alunos
## Descrição:
Este projeto é um aplicativo simples para o cadastro de alunos de uma instituição. 
Utiliza a API mockapi.io para armazenar os dados dos alunos e a API viacep.com.br para preencher automaticamente os dados de endereço com base no CEP informado.

## Funcionalidades:
### O aplicativo possui duas telas principais:
#### Tela de Cadastro de Alunos:

Permite inserir os dados do aluno.
Ao informar o CEP, os demais campos de endereço (logradouro, complemento, bairro, cidade, e UF) são preenchidos automaticamente utilizando a API do ViaCEP.
Os dados são salvos no Mockapi.

#### Tela de Listagem de Alunos:

Exibe uma lista de todos os alunos cadastrados.
# Estrutura da Classe Aluno
A classe Aluno possui a seguinte composição:


    public Aluno() {}

    public Aluno(int ra, String nome, String cep, String logradouro, String complemento,
                 String bairro, String cidade, String uf) {
        this.ra = ra;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

# Tecnologias Utilizadas
Java: Linguagem de programação principal do projeto.

Mockapi.io: Serviço para simulação de API REST para armazenamento dos dados.

ViaCEP: Serviço para consulta de endereços a partir do CEP.

Swing/JavaFX: Bibliotecas para construção da interface gráfica (dependendo da escolha).

## Link do aplicativo funcionando : https://youtu.be/KRKbyBubxh4
## Desenvolvido por: 
David Lucas rosa Rolim da Silva
RA: 223549
