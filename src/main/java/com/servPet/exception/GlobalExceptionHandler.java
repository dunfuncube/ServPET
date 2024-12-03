package com.servPet.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.servPet.meb.model.MemberNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFound(MemberNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "front_end/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", "系統發生錯誤，請稍後再試");
        return "front_end/error";
    }
}
