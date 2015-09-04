package com.lifeproject;

import com.lifeproject.data.*;
import me.nbeaussart.gui.JConsole;
import me.nbeaussart.gui.Tools;

import java.awt.*;

/**
 * Created by beaussan on 03/09/15.
 */
public class TerminalOutput {


    private static final SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
    private static final NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");

    public TerminalOutput(){

        JConsole jc = new JConsole(100,40);
        Tools.showComponent(jc);
        jc.setCursorVisible(true);
        jc.setCursorBlink(true);

        System.out.println(nr.getNom());


        Humain hum = new Humain("Nicolas","Paul",true);


        GeneratorUtil.randomizeNameOf(hum, nr, sr);
        hum.setHistory(History.getInstance().getHistory(hum));
        outHum(hum, jc);

        String in;
        do {
            in = jc.read(new String[]{"Recommencer", "Sauvegarder", "Quitter"});
            if (in.equals("Recommencer")){
                jc.clearScreen();
                GeneratorUtil.randomizeNameOf(hum, nr, sr);
                hum.setHistory(History.getInstance().getHistory(hum));
                outHum(hum, jc);
            } else if (in.equals("Sauvegarder")){
                SavingUtil.writeToFile(hum);
            }
        } while (!in.equals("Quitter"));
        System.exit(0);

    }

    private void outHum(Humain hum, JConsole jc){

        if (hum.isMasculin()){
            jc.setForeground(new Color(0, 138, 178));
        } else {
            jc.setForeground(new Color(127, 0, 0));
        }
        jc.writeln(hum.getName() + " " + hum.getSurname());
        switch (hum.getRace()){
            case DWARF:
                jc.setForeground(new Color(0, 255, 140));
                break;
            case ELF:
                jc.setForeground(new Color(255, 191, 0));
                break;
            case HOBBIT:
                jc.setForeground(new Color(0, 64, 255));
                break;
            case HUMAIN:
                jc.setForeground(new Color(127, 127, 0));
                break;
            case MORTIS:
                jc.setForeground(new Color(178, 53, 0));
                break;
            case ORC:
                jc.setForeground(new Color(178, 0, 53));
                break;
        }
        jc.writeln(hum.getRace().name());
        jc.setForeground(new Color(0, 127, 0));
        jc.writeln(hum.getHistory());
        jc.setForeground(new Color(192, 192, 192));
        jc.writeln("____________");
        jc.setForeground(new Color(192, 192, 192));
    }

}