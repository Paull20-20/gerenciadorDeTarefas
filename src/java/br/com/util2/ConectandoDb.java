package br.com.util2;


import br.com.entidade2.Pessoa;
import br.com.bean2.PessoaBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectandoDb {
   
    private Connection connection;
     
    public ConectandoDb(){
          
            try {
                Class.forName("org.postgresql.Driver");
                this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banco1", "postgres", "root");
                 System.out.println("\nConectado com sucesso!");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        
     
    
    }
        
    
    public void fecharConexao(){
        if(connection != null){
            try {
                connection.close();
                connection = null;
                System.out.println("\nDados inseridos com sucesso e conexão encerrada!");
            } catch (SQLException ex) {
                Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    //Este método está servindo para inserção e edição!
    public void inserir(Pessoa pessoa1){
        
        //String sql = "INSERT INTO `pessoa` (titulo2,descricao2,deadline2,responsavel2,prioridade2) VALUES(?,?,?,?,?)";
        
        try {
                PreparedStatement stmt;
           // PreparedStatement stmt = connection.prepareStatement(sql);
           if(pessoa1.getId() == null){
               stmt = connection.prepareStatement("INSERT INTO pessoa (titulo2 , descricao2, deadline2, responsavel2 , prioridade2) VALUES(?,?,?,?,?)");
           }else{
               stmt = connection.prepareStatement("UPDATE pessoa SET titulo2=?, descricao2=?, deadline2=?, responsavel2=?, prioridade2=? WHERE id=?");
               stmt.setInt(6, pessoa1.getId());
           }
           //stmt.setLong(1, pessoa1.getId());
            stmt.setString(1, pessoa1.getTitulo2());
            stmt.setString(2, pessoa1.getDescricao2());
            stmt.setString(3, pessoa1.getDeadline2());
            stmt.setString(4, pessoa1.getResponsavel2());
            stmt.setString(5, pessoa1.getPrioridade2());
            stmt.execute();
            
            //fecharConexao(); //Buga o sistema, deixa conexão null no método abaixo!
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            // return false;
        }
        
       // return false;
       
    }
 
    
    public List<Pessoa> listar(){
       // String sql = "SELECT * FROM pessoa";
        
        
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM pessoa");
            ResultSet resultado = stmt.executeQuery();
            List<Pessoa> pessoa = new ArrayList<>();
            
            while(resultado.next()){ //next percorre todos os registros do banco! Acessando o primeiro registro da listagem que o Resultset cria!
                Pessoa pessoa1 = new Pessoa();
                pessoa1.setId(resultado.getInt("id")); // "id" = Se refere a coluna id do DB
                pessoa1.setTitulo2(resultado.getString("titulo2"));
                pessoa1.setDescricao2(resultado.getString("descricao2"));
                pessoa1.setDeadline2(resultado.getString("deadline2"));
                pessoa1.setResponsavel2(resultado.getString("responsavel2"));
                pessoa1.setPrioridade2(resultado.getString("prioridade2"));
                pessoa.add(pessoa1); //Adicionando na lista
                
            }
             return pessoa;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            return null; // Retornando valor null caso ocorra algum bug
        }
        
      
      
    }
    
    public boolean delete(Integer id){
       // String sql = "DELETE FROM pessoa WHERE id=?";  
       
       
        try {          
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM pessoa WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("\nExcluído com sucesso!");
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nNão foi excluído ocorreu algum erro!");
            return false;
        }
        
                
        
    }
    
    
    public boolean finalizarTarefa(Integer id){
            try {          
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM pessoa WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("\nExcluído com sucesso!");
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nNão foi excluído ocorreu algum erro!");
            return false;
        }
    }

    
    //NÃO ESTÁ SENDO UTILIZADO POIS UTILIZEI O MÉTODO INSERIR PARA FAZER A EDIÇÃO DOS DADOS!
    public void editar(Pessoa pessoa1){
             try {
            
           // PreparedStatement stmt = connection.prepareStatement(sql);
            PreparedStatement stmt = connection.prepareStatement("UPDATE pessoa SET titulo2=?, descricao2=?, deadline2=?, responsavel2=?, prioridade2=? WHERE id=?");
          //  stmt.setLong(1, pessoa1.getId());
            stmt.setString(1, pessoa1.getTitulo2());
            stmt.setString(2, pessoa1.getDescricao2());
            stmt.setString(3, pessoa1.getDeadline2());
            stmt.setString(4, pessoa1.getResponsavel2());
            stmt.setString(5, pessoa1.getPrioridade2());
            stmt.setInt(6, pessoa1.getId());
            stmt.execute();
            //return true;
            //fecharConexao(); //observar para ver se n buga o sistema!
            
        } catch (SQLException ex) {
            Logger.getLogger(ConectandoDb.class.getName()).log(Level.SEVERE, null, ex);
            // return false;
        }
        
    }
    
    
}
