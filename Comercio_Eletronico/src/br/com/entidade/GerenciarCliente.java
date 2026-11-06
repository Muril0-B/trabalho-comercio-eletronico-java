package br.com.entidade;

import br.com.controle.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerenciarCliente extends DAO {

    public void inserir(Cliente c) throws Exception {
        try {
            abrirBanco();

            String existe = "SELECT email FROM cliente WHERE email = ?";
            pst = con.prepareStatement(existe);
            pst.setString(1, c.getEmail());

            ResultSet tr = pst.executeQuery();

            if (tr.next()) {
                JOptionPane.showMessageDialog(null, "E-mail já cadastrado!");
            } else {

                String query = "INSERT INTO cliente (nome, email, telefone, cpf_cnpj, endereco) VALUES (?, ?, ?, ?, ?)";
                pst = con.prepareStatement(query);

                pst.setString(1, c.getNome());
                pst.setString(2, c.getEmail());
                pst.setString(3, c.getTelefone());
                pst.setString(4, c.getCpf_cnpj());
                pst.setString(5, c.getEndereco());

                pst.execute();

                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }

            tr.close();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void deletarCliente(Cliente c) throws Exception {
        try {
            abrirBanco();

            String query = "DELETE FROM cliente WHERE id_cliente = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, String.valueOf(c.getId_cliente()));
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }

            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void pesquisarRegistro(Cliente c) throws Exception {
        try {
            abrirBanco();

            String query = "SELECT * FROM cliente WHERE id_cliente = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, String.valueOf(c.getId_cliente()));

            ResultSet tr = pst.executeQuery();

            if (tr.next()) {
                c.setId_cliente(tr.getInt("id_cliente"));
                c.setCpf_cnpj(tr.getString("cpf_cnpj"));
                c.setNome(tr.getString("nome"));
                c.setEmail(tr.getString("email"));
                c.setTelefone(tr.getString("telefone"));
                c.setEndereco(tr.getString("endereco"));
                c.setData_cadastro("data_cadastro");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }

            tr.close();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> pesquisarTudo() throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            abrirBanco();

            String query = "SELECT * FROM cliente";
            pst = con.prepareStatement(query);

            ResultSet tr = pst.executeQuery();

            while (tr.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(tr.getInt("id_cliente"));
                c.setCpf_cnpj(tr.getString("cpf_cnpj"));
                c.setNome(tr.getString("nome"));
                c.setEmail(tr.getString("email"));
                c.setTelefone(tr.getString("telefone"));
                c.setEndereco(tr.getString("endereco"));
                c.setData_cadastro("data_cadastro");
                clientes.add(c);
            }

            tr.close();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return clientes;
    }

    public void atualizarCliente(Cliente c) throws Exception {
        try {
            abrirBanco();

            String query = "UPDATE cliente SET nome = ?, email = ?, telefone = ?, endereco = ?, cpf_cnpj = ? WHERE id_cliente = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, c.getNome());
            pst.setString(2, c.getEmail());
            pst.setString(3, c.getTelefone());
            pst.setString(4, c.getEndereco());
            pst.setString(5, c.getCpf_cnpj());
            pst.setString(6, String.valueOf(c.getId_cliente()));

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }

            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
