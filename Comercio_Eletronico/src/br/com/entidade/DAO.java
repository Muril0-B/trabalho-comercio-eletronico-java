package br.com.entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void abrirBanco() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mercadinho";
            String user = "root";
            String senha = "Murilo2007!@";
            con = DriverManager.getConnection(url, user, senha);
            System.out.println("Conectado ao banco de dados!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver JDBC do MySQL não encontrado!");
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void fecharBanco() throws Exception {
        if (pst != null) {
            pst.close();
        }
        if (con != null) {
            con.close();
        }
        System.out.println("Conexão fechada com sucesso!");
    }
}
