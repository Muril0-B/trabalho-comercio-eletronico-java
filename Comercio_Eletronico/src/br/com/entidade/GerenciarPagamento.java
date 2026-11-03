package br.com.entidade;

import br.com.controle.Pagamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ManterPagamento extends DAO {

    public void inserir(Pagamento p) throws Exception {
        abrirBanco();
        String query = "INSERT INTO pagamento (id_pedido, valor_pago, forma_pagamento, status_pagamento) VALUES (?, ?, ?, ?)";
        pst = con.prepareStatement(query);
        pst.setInt(1, p.getIdPedido());
        pst.setDouble(2, p.getValorPago());
        pst.setString(3, p.getFormaPagamento());
        pst.setString(4, p.getStatusPagamento());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pagamento cadastrado com sucesso!");
        fecharBanco();
    }

    public void deletarPagamento(Pagamento p) throws Exception {
        abrirBanco();
        String query = "DELETE FROM pagamento WHERE id_pagamento = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, p.getIdPagamento());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pagamento deletado com sucesso!");
        fecharBanco();
    }

    public void pesquisarRegistro(Pagamento p) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM pagamento WHERE id_pagamento = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, p.getIdPagamento());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                p.setIdPagamento(tr.getInt("id_pagamento"));
                p.setIdPedido(tr.getInt("id_pedido"));
                p.setDataPagamento(tr.getString("data_pagamento"));
                p.setValorPago(tr.getDouble("valor_pago"));
                p.setFormaPagamento(tr.getString("forma_pagamento"));
                p.setStatusPagamento(tr.getString("status_pagamento"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Pagamento> pesquisarTudo() throws Exception {
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM pagamento";
            pst = con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            while (tr.next()) {
                Pagamento p = new Pagamento();
                p.setIdPagamento(tr.getInt("id_pagamento"));
                p.setIdPedido(tr.getInt("id_pedido"));
                p.setDataPagamento(tr.getString("data_pagamento"));
                p.setValorPago(tr.getDouble("valor_pago"));
                p.setFormaPagamento(tr.getString("forma_pagamento"));
                p.setStatusPagamento(tr.getString("status_pagamento"));
                pagamentos.add(p);
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return pagamentos;
    }
}