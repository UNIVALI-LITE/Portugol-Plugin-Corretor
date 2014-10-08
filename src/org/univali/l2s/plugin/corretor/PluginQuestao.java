/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.univali.l2s.plugin.corretor;

import br.univali.portugol.corretor.dinamico.model.Questao;

/**
 *
 * @author Andrei
 */
public class PluginQuestao {
    
    // Status
    static final int EM_ABERTO = 0;
    static final int PARCIAL = 1;
    static final int FINALIZADO = 2;
    
    private Questao questao;
    private int status = 0; 
    private float melhorNota = 0;
    private int tentativas = 0;

    public PluginQuestao(Questao questao) {
        this.questao = questao;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getMelhorNota() {
        return melhorNota;
    }

    public void setMelhorNota(float melhorNota) {
        if (melhorNota > this.melhorNota)
            this.melhorNota = melhorNota;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void realizarTentativa() {
        this.tentativas++;
    }
        
}
