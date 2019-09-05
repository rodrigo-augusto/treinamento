package br.com.cast.avaliacao.business;

import br.com.cast.avaliacao.ui.IMsg;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Rn0003 {

    public void process(final Calendar dataInicial, final Calendar dataFinal) {
        if (dataInicial.after(dataFinal)) {
            throw new RuntimeException(IMsg.MSG_0004);
        }
    }
}
