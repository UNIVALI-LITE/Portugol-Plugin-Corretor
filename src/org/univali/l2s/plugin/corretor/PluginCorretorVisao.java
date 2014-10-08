package org.univali.l2s.plugin.corretor;

import br.univali.ps.plugins.base.VisaoPlugin;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Luiz Fernando Noschang
 */
public final class PluginCorretorVisao extends VisaoPlugin
{
    private JPanel painel;
    
    public PluginCorretorVisao(PluginCorretor plugin)
    {
        super(plugin);
        initComponents();

        exibirPainel();
    }

    private JPanel getPainel()
    {
        PluginCorretor plugin = (PluginCorretor) getPlugin();
        if (plugin.estado == 0){
            painel = new PainelQuestoes(plugin);
        } else {
            painel = new PainelCorretor(plugin);
        }
        return painel;
    }

    public void exibirPainel()
    {
        this.removeAll();
        add(getPainel(), BorderLayout.CENTER);

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                validate();
                repaint();
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
