package br.com.cast.avaliacao.service.business;

import br.com.cast.avaliacao.util.IMsg;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Rn0003 {

    public void process(final Calendar dataInicial, final Calendar dataFinal) {
        if (dataInicial.after(dataFinal)) {
            throw new BusinessException(IMsg.MSG_0004);
        }
    }
}
