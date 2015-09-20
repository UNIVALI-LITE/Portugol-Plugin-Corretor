/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.univali.l2s.plugin.corretor;

import br.univali.portugol.corretor.dinamico.CasoFalho;
import br.univali.portugol.corretor.dinamico.model.Caso;
import br.univali.portugol.corretor.dinamico.model.Entrada;
import br.univali.portugol.corretor.dinamico.model.Saida;
import br.univali.portugol.corretor.utils.MensagemCorretorEstatico;
import br.univali.portugol.nucleo.asa.TrechoCodigoFonte;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Andrei
 */
public class PainelVisualizador extends javax.swing.JPanel {
    
    private List<MensagemCorretorEstatico> mensagensDoEstatico;
    private List<Caso> casosCorretos;
    private List<CasoFalho> casosErrados;
    private UtilizadorPlugins utilizador;
    private DefaultListModel lista = new DefaultListModel();  
    private PluginDatabase database = new PluginDatabase();
    private boolean focouCasos = false;
    private Map<Integer, Boolean> focouMensagens = new HashMap<>();
    private int ultimoLog;
    
    public PainelVisualizador(List<MensagemCorretorEstatico> mensagensDoEstatico, List<Caso> casosCorretos, List<CasoFalho> casosErrados, UtilizadorPlugins utilizador, int ultimoLog) {
        
        this.mensagensDoEstatico = mensagensDoEstatico;
        this.casosCorretos = casosCorretos;
        this.casosErrados = casosErrados;
        this.utilizador = utilizador;
        this.ultimoLog = ultimoLog;
        
        if(mensagensDoEstatico.size() > 0){
            for(MensagemCorretorEstatico mensagem : mensagensDoEstatico){
                lista.addElement(mensagem.getMensagem());
            }
        }
        
        initComponents();

        if(mensagensDoEstatico.size() > 0){            
            for(int cont = 0; cont < mensagensDoEstatico.size(); cont++){
                focouMensagens.put(cont, false);
            }
            jList1.addListSelectionListener(null);
            jList1.addMouseListener(
                new SelecionadorCodigoFonte(
                    this.utilizador,
                    this.mensagensDoEstatico,
                    this.ultimoLog
                )
            );
        }else{
            jList1.setVisible(false);
            scrollDicas.setVisible(false);
            labelDicas.setVisible(false);  
        }

        exibeCasos();
        
    }
    
    private void exibeCasos(){
        
        DefaultMutableTreeNode casos = new DefaultMutableTreeNode("Casos");

        DefaultMutableTreeNode corretos = new DefaultMutableTreeNode("Corretos");
        
        casos.add(corretos);
        
        int i = 1;
        
        for(Caso caso: casosCorretos){
            DefaultMutableTreeNode no = new DefaultMutableTreeNode("Caso "+i);
            DefaultMutableTreeNode entradas = new DefaultMutableTreeNode("Entradas");    
            DefaultMutableTreeNode saidas = new DefaultMutableTreeNode("Saídas");
            
            for(Entrada entrada : caso.getEntradas()){
                entradas.add(new DefaultMutableTreeNode("("+entrada.getTipodado()+") " + entrada.getValor()));
            }
            
            for(Saida saida : caso.getSaidas()){
                saidas.add(new DefaultMutableTreeNode("("+saida.getTipodado()+") " + saida.getValor()));
            }
            
            no.add(entradas);
            no.add(saidas);
            corretos.add(no);
            
            i++;
        }
        
        DefaultMutableTreeNode errados = new DefaultMutableTreeNode("Errados");

        for(CasoFalho caso: casosErrados){
            DefaultMutableTreeNode no = new DefaultMutableTreeNode("Caso "+i);
            DefaultMutableTreeNode entradas = new DefaultMutableTreeNode("Entradas");    
            DefaultMutableTreeNode saidasEncontradas = new DefaultMutableTreeNode("Saídas Encontradas");
            DefaultMutableTreeNode saidasEsperadas = new DefaultMutableTreeNode("Saídas Esperadas");

            for(Entrada entrada : caso.getCasoTestado().getEntradas()){
                entradas.add(new DefaultMutableTreeNode("("+entrada.getTipodado()+") " + entrada.getValor()));
            }
            
            if (caso.getSaidaEncontrada() != null){
                for(Saida saida : caso.getSaidaEncontrada()){
                    saidasEncontradas.add(new DefaultMutableTreeNode("("+saida.getTipodado()+") " + saida.getValor()));
                }
            }else{
                saidasEncontradas.add(new DefaultMutableTreeNode("Nenhuma saída encontrada!"));
            }
            
            for(Saida saida :  caso.getCasoTestado().getSaidas()){
                saidasEsperadas.add(new DefaultMutableTreeNode("("+saida.getTipodado()+") " + saida.getValor()));
            }
           
            
            no.add(entradas);
            no.add(saidasEncontradas);
            no.add(saidasEsperadas);
            errados.add(no);
            
            i++;
        }
        
        casos.add(errados);
        
        arvoreCasos.setModel(new DefaultTreeModel(casos));
        
    }
    
    private class SelecionadorCodigoFonte extends MouseAdapter{
        
        private UtilizadorPlugins utilizador;
        private List<MensagemCorretorEstatico> mensagensDoEstatico;
        private int ultimoLog;

        public SelecionadorCodigoFonte(UtilizadorPlugins utilizador, List<MensagemCorretorEstatico> mensagensDoEstatico, int ultimoLog) {
            this.utilizador = utilizador;
            this.mensagensDoEstatico = mensagensDoEstatico;
            this.ultimoLog = ultimoLog;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            int index = jList1.getSelectedIndex();
            if(index > -1){
                final MensagemCorretorEstatico mensagem = mensagensDoEstatico.get(jList1.getSelectedIndex());
                if(mensagem.getTrecho() != null){
                    this.utilizador.destacarTrechoCodigoFonte(
                            mensagem.getTrecho().getLinha()-1,
                            mensagem.getTrecho().getColuna(),
                            mensagem.getTrecho().getTamanhoTexto());
                }
                if(!focouMensagens.get(index)){
                    focouMensagens.put(index, true);
                    // Inicia uma thread para não prejudicar desempanho
                    SwingUtilities.invokeLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            database.gravaLogFoco(ultimoLog, mensagem.getDados());
                        }
                    });
                }
            }
            //this.utilizador.ocultarPainelFlutuante();
        }
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        labelDicas = new javax.swing.JLabel();
        scrollDicas = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        arvoreCasos = new javax.swing.JTree();

        jScrollPane1.setViewportView(jTree1);

        labelDicas.setText("Dicas:");

        scrollDicas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jList1.setModel(lista);
        scrollDicas.setViewportView(jList1);

        jLabel2.setText("Casos de Testes:");

        arvoreCasos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arvoreCasosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(arvoreCasos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(98, 390, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollDicas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelDicas)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDicas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollDicas, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void arvoreCasosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arvoreCasosMouseClicked
        if(!focouCasos){
            focouCasos = true;
            // Inicia uma thread para não prejudicar desempanho
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    database.gravaLogFoco(ultimoLog, "(Casos de Testes){Usuário clicou nos casos!}");
                }
            });
        }
    }//GEN-LAST:event_arvoreCasosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arvoreCasos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel labelDicas;
    private javax.swing.JScrollPane scrollDicas;
    // End of variables declaration//GEN-END:variables

}
