package br.com.cast.avaliacao.business;

import br.com.cast.avaliacao.ui.IMsg;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Rn0002 {

    public void process(final Calendar data) {
        if (data.before(Calendar.getInstance())) {
            throw new RuntimeException(IMsg.MSG_0003);
        }
    }

}
