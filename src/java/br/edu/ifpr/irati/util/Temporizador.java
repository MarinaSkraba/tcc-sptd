package br.edu.ifpr.irati.util;

import br.edu.ifpr.irati.dao.Dao;
import br.edu.ifpr.irati.dao.GenericDAO;
import br.edu.ifpr.irati.mb.PTDIncompletoMB;
import br.edu.ifpr.irati.modelo.PTDIncompleto;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.hibernate.HibernateException;

public class Temporizador {

    public static final long TEMPO = (5000 * 60); //atualiza o site a cada 5 minutos
    Timer timer = null;
    PTDIncompletoMB ptdIncompletoMB;

    public Temporizador() {
        ptdIncompletoMB = new PTDIncompletoMB();
        if ((timer == null)) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                public void run() {
                    try {
                        ptdIncompletoMB.alterarPTDIncompleto(null);
                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }

}

