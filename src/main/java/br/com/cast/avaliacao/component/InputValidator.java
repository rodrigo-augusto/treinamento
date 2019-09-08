package br.com.cast.avaliacao.component;

import br.com.cast.avaliacao.util.IMsg;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class InputValidator implements MessageSourceAware {

    private static final String DELIMITER = ", ";
    public static final Object[] DEFAULT_OBJECTS = {};
    private List<String> invalidInputs = new ArrayList<>();

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public InputValidator check(final Supplier<Boolean> supplier, final String input) {
        if (!supplier.get()) {
            invalidInputs.add(input);
        }
        return this;
    }

    public Object[] getInvalidInputs() {
        final String msg = invalidInputs.stream()
                .map((codeStr) -> messageSource.getMessage(
                        codeStr,
                       null,
                        Locale.getDefault()))
                .collect(Collectors.joining(DELIMITER));
        return msg.isEmpty() ? DEFAULT_OBJECTS : Collections.singletonList(msg).toArray();
    }


}
