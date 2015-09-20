/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.univali.l2s.plugin.corretor;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3436101
 */
public class PluginDatabase {
    
    String url, dbName, driver, userName, password;
    private Connection conexao;

    private void open() {
        Properties props = FileFunction.getProperties("/br/univali/astpcore/conexao/database.properties");
        userName = props.getProperty("database.user");
        password = props.getProperty("database.password");
        url = props.getProperty("database.url");
        driver = props.getProperty("database.driver");
        try {
            Class.forName(driver).newInstance();
            this.conexao = DriverManager.getConnection( url+dbName, userName, password);
        } catch (Exception ex) {
            Logger.getLogger(PluginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void close(){
        try {
            conexao.close();
        } catch (Exception ex) {
            Logger.getLogger(PluginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int gravaLogCorrecao(String usuario, String tituloExercicio, int tentativa, float nota, String mensagensEstatico, String codigoFonte){
        int id = 0;
        try {
            open();
            
            String query = " insert into eventocorretor (usuario, tituloExercicio, tentativa, nota, mensagensEstatico, codigoFonte, hora)"
                         + " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario);
            ps.setString(2, tituloExercicio);
            ps.setInt(3, tentativa);
            ps.setFloat(4, nota);
            ps.setString(5, mensagensEstatico);
            ps.setString(6, codigoFonte);
            ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
            
            ps.executeUpdate();
            
            // Busca id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            
            close();
            
        } catch (Exception ex) {
            Logger.getLogger(PluginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public void gravaLogFoco(int id, String mensagem){
        try {
            open();
            
            String query = " insert into eventocorretorfoco (idEventoFoco, mensagem, hora)"
                         + " values (?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, mensagem);
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));            
            ps.executeUpdate();
            
            close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PluginDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
