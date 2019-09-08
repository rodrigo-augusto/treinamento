package br.com.cast.avaliacao.service.business;

import br.com.cast.avaliacao.util.IMsg;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class Rn0001 {

    public <ID, E> void process(final ID id, final Function<ID, Optional<E>> function) {
        Optional.ofNullable(id)
                .map(function)
                .ifPresent((optE) -> {
                    if (optE.equals(Optional.empty())) {
                        throw new BusinessException(IMsg.MSG_0002);
                    }
                });

    }

}
