package org.univali.l2s.plugin.corretor;

import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import br.univali.ps.plugins.base.VisaoPlugin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luiz Fernando Noschang
 */
public final class PluginCorretor extends Plugin
{
    private final static List<PluginCorretor> instancias = new ArrayList<>();    
    private VisaoPlugin visao;
    private UtilizadorPlugins utilizador;

    @Override
    protected void inicializar(UtilizadorPlugins utilizador)
    {
        registrarInstancia(this);
        
        this.utilizador = utilizador;
    }

    public UtilizadorPlugins getUtilizador() 
    {
        return utilizador;
    }

    @Override
    protected void finalizar(UtilizadorPlugins utilizador)
    {
        removerInstancia(this);
    }

    @Override
    public VisaoPlugin getVisao()
    {
        if (visao == null)
        {
            visao = new PluginCorretorVisao(this);
        }

        return visao;
    }

    private void registrarInstancia(PluginCorretor plugin)
    {
        if (!instancias.contains(plugin))
        {
            instancias.add(plugin);
        }
    }

    private void removerInstancia(PluginCorretor plugin)
    {
        if (instancias.contains(plugin))
        {
            instancias.remove(plugin);
        }
    }

    private void atualizarInstancias()
    {
        for (PluginCorretor instancia : instancias)
        {
            PluginCorretorVisao visaoPlugin = (PluginCorretorVisao) instancia.getVisao();

            visaoPlugin.exibirPainelCorretor();
        }
    }
}
