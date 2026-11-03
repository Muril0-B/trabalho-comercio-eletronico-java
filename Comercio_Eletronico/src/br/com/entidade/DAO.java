package br.com.entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
<<<<<<< HEAD
  Connection con;// criação do objeto conexao
    PreparedStatement pst;//criação do objeto preparação de ambiente
    ResultSet rs;// criação do objeto que recebe resultados
   
    public void abrirBanco() throws SQLException {// criando metodo para acessar o banco
      try {
        Class.forName("com.mysql.jdbc.Driver");//classe para utilização do arquivo com configurções do serivdor mysql
        String url = "jdbc:mysql://localhost/crudjava10";// drive servidor e banco de dados a serem utilizados e indicação do banco a ser utilizado
        String user ="root";// usuario do banco de dados
        String senha ="";//senha do usuario do banco de dados    
        con=(Connection) DriverManager.getConnection(url,user,senha);//metodo que usa os parametros para conectar com o banco
         System.out.println("Conectado ao banco de dados ");
      } catch (ClassNotFoundException ex) {//tratamento de erro de drive
          System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
        Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException e) {//tratamento de erro de SQL
          System.out.println(e);
          throw new RuntimeException(e);
       } 
     }    
    
    public void fecharBanco() throws Exception{//criando metodo que fechao conexão com o banco
       if (pst!= null) { //limpando os dados de conexão   
            pst.close();//fechando o ambiente de conexão
            System.out.println("Execuçao da Query fechada\n");
    }    }
}
=======
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void abrirBanco() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mercadinho";
            String user = "root";
            String senha = "";
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
>>>>>>> cb5edcfd4dae74c275f6119ea22093d2a5e18be2
