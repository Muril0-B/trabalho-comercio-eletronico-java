package br.com.entidade;

import br.com.controle.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciarProduto extends DAO {

    public void inserir(Produto p) throws Exception {
        abrirBanco();
        // verifica se ja existe um produto com o mesmo nome
        String existe = "select id_produto, nome FROM produto where nome=?";
        pst = (PreparedStatement) con.prepareStatement(existe);
        pst.setString(1, p.getNome());
        ResultSet tr = pst.executeQuery();
        if (tr.next()) {
            JOptionPane.showMessageDialog(null, "Produto ja cadastrado anteriormente!");
        } else {
            String query = "INSERT INTO produto (id_produto, nome, descricao, preco, estoque) values(null,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setDouble(3, p.getPreco());
            pst.setInt(4, p.getEstoque());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Inserido com sucesso!");
        }
        fecharBanco();
    }

    public void deletarProduto(Produto p) throws Exception {
        abrirBanco();
        try {
            String query = "delete from produto where id_produto=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, p.getId_produto());
            int linhas = pst.executeUpdate();
            if (linhas > 0) {
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com esse codigo!");
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            // produto ja usado em algum item de pedido (chave estrangeira)
            JOptionPane.showMessageDialog(null, "Nao e possivel excluir: este produto ja faz parte de pedidos.");
        }
        fecharBanco();
    }

    public void pesquisarRegistro(Produto p) throws Exception {
        try {
            abrirBanco();
            String query = "select id_produto, nome, descricao, preco, estoque FROM produto where id_produto=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, p.getId_produto());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                p.setId_produto(tr.getInt("id_produto"));
                p.setNome(tr.getString("nome"));
                p.setDescricao(tr.getString("descricao"));
                p.setPreco(tr.getDouble("preco"));
                p.setEstoque(tr.getInt("estoque"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public ArrayList<Produto> pesquisarTudo() throws Exception {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        try {
            abrirBanco();
            String query = "select * FROM produto";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            Produto p;
            while (tr.next()) {
                p = new Produto();
                p.setId_produto(tr.getInt("id_produto"));
                p.setNome(tr.getString("nome"));
                p.setDescricao(tr.getString("descricao"));
                p.setPreco(tr.getDouble("preco"));
                p.setEstoque(tr.getInt("estoque"));
                produtos.add(p);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return produtos;
    }

    public void editarProduto(Produto p) throws Exception {
        abrirBanco();
        String query = "UPDATE produto set nome = ?, descricao = ?, preco = ?, estoque = ? where id_produto = ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, p.getNome());
        pst.setString(2, p.getDescricao());
        pst.setDouble(3, p.getPreco());
        pst.setInt(4, p.getEstoque());
        pst.setInt(5, p.getId_produto());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto Alterado com sucesso!");
        fecharBanco();
    }

    // Usado pelo pedido: da baixa no estoque somente se houver quantidade suficiente
    public boolean baixarEstoque(int idProduto, int quantidade) throws Exception {
        abrirBanco();
        String query = "UPDATE produto SET estoque = estoque - ? WHERE id_produto = ? AND estoque >= ?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, quantidade);
        pst.setInt(2, idProduto);
        pst.setInt(3, quantidade);
        int linhas = pst.executeUpdate();
        fecharBanco();
        return linhas > 0;
    }
}
