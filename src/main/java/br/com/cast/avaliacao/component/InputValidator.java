package br.com.cast.avaliacao.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

@Component
public class InputValidator implements MessageSourceAware {

    private static final String DELIMITER = ", ";
    private static final Object[] DEFAULT_OBJECTS = {};

    private List<String> invalidInputs = new ArrayList<>();
    private Map<Supplier<Boolean>, String> mapInputs = new HashMap<>();
    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public InputValidator check(final Supplier<Boolean> supplier, final String input) {
        mapInputs.put(supplier, input);
        return this;
    }

    public Object[] getInvalidInputs() {
        invalidInputs.clear();
        mapInputs.keySet().forEach(supplierKey -> {
            if (!supplierKey.get()) {
                invalidInputs.add(mapInputs.get(supplierKey));
            }
        });
        final String msg = invalidInputs.stream()
                .map((codeStr) -> messageSource.getMessage(
                        codeStr,
                        null,
                        Locale.getDefault()))
                .collect(Collectors.joining(DELIMITER));
        return msg.isEmpty() ? DEFAULT_OBJECTS : Collections.singletonList(msg).toArray();
    }

}
