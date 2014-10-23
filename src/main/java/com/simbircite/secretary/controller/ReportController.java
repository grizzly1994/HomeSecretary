package com.simbircite.secretary.controller;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simbircite.secretary.model.BudgetState;
import com.simbircite.secretary.model.Debt;
import com.simbircite.secretary.model.Pay;
import com.simbircite.secretary.model.Period;
import com.simbircite.secretary.model.User;
import com.simbircite.secretary.service.BudgetStateService;
import com.simbircite.secretary.service.DebtService;
import com.simbircite.secretary.service.PayService;
import com.simbircite.secretary.service.ReportsService;
import com.simbircite.secretary.util.DateUtil;
import com.simbircite.secretary.util.spring.DateTimeEditor;

@Controller
@RequestMapping("/report")
public class ReportController {

	private static final String MODEL = "model";

	@Autowired
	private BudgetStateService budgetStates;
	
	@Autowired
	private PayService pays;

	@Autowired
	private DebtService debts;

	@Autowired
	private ReportsService reports;
	
	private Period period;

	public ReportController() {
		period = new Period();
		period.setMoment(new DateTime());
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String show() {
		return "redirect:/report/pay";
	}

	@RequestMapping(value = "{report}/{presentation}", method = RequestMethod.GET)
	public String show(
			@PathVariable("report") String report,
			@PathVariable("presentation") String presentation,
			Model model,
			Authentication auth) {
		String selected = String.format("%s_%s_selected", report, presentation);
		model.addAttribute(selected, "selected");
		User user = (User) auth.getPrincipal();
		BudgetState budgetState = budgetStates.get(user, period.getMoment());
		model.addAttribute("state", budgetState);
		model.addAttribute("period", period);
		return report + "/" + presentation;
	}

	@RequestMapping(value = "{report}/moment", method = RequestMethod.POST)
	public String changeDate(
			@PathVariable("report") String report,
			@ModelAttribute("period") Period date) {
		period = date;
		return "redirect:/report/" + report;
	}

	private int updatable;

	@RequestMapping(value = "{report}/update", method = RequestMethod.GET)
	public String showAdd(
			@PathVariable("report") String report,
			Model model) {
		updatable = 0;
		model.addAttribute(MODEL, reports.getEntity(report));
		return report + "/add";
	}

	@RequestMapping(value = "{report}/update/{id}", method = RequestMethod.GET)
	public String showUpdate(
			@PathVariable("report") String report,
			@PathVariable("id") int id,
			Model model) {
		updatable = id;
		model.addAttribute(MODEL, reports.getService(report).get(updatable));
		return report + "/update";
	}

	@RequestMapping(value = "pay/update", method = RequestMethod.POST)
	public String updatePay(
			@Valid @ModelAttribute(MODEL) Pay entity,
			BindingResult result,
			Authentication auth) {
		if (result.hasErrors()) {
			return "pay/update";
		}
		entity.setId(updatable);
		entity.setUser((User) auth.getPrincipal());
		pays.update(entity);
		return "redirect:/report/pay";
	}

	@RequestMapping(value = "debt/update", method = RequestMethod.POST)
	public String updateDebt(
			@Valid @ModelAttribute(MODEL) Debt entity,
			BindingResult result,
			Authentication auth) {
		if (result.hasErrors()) {
			return "debt/update";
		}
		entity.setId(updatable);
		entity.setUser((User) auth.getPrincipal());
		debts.update(entity);
		return "redirect:/report/debt";
	}

	@RequestMapping(value = "{report}/delete/{id}", method = RequestMethod.GET)
	public String delete(
			@PathVariable("report") String report,
			@PathVariable("id") int id,
			Model model) {
		reports.getService(report).delete(id);
		return "redirect:/report/" + report;
	}

	@RequestMapping(value = "{report}/list", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Object list(
			@PathVariable("report") String report,
			Authentication auth) {
		User user = (User) auth.getPrincipal();
		return reports.getListService(report).getAll(user, period.getMoment());
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String format = DateUtil.getDateFormat();
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		DateTimeEditor editor = new DateTimeEditor(formatter);
		binder.registerCustomEditor(DateTime.class, editor);
	}
}
