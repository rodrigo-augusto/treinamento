package br.com.cast.avaliacao.service.business;

import br.com.cast.avaliacao.util.IMsg;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Rn0002 {

    public void process(final Calendar data) {
        if (data.before(Calendar.getInstance())) {
            throw new BusinessException(IMsg.MSG_0003);
        }
    }

}
