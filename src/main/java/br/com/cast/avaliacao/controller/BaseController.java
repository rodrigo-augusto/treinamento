package br.com.cast.avaliacao.controller;

import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.cast.avaliacao.service.business.BusinessException;
import br.com.cast.avaliacao.util.IMsg;

public class BaseController implements MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    <T> ResponseEntity processNoContent(final T element) {
        return element != null ? ResponseEntity.ok(element) : ResponseEntity.noContent().build();
    }

    <T> ResponseEntity processBusinessWithReturn(final Supplier<T> supplier) {
        return processBusiness(supplier, Boolean.TRUE);
    }

    <T> ResponseEntity processBusinessWithoutReturn(final Supplier<T> supplier) {
        return processBusiness(supplier, Boolean.FALSE);
    }

    ResponseEntity processBusinessVoid(final Runnable runnable) {
        try {
            runnable.run();
            return ResponseEntity.ok().build();
        } catch (final BusinessException ex) {
            return processBusinessException(ex);
        } catch (final Exception ex) {
            return getResponseGenericError(ex);
        }
    }

    private <T> ResponseEntity processBusiness(final Supplier<T> supplier, final boolean isReturnElement) {
        try {
            final T ret = supplier.get();
            return isReturnElement ? ResponseEntity.ok(ret) : ResponseEntity.ok().build();
        } catch (final BusinessException ex) {
            return processBusinessException(ex);
        } catch (final Exception ex) {
            return getResponseGenericError(ex);
        }
    }

    private ResponseEntity processBusinessException(final BusinessException exception) {
        final String msg = messageSource.getMessage(
                processGetMessage(exception), exception.getArgs(), null, Locale.getDefault());
        return msg != null ? ResponseEntity.status(HttpStatus.CONFLICT).body(msg) : getResponseGenericError(exception);
    }

    private String processGetMessage(Throwable exception) {
        return Optional.ofNullable(exception.getCause()).map(Throwable::getMessage).orElse(exception.getMessage());
    }

    private ResponseEntity getResponseGenericError(final Throwable exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(messageSource.getMessage(
                        IMsg.MSG_0001,
                        Collections.singletonList(exception.getMessage()).toArray(),
                        Locale.getDefault()));
    }
}
