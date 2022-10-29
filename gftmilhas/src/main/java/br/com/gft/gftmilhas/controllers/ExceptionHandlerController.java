package br.com.gft.gftmilhas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(Exception.class)
    @RequestMapping(value = "/erros")
    public ModelAndView tratadorDeException() {

        ModelAndView modelAndView = new ModelAndView();
        //
        modelAndView.setViewName("notFound.html");

        return modelAndView;
    }
}