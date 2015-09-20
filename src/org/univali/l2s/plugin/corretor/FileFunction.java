package org.univali.l2s.plugin.corretor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrei
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileFunction {

    public static String lerArquivoLocal(String fileLocal) {
        StringBuilder retorno = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(FileFunction.class.getResourceAsStream(fileLocal)));
            String str;
            while (in.ready()) {
                str = in.readLine();
                retorno.append(str);
                retorno.append("\n");
            }
            in.close();
        } catch (IOException e) {
            //
        }

        return retorno.toString();
    }

    public static Properties getProperties(String fileLocal) {
        try {
            Properties props = new Properties();
            InputStreamReader file = new InputStreamReader(FileFunction.class.getResourceAsStream(fileLocal));
            props.load(file);
            return props;
        } catch (Exception e) {
            return null;
        }
    }

}