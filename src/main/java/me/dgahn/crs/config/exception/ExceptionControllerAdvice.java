package me.dgahn.crs.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(value = RequestErrorException.class)
  public ModelAndView handleAccountDuplicatedException(RequestErrorException e) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("e", e);
    mav.setViewName("error");
    return mav;
  }

}
