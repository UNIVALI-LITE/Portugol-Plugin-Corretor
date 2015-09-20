package org.univali.l2s.plugin.corretor;

import br.univali.portugol.corretor.dinamico.CasoFalho;
import br.univali.portugol.corretor.dinamico.Corretor;
import br.univali.portugol.corretor.dinamico.model.Caso;
import br.univali.portugol.corretor.estatico.CorretorEstatico;
import br.univali.portugol.corretor.utils.MensagemCorretorEstatico;
import br.univali.portugol.nucleo.ErroCompilacao;
import br.univali.portugol.nucleo.asa.ExcecaoVisitaASA;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Andrei Hodecker
 */
public final class PainelCorretor extends JPanel
{
    private PluginCorretor plugin;
    private PainelVisualizador visualizador;
    private PluginQuestao questao;
    private List<Caso> casosCorretos;
    private List<CasoFalho> casosErrados;
    private List<MensagemCorretorEstatico> mensagens;
    private int ultimoLogId = 0;
    private String ultimoCodigoFonte;

    public PainelCorretor(PluginCorretor plugin)
    {
        initComponents();
        this.plugin = plugin;
        carregaQuestao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        botaoCorrigir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textEnunciado = new javax.swing.JTextPane();
        barraDeProgresso = new javax.swing.JProgressBar();
        botaoAnterior = new javax.swing.JButton();
        botaoMenu = new javax.swing.JButton();
        botaoProximo = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();

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

        textEnunciado.setEditable(false);
        jScrollPane1.setViewportView(textEnunciado);

        barraDeProgresso.setForeground(new java.awt.Color(0, 153, 0));
        barraDeProgresso.setStringPainted(true);

        botaoAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/univali/l2s/plugin/corretor/icones/resultset_previous.png"))); // NOI18N
        botaoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnteriorActionPerformed(evt);
            }
        });

        botaoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/univali/l2s/plugin/corretor/icones/house.png"))); // NOI18N
        botaoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMenuActionPerformed(evt);
            }
        });

        botaoProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/univali/l2s/plugin/corretor/icones/resultset_next.png"))); // NOI18N
        botaoProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximoActionPerformed(evt);
            }
        });

        labelTitulo.setText("Título");

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
                        .addComponent(labelTitulo)
                        .addGap(0, 213, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(labelTitulo)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCorrigir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraDeProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoMenu)
                    .addComponent(botaoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCorrigirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCorrigirActionPerformed
       
        UtilizadorPlugins utilizador = plugin.getUtilizador();
        String source = utilizador.obterCodigoFonteUsuario();
        
        if(!source.equals(this.ultimoCodigoFonte)){
                
            this.mensagens = new ArrayList<>();
            boolean ocorreuErro = false;
            
            if(this.questao.getStatus() != PluginQuestao.FINALIZADO)
                this.questao.setStatus(PluginQuestao.PARCIAL);

            // Corretor dinâmico
            Corretor corretorDinamico = new Corretor(questao.getQuestao());

            // Corretor Estático
            CorretorEstatico estatico = new CorretorEstatico(questao.getQuestao());

            try {          
                corretorDinamico.executar(source, null); 
                for(String s : corretorDinamico.listarMensagens()){
                    this.mensagens.add(new MensagemCorretorEstatico(s));
                }            
                this.mensagens.addAll(estatico.executar(source, null));
            } catch (ErroCompilacao | ExcecaoVisitaASA ex){
                ocorreuErro = true;
                JOptionPane.showMessageDialog(null, 
                        "Tente executar o seu programa e corrija os erros apontados!",
                        "Erro de Compilação",
                        JOptionPane.ERROR_MESSAGE);
            }
            
            if(!ocorreuErro){ // Se não ocorreu erro
                
                this.questao.realizarTentativa();
                this.ultimoCodigoFonte = source;
                
                this.casosCorretos = corretorDinamico.getCasosAcertados();
                this.casosErrados = corretorDinamico.getCasosFalhos();
                int qtdCasosCorretos = this.casosCorretos.size();
                int qtdCasosErrados = this.casosErrados.size();
                int total = qtdCasosCorretos + qtdCasosErrados;

                barraDeProgresso.setMinimum(0);
                barraDeProgresso.setMaximum(total);
                barraDeProgresso.setValue(qtdCasosCorretos);

                // Acertou todas
                if(qtdCasosErrados == 0 && this.questao.getStatus() != PluginQuestao.FINALIZADO){
                    this.questao.setStatus(PluginQuestao.FINALIZADO);
                }

                float nota = ((100*qtdCasosCorretos)/total)/10;
                this.questao.setMelhorNota(nota);

                String todasMensagens = "";
                for(MensagemCorretorEstatico m : this.mensagens){
                    todasMensagens += m.getDados() + "\n";
                }

                PluginDatabase pd = new PluginDatabase();
                ultimoLogId = pd.gravaLogCorrecao(PluginCorretor.usuario, 
                                    questao.getQuestao().getTitulo(),
                                    questao.getTentativas(),
                                    nota,
                                    todasMensagens,
                                    source);
   
                this.visualizador = new PainelVisualizador(
                                    mensagens,
                                    casosCorretos,
                                    casosErrados,
                                    utilizador,
                                    this.ultimoLogId);
                
                utilizador.exibirPainelFlutuante(botaoCorrigir, this.visualizador, true);
            }

        } else {
            utilizador.exibirPainelFlutuante(botaoCorrigir, this.visualizador, true);
        }
        
    }//GEN-LAST:event_botaoCorrigirActionPerformed

    private void botaoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMenuActionPerformed
        this.plugin.estado = 0;
        this.plugin.atualizaPainel();
    }//GEN-LAST:event_botaoMenuActionPerformed

    private void botaoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnteriorActionPerformed
        this.plugin.retrocedeQuestao();
        carregaQuestao();
    }//GEN-LAST:event_botaoAnteriorActionPerformed

    private void botaoProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximoActionPerformed
        this.plugin.avancaQuestao();
        carregaQuestao();
    }//GEN-LAST:event_botaoProximoActionPerformed
       
    private void carregaQuestao() {
        this.questao = PluginCorretor.questoes.get(this.plugin.questaoSelecionada);
        this.textEnunciado.setText(this.questao.getQuestao().getEnunciado());
        this.labelTitulo.setText(this.questao.getQuestao().getTitulo());
        this.ultimoCodigoFonte = "";
        this.barraDeProgresso.setValue(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraDeProgresso;
    private javax.swing.JButton botaoAnterior;
    private javax.swing.JButton botaoCorrigir;
    private javax.swing.JButton botaoMenu;
    private javax.swing.JButton botaoProximo;
    private javax.swing.JButton jButton1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextPane textEnunciado;
    // End of variables declaration//GEN-END:variables

}
