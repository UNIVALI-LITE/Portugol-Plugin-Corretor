package org.univali.l2s.plugin.corretor;

import br.univali.ps.plugins.base.VisaoPlugin;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author Luiz Fernando Noschang
 */
public final class PluginCorretorVisao extends VisaoPlugin
{
    private PainelCorretor painelCorretor;
    
    public PluginCorretorVisao(PluginCorretor plugin)
    {
        super(plugin);
        initComponents();

        exibirPainelCorretor();
    }

    private PainelCorretor getPainelCorretor()
    {
        if (painelCorretor == null)
        {
            painelCorretor = new PainelCorretor((PluginCorretor) getPlugin());
        }

        return painelCorretor;
    }

    public void exibirPainelCorretor()
    {
        add(getPainelCorretor(), BorderLayout.CENTER);

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
