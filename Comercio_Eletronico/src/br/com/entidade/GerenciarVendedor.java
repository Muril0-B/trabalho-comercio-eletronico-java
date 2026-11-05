package br.com.entidade;

import br.com.controle.Vendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciarVendedor extends DAO {

    public void inserir(Vendedor v) throws Exception {
        abrirBanco();
        String query = "INSERT INTO vendedor (nome, email, telefone, percentual_comissao) VALUES (?, ?, ?, ?)";
        pst = con.prepareStatement(query);
        pst.setString(1, v.getNome());
        pst.setString(2, v.getEmail());
        pst.setString(3, v.getTelefone());
        pst.setDouble(4, v.getPercentual_comissao());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
        fecharBanco();
    }

    public void alterar(Vendedor v) throws Exception {
        abrirBanco();
        String query = "UPDATE vendedor SET nome = ?, email = ?, telefone = ?, percentual_comissao = ? WHERE id_vendedor = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, v.getNome());
        pst.setString(2, v.getEmail());
        pst.setString(3, v.getTelefone());
        pst.setDouble(4, v.getPercentual_comissao());
        pst.setInt(5, v.getId_vendedor());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Vendedor atualizado com sucesso!");
        fecharBanco();
    }

    public void deletarVendedor(Vendedor v) throws Exception {
        abrirBanco();
        String query = "DELETE FROM vendedor WHERE id_vendedor = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, v.getId_vendedor());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Vendedor deletado com sucesso!");
        fecharBanco();
    }

    public void pesquisarRegistro(Vendedor v) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM vendedor WHERE id_vendedor = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, v.getId_vendedor());
            ResultSet tr = pst.executeQuery();

            if (tr.next()) {
                v.setId_vendedor(tr.getInt("id_vendedor"));
                v.setNome(tr.getString("nome"));
                v.setEmail(tr.getString("email"));
                v.setTelefone(tr.getString("telefone"));
                v.setPercentual_comissao(tr.getDouble("percentual_comissao"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }

            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Vendedor> pesquisarTudo() throws Exception {
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        try {
            abrirBanco();
            String query = "SELECT * FROM vendedor";
            pst = con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();

            while (tr.next()) {
                Vendedor v = new Vendedor();
                v.setId_vendedor(tr.getInt("id_vendedor"));
                v.setNome(tr.getString("nome"));
                v.setEmail(tr.getString("email"));
                v.setTelefone(tr.getString("telefone"));
                v.setPercentual_comissao(tr.getDouble("percentual_comissao"));
                vendedores.add(v);
            }

            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return vendedores;
    }
}