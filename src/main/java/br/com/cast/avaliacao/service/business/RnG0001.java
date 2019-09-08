package br.com.cast.avaliacao.service.business;

import br.com.cast.avaliacao.component.InputValidator;
import br.com.cast.avaliacao.util.IMsg;
import org.springframework.stereotype.Component;

@Component
public class RnG0001 {

    public void process(final InputValidator inputValidator) {
        final Object[] args = inputValidator.getInvalidInputs();
        if (args.length != 0) {
            throw new BusinessException(IMsg.MSG_0006, args);
        }
    }

}
