package com.simbircite.demo.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simbircite.demo.model.BudgetState;
import com.simbircite.demo.model.Moment;
import com.simbircite.demo.model.Pay;
import com.simbircite.demo.model.Debt;
import com.simbircite.demo.service.BudgetService;
import com.simbircite.demo.service.BudgetStateService;
import com.simbircite.demo.service.DebtStateService;
import com.simbircite.demo.service.EntityListService;
import com.simbircite.demo.service.EntityService;
import com.simbircite.demo.service.PayService;
import com.simbircite.demo.service.DebtService;
import com.simbircite.demo.util.DateUtil;
import com.simbircite.demo.util.spring.CustomDateTimeEditor;

@Controller
@RequestMapping("/report")
public class ReportsController {

    @Autowired
    private PayService payService;

    @Autowired
    private DebtService debtService;

    private Moment moment;

    private static final String PAY = "pay";
    private static final String DEBT = "debt";
    private static final String BUDGET = "budget";
    private static final String ENTITY = "entity";
    
    public ReportsController() {
        moment = new Moment();
        moment.setMoment(new DateTime());
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String actions() {
        return "redirect:/report/pay";
    }
    
    @RequestMapping(value = "{report}", method = RequestMethod.GET)
    public String actions(@PathVariable("report") String report, Model model) {
        BudgetStateService budgetStateService = new BudgetStateService(
                payService.getAll(moment),
                debtService.getAll(moment));
        BudgetState budgetState = budgetStateService.get(moment.getMoment());
        model.addAttribute("period", moment);
        model.addAttribute("payCount", budgetState.getPayCount());
        model.addAttribute("debtCount", budgetState.getDebtCount());
        model.addAttribute("total", budgetState.getTotal());
        model.addAttribute("unpaid", budgetState.getUnpaid());
        model.addAttribute("balance", budgetState.getBalance());
        return report;
    }

    @RequestMapping(value = "{report}/moment", method = RequestMethod.POST)
    public String changeDate(@PathVariable("report") String report, @ModelAttribute Moment date) {
        moment = date;
        return "redirect:/report/" + report;
    }

    @RequestMapping(value = "{report}/add", method = RequestMethod.GET)
    public String showAdd(@PathVariable("report") String report, Model model) {
        model.addAttribute(ENTITY, getEntity(report));
        return report + "/add";
    }

    @RequestMapping(value = "{report}/update/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("report") String report,
            @PathVariable("id") int id, Model model) {
        model.addAttribute(ENTITY, getService(report).get(id));
        return report + "/update";
    }

    @RequestMapping(value = "pay/update", method = RequestMethod.POST)
    public String updatePay(@ModelAttribute(ENTITY) Pay entity) {
        payService.update(entity);
        return "redirect:/report/pay";
    }
    
    @RequestMapping(value = "debt/update", method = RequestMethod.POST)
    public String updateDebt(@ModelAttribute(ENTITY) Debt entity) {
        debtService.update(entity);
        return "redirect:/report/debt";
    }

    @RequestMapping(value = "{report}/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("report") String report,
            @PathVariable("id") int id, Model model) {
        getService(report).delete(id);
        return "redirect:/report/" + report;
    }

    @RequestMapping(value = "{report}/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getAll(@PathVariable("report") String report) {
        return getListService(report).getAll(moment);
    }
    
    private Object getEntity(String report) {
        if (PAY.equals(report)) {
            return new Pay();
        }
        if (DEBT.equals(report)) {
            return new Debt();
        }
        return null;
    }

    private EntityService getService(String report) {
        if (PAY.equals(report)) {
            return payService;
        }
        if (DEBT.equals(report)) {
            return debtService;
        }
        return null;
    }

    private EntityListService getListService(String report) {
        if (PAY.equals(report)) {
            return payService;
        }
        if (DEBT.equals(report)) {
            return new DebtStateService(debtService);
        }
        if (BUDGET.equals(report)) {
            return new BudgetService(payService, debtService);
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DateUtil
                .getDateFormat());
        binder.registerCustomEditor(DateTime.class, new CustomDateTimeEditor(
                formatter));
    }
}
