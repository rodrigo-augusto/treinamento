package br.com.cast.avaliacao.business;

import br.com.cast.avaliacao.ui.IMsg;
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
                        throw new RuntimeException(IMsg.MSG_0002);
                    }
                });

    }

}
