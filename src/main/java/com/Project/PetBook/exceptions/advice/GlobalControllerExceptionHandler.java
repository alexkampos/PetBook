package com.Project.PetBook.exceptions.advice;

import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleConflict(HttpServletRequest req, DataIntegrityViolationException ex) {
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("timestamp", new java.util.Date());
        mav.addObject("message", ex.getMessage());
        mav.addObject("status", HttpStatus.NOT_FOUND);
        mav.addObject("path", req.getRequestURL());
        mav.addObject("trace", ex.toString());
        mav.addObject("exception", ex);
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleException(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("timestamp", new java.util.Date());
        mav.addObject("message", ex.getMessage());
        mav.addObject("error", ex.getErrorCode());
        mav.addObject("status", HttpStatus.BAD_REQUEST);
        mav.addObject("path", req.getRequestURL());
        mav.addObject("trace", ex.toString());
        mav.addObject("exception", ex);
        mav.setViewName("error");
        return mav;
    }
}
