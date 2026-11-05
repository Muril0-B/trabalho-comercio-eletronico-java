
package br.com.controle;

public class Vendedor {
   private String nome;
   private String email;
   private String telefone; /* deve conter o DDD */
   private double percentual_comissao; /* deve ser apresentada em & */
   private int id_vendedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getPercentual_comissao() {
        return percentual_comissao;
    }

    public void setPercentual_comissao(double percentual_comissao) {
        this.percentual_comissao = percentual_comissao;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
   
   
}
