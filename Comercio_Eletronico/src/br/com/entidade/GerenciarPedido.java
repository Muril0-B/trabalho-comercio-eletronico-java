package br.com.entidade;

import br.com.controle.Cliente;
import br.com.controle.ItemPedido;
import br.com.controle.Pedido;
import br.com.controle.Produto;
import br.com.controle.Vendedor;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GerenciarPedido extends DAO {
    public void inserir(Pedido p) throws Exception {
        try {
            abrirBanco();

            String query = "INSERT INTO pedido (id_cliente, id_vendedor, valor_total, status_pedido) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);

            pst.setInt(1, p.getId_cliente());
            pst.setInt(2, p.getId_vendedor());
            pst.setDouble(3, p.getValor_total());
            pst.setString(4, p.getStatus_pedido());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!");

            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void inserirItens(ItemPedido ip) throws Exception {
        try {
            abrirBanco();

            String query = "INSERT INTO item_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);

            pst.setInt(1, ip.getId_pedido());
            pst.setInt(2, ip.getId_produto());
            pst.setInt(3, ip.getQuantidade());
            pst.setDouble(4, ip.getPreco_unitario());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Itens do pedido cadastrado com sucesso!");

            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public void alterar(Pedido p) throws Exception {
        abrirBanco();
        String query = "UPDATE pedido SET id_cliente = ?, id_vendedor = ?, valor_total = ?, status_pedido = ? WHERE id_pedido = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, p.getId_cliente());
        pst.setInt(2, p.getId_vendedor());
        pst.setDouble(3, p.getValor_total());
        pst.setString(4, p.getStatus_pedido());
        pst.setInt(5, p.getId_pedido());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        fecharBanco();
    }
    public void alterarItens(ItemPedido ip) throws Exception {
        abrirBanco();
        String query = "UPDATE item_pedido SET id_pedido = ?, id_produto = ?, quantidade = ?, preco_unitario = ? WHERE id_item = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, ip.getId_pedido());
        pst.setInt(2, ip.getId_produto());
        pst.setInt(3, ip.getQuantidade());
        pst.setDouble(4, ip.getPreco_unitario());
        pst.setInt(5, ip.getId_item());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        fecharBanco();
    }
    public void deletar(Pedido p) throws Exception {
        abrirBanco();
        String query = "DELETE FROM pedido WHERE id_pedido = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, p.getId_pedido());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pedido deletado com sucesso!");
        fecharBanco();
    }
    public void deletarItem(ItemPedido ip) throws Exception {
        abrirBanco();
        String query = "DELETE FROM item_pedido WHERE id_item = ?";
        pst = con.prepareStatement(query);
        pst.setInt(1, ip.getId_item());
        pst.execute();
        JOptionPane.showMessageDialog(null, "Pedido deletado com sucesso!");
        fecharBanco();
    }
    public void pesquisarPedido(Pedido p) throws Exception {
        try {
            abrirBanco();

            String query = "SELECT * FROM pedido WHERE id_pedido = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, String.valueOf(p.getId_pedido()));

            ResultSet tr = pst.executeQuery();

            if (tr.next()) {
                p.setId_cliente(tr.getInt("id_cliente"));
                p.setId_vendedor(tr.getInt("id_vendedor"));
                p.setValor_total(tr.getDouble("valor_total"));
                p.setStatus_pedido(tr.getString("status_pedido"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }

            tr.close();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public void pesquisarItemPedido(ItemPedido ip) throws Exception {
        try {
            abrirBanco();

            String query = "SELECT * FROM item_pedido WHERE id_item = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, String.valueOf(ip.getId_item()));

            ResultSet tr = pst.executeQuery();

            if (tr.next()) {
                ip.setId_pedido(tr.getInt("id_pedido"));
                ip.setId_produto(tr.getInt("id_produto"));
                ip.setQuantidade(tr.getInt("quantidade"));
                ip.setPreco_unitario(tr.getDouble("preco_unitario"));
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
            }

            tr.close();
            fecharBanco();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    public ArrayList<ItemPedido> pesquisarId(Pedido pedido) throws Exception {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.id_pedido, p.data_pedido, p.valor_total, p.status_pedido, c.nome AS nome_cliente, v.nome AS nome_vendedor, pr.nome AS nome_produto, ip.quantidade, ip.preco_unitario FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido INNER JOIN produto pr ON ip.id_produto = pr.id_produto WHERE p.id_pedido = ? ORDER BY p.id_pedido, pr.nome";
            pst = con.prepareStatement(query);
            pst.setInt(1, pedido.getId_pedido());
            ResultSet tr = pst.executeQuery();
                while (tr.next()){
                    Cliente c = new Cliente();
                    c.setNome(tr.getString("nome_cliente"));
                    Vendedor v = new Vendedor();
                    v.setNome(tr.getString("nome_vendedor"));
                    ItemPedido ip = new ItemPedido();
                    ip.setNome(tr.getString("nome_produto"));
                    ip.setId_pedido(tr.getInt("id_pedido"));
                    ip.setData_pedido(tr.getString("data_pedido"));
                    ip.setValor_total(tr.getDouble("valor_total"));
                    ip.setStatus_pedido(tr.getString("status_pedido"));
                    ip.setCliente(c);
                    ip.setVendedor(v);
                    ip.setQuantidade(tr.getInt("quantidade"));
                    ip.setPreco_unitario(tr.getDouble("preco_unitario"));
                    lista.add(ip);
                } if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
                }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
    public ArrayList<ItemPedido> pesquisarNomeCliente(Cliente cliente) throws Exception {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.id_pedido, p.data_pedido, p.valor_total, p.status_pedido, c.nome AS nome_cliente, v.nome AS nome_vendedor, pr.nome AS nome_produto, ip.quantidade, ip.preco_unitario FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido INNER JOIN produto pr ON ip.id_produto = pr.id_produto WHERE c.nome LIKE CONCAT('%', ?, '%') ORDER BY p.id_pedido, pr.nome";
            pst = con.prepareStatement(query);
            pst.setString(1, cliente.getNome());
            ResultSet tr = pst.executeQuery();
                while (tr.next()){
                    Cliente c = new Cliente();
                    c.setNome(tr.getString("nome_cliente"));
                    Vendedor v = new Vendedor();
                    v.setNome(tr.getString("nome_vendedor"));
                    ItemPedido ip = new ItemPedido();
                    ip.setNome(tr.getString("nome_produto"));
                    ip.setId_pedido(tr.getInt("id_pedido"));
                    ip.setData_pedido(tr.getString("data_pedido"));
                    ip.setValor_total(tr.getDouble("valor_total"));
                    ip.setStatus_pedido(tr.getString("status_pedido"));
                    ip.setCliente(c);
                    ip.setVendedor(v);
                    ip.setQuantidade(tr.getInt("quantidade"));
                    ip.setPreco_unitario(tr.getDouble("preco_unitario"));
                    lista.add(ip);
                } if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
                }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
    public ArrayList<ItemPedido> pesquisarNomeVendedor(Vendedor vendedor) throws Exception {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.id_pedido, p.data_pedido, p.valor_total, p.status_pedido, c.nome AS nome_cliente, v.nome AS nome_vendedor, pr.nome AS nome_produto, ip.quantidade, ip.preco_unitario FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido INNER JOIN produto pr ON ip.id_produto = pr.id_produto WHERE v.nome LIKE CONCAT('%', ?, '%') ORDER BY p.id_pedido, pr.nome";
            pst = con.prepareStatement(query);
            pst.setString(1, vendedor.getNome());
            ResultSet tr = pst.executeQuery();
                while (tr.next()){
                    Cliente c = new Cliente();
                    c.setNome(tr.getString("nome_cliente"));
                    Vendedor v = new Vendedor();
                    v.setNome(tr.getString("nome_vendedor"));
                    ItemPedido ip = new ItemPedido();
                    ip.setNome(tr.getString("nome_produto"));
                    ip.setId_pedido(tr.getInt("id_pedido"));
                    ip.setData_pedido(tr.getString("data_pedido"));
                    ip.setValor_total(tr.getDouble("valor_total"));
                    ip.setStatus_pedido(tr.getString("status_pedido"));
                    ip.setCliente(c);
                    ip.setVendedor(v);
                    ip.setQuantidade(tr.getInt("quantidade"));
                    ip.setPreco_unitario(tr.getDouble("preco_unitario"));
                    lista.add(ip);
                } if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
                }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
    public ArrayList<ItemPedido> pesquisarStatusPedido(Pedido pedido) throws Exception {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.id_pedido, p.data_pedido, p.valor_total, p.status_pedido, c.nome AS nome_cliente, v.nome AS nome_vendedor, pr.nome AS nome_produto, ip.quantidade, ip.preco_unitario FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido INNER JOIN produto pr ON ip.id_produto = pr.id_produto WHERE p.status_pedido LIKE CONCAT('%', ?, '%') ORDER BY p.id_pedido, pr.nome";
            pst = con.prepareStatement(query);
            pst.setString(1, pedido.getStatus_pedido());
            ResultSet tr = pst.executeQuery();
                while (tr.next()){
                    Cliente c = new Cliente();
                    c.setNome(tr.getString("nome_cliente"));
                    Vendedor v = new Vendedor();
                    v.setNome(tr.getString("nome_vendedor"));
                    ItemPedido ip = new ItemPedido();
                    ip.setNome(tr.getString("nome_produto"));
                    ip.setId_pedido(tr.getInt("id_pedido"));
                    ip.setData_pedido(tr.getString("data_pedido"));
                    ip.setValor_total(tr.getDouble("valor_total"));
                    ip.setStatus_pedido(tr.getString("status_pedido"));
                    ip.setCliente(c);
                    ip.setVendedor(v);
                    ip.setQuantidade(tr.getInt("quantidade"));
                    ip.setPreco_unitario(tr.getDouble("preco_unitario"));
                    lista.add(ip);
                } if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
                }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
    public ArrayList<ItemPedido> pesquisarTudo() throws Exception {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.id_pedido, p.data_pedido, p.valor_total, p.status_pedido, c.nome AS nome_cliente, v.nome AS nome_vendedor, pr.nome AS nome_produto, ip.quantidade, ip.preco_unitario FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN vendedor v ON p.id_vendedor = v.id_vendedor INNER JOIN item_pedido ip ON p.id_pedido = ip.id_pedido INNER JOIN produto pr ON ip.id_produto = pr.id_produto ORDER BY p.id_pedido, pr.nome";
            pst = con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
                while (tr.next()){
                    Cliente c = new Cliente();
                    c.setNome(tr.getString("nome_cliente"));
                    Vendedor v = new Vendedor();
                    v.setNome(tr.getString("nome_vendedor"));
                    ItemPedido ip = new ItemPedido();
                    ip.setNome(tr.getString("nome_produto"));
                    ip.setId_pedido(tr.getInt("id_pedido"));
                    ip.setData_pedido(tr.getString("data_pedido"));
                    ip.setValor_total(tr.getDouble("valor_total"));
                    ip.setStatus_pedido(tr.getString("status_pedido"));
                    ip.setCliente(c);
                    ip.setVendedor(v);
                    ip.setQuantidade(tr.getInt("quantidade"));
                    ip.setPreco_unitario(tr.getDouble("preco_unitario"));
                    lista.add(ip);
                } if(lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado!");
                }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }
}

