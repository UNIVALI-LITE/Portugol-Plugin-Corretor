package org.univali.l2s.plugin.corretor;

import br.univali.portugol.corretor.dinamico.CasoFalho;
import br.univali.portugol.corretor.dinamico.Corretor;
import br.univali.portugol.corretor.dinamico.model.Caso;
import br.univali.portugol.corretor.dinamico.model.Questao;
import br.univali.portugol.corretor.estatico.CorretorEstatico;
import br.univali.portugol.corretor.utils.MensagemCorretorEstatico;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Andrei Hodecker
 */
public final class PainelCorretor extends JPanel
{
    private PluginCorretor plugin;
    private PainelVisualizador visualizador;
    private PluginSeletorQuestoes seletorQuestoes;
    private Questao questao;
    private List<Caso> casosCorretos;
    private List<CasoFalho> casosErrados;
    private List<MensagemCorretorEstatico> mensagensDoEstatico;

    public PainelCorretor(PluginCorretor plugin)
    {
        initComponents();
        botaoCorrigir.setEnabled(false);
        this.plugin = plugin;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        botaoCorrigir = new javax.swing.JButton();
        botaoExercicios = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        barraDeProgresso = new javax.swing.JProgressBar();
        botaoAnterior = new javax.swing.JButton();
        botaoMenu = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(160, 50));

        botaoCorrigir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/univali/l2s/plugin/corretor/icones/yes.png"))); // NOI18N
        botaoCorrigir.setText("Corrigir");
        botaoCorrigir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCorrigirActionPerformed(evt);
            }
        });

        botaoExercicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/univali/l2s/plugin/corretor/icones/folder_open.png"))); // NOI18N
        botaoExercicios.setText("Exercícios");
        botaoExercicios.setBorderPainted(false);
        botaoExercicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExerciciosActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        barraDeProgresso.setForeground(new java.awt.Color(0, 153, 0));
        barraDeProgresso.setStringPainted(true);

        botaoAnterior.setText("Anterior");

        botaoMenu.setText("Menu");

        botaoProximo.setText("Próximo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(botaoCorrigir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barraDeProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoExercicios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoMenu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(botaoExercicios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCorrigir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraDeProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoMenu)
                        .addComponent(botaoProximo))
                    .addComponent(botaoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoExerciciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExerciciosActionPerformed
        seletorQuestoes = new PluginSeletorQuestoes(this);
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                seletorQuestoes.setVisible(true);
                seletorQuestoes.setLocationRelativeTo(null);
            }
            
        });
    }//GEN-LAST:event_botaoExerciciosActionPerformed

    private void botaoCorrigirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCorrigirActionPerformed
        UtilizadorPlugins utilizador = plugin.getUtilizador();
        String source = utilizador.obterCodigoFonteUsuario();
        
        // Corretor dinâmico
        Corretor corretorDinamico = new Corretor(questao);
        
        // Corretor Estático
        CorretorEstatico estatico = new CorretorEstatico(questao);
        
        try {
            corretorDinamico.executar(source, null);
            this.mensagensDoEstatico = estatico.executar(source, null);
        } catch (ErroCompilacao | ExcecaoVisitaASA ex){
            JOptionPane.showMessageDialog(null, 
                    "Tente executar o seu programa e corrija os erros apontados!",
                    "Erro de Compilação",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        this.casosCorretos = corretorDinamico.getCasosAcertados();
        this.casosErrados = corretorDinamico.getCasosFalhos();
        int qtdCasosCorretos = this.casosCorretos.size();
        int qtdCasosErrados = this.casosErrados.size();
        int total = qtdCasosCorretos + qtdCasosErrados;
        
        barraDeProgresso.setMinimum(0);
        barraDeProgresso.setMaximum(total);
        barraDeProgresso.setValue(qtdCasosCorretos);
        
        for(String s : corretorDinamico.listarMensagens()){
            mensagensDoEstatico.add(new MensagemCorretorEstatico(s));
        }
        
        this.visualizador = new PainelVisualizador(mensagensDoEstatico, casosCorretos, casosErrados);
        utilizador.exibirPainelFlutuante(botaoCorrigir, this.visualizador, true);
        
    }//GEN-LAST:event_botaoCorrigirActionPerformed
    
    public void carregaQuestao(Questao q){
        this.questao = q;
        jTextPane1.setText(q.getEnunciado());
        botaoCorrigir.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraDeProgresso;
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoCorrigir;
    private javax.swing.JButton botaoExercicios;
    private javax.swing.JButton botaoMenu;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

}
