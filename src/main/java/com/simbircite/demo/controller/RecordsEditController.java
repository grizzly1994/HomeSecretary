package com.simbircite.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/recordsEdit")
public class RecordsEditController {

    private static final String CONTROLLER_PATH = "recordsEdit/";

    @RequestMapping(value = "/actions", method = RequestMethod.GET)
    public String actions() {
        return CONTROLLER_PATH + "actionsEditor";
    }

    @RequestMapping(value = "/credits", method = RequestMethod.GET)
    public String credits() {
        return CONTROLLER_PATH + "creditsEditor";
    }

    @RequestMapping(value = "/debts", method = RequestMethod.GET)
    public String debts() {
        return CONTROLLER_PATH + "debtsEditor";
    }

    @RequestMapping(value = "/income", method = RequestMethod.GET)
    public String income() {
        return CONTROLLER_PATH + "incomeEditor";
    }

    @RequestMapping(value = "/accumulations", method = RequestMethod.GET)
    public String accumulations() {
        return CONTROLLER_PATH + "accumulationsEditor";
    }
}
