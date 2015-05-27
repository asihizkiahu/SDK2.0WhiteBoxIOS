package com.service.validator;

/**
 * Created by asih on 14/05/2015.
 */
public interface Validator {

    <T> boolean validateElement(T element, String msg);
}
