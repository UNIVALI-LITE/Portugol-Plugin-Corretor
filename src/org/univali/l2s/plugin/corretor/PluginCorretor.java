package org.univali.l2s.plugin.corretor;

import br.univali.portugol.corretor.dinamico.model.Questao;
import br.univali.ps.plugins.base.Plugin;
import br.univali.ps.plugins.base.UtilizadorPlugins;
import br.univali.ps.plugins.base.VisaoPlugin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Andrei
 */
public final class PluginCorretor extends Plugin
{
    public static List<PluginQuestao> questoes = new ArrayList<>();
    public static boolean iniciou = false;
    public static String usuario;
    public int questaoSelecionada;
    public int estado = 0; // 0 - menu, 1 - questão    
    private VisaoPlugin visao;
    private UtilizadorPlugins utilizador;
    
    @Override
    protected void inicializar(UtilizadorPlugins utilizador)
    {       
        this.utilizador = utilizador;
        // Busca questões
        if(!iniciou){
            iniciou = true;
            
            try {
                // Salva usuário no arquivo para nao perder se fechar o editor
                File f = new File("plugin-corretor.dat");
                if(f.exists()){
                    Scanner fileScanner = new Scanner(f);
                    if(fileScanner.hasNext()){
                        PluginCorretor.usuario = fileScanner.next();
                    }
                    fileScanner.close();
                } else {
                    PluginCorretor.usuario = UUID.randomUUID().toString();
                    f.createNewFile();
                    FileWriter fw = new FileWriter(f, true);
                    for (int i = 0; i < PluginCorretor.usuario.length(); i++) {
                        fw.append(PluginCorretor.usuario.charAt(i));
                    }
                    fw.close();
                }
                // ---
                String[] exercicios = new String[12];
                String prefix = "/org/univali/l2s/plugin/corretor/questoes/";
                exercicios[0] = "exercicio14.pex";
                exercicios[1] = "exercicio18.pex";
                exercicios[2] = "exercicio27.pex";
                exercicios[3] = "exercicio29.pex";
                exercicios[4] = "exercicio30.pex";
                exercicios[5] = "exercicio34.pex";
                exercicios[6] = "exercicio40.pex";
                exercicios[7] = "exercicio45.pex";
                exercicios[8] = "exercicio47.pex";
                exercicios[9] = "exercicio51.pex";
                exercicios[10] = "exercicio57.pex";
                exercicios[11] = "exercicio60.pex";
                int i = 0;
                for (String s : exercicios) {
                    InputStream in = this.getClass().getResourceAsStream(prefix + s);
                    this.getClass().getResourceAsStream(s);
                    JAXBContext jc = JAXBContext.newInstance(Questao.class);  
                    Unmarshaller u = jc.createUnmarshaller();
                    Questao questao = (Questao) u.unmarshal(in);
                    PluginCorretor.questoes.add(new PluginQuestao(questao));
                }
            } catch (JAXBException ex) {
                Logger.getLogger(PluginCorretor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PluginCorretor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public UtilizadorPlugins getUtilizador() 
    {
        return utilizador;
    }

    @Override
    protected void finalizar(UtilizadorPlugins utilizador){
        // Do Nothing
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
    
    public void atualizaPainel(){
        ((PluginCorretorVisao) getVisao()).exibirPainel();
    }
    
    public void avancaQuestao(){
        if(this.questaoSelecionada < PluginCorretor.questoes.size()-1){
            this.questaoSelecionada++;
        }
    }
    
    public void retrocedeQuestao(){
        if(this.questaoSelecionada > 0){
            this.questaoSelecionada--;
        }
    }

}
