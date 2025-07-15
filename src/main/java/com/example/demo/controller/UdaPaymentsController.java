package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.UdaPayment;
import com.example.demo.service.UdaPaymentsService;

@Controller
@RequestMapping("/uda-payments")
public class UdaPaymentsController {
	private final UdaPaymentsService service;

	public UdaPaymentsController(UdaPaymentsService service) {
		this.service = service;
	}

	/**
	 * 一覧画面
	 * @param model
	 * @return
	 */
	// URL：http://localhost:8080/uda-payments
	@GetMapping
	public String payments(Model model) {
		List<UdaPayment> list = service.findAll();
		int totalPrice = service.getTotalPrice();
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", totalPrice);
		return "payments/index";
	}

	/**
	 * 明細追加画面へ遷移
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public String add(Model model) {
		return "payments/add";
	}

	/**
	 * 完了画面
	 * @param model
	 * @return
	 */
	@GetMapping("/complete")
	public String complete(Model model) {
		return "payments/complete";
	}

	/**
	 * 追加画面
	 * @param model
	 * @param payment
	 * @return
	 */
	@PostMapping("/add")
	public String complete(Model model, UdaPayment payment) {
		service.insertUdaPayment(payment);
		model.addAttribute("udaPayment", payment);
		return "payments/addComplete";
	}
	
	/**
	 * 一覧画面（年月指定）
	 * @param model
	 * @return
	 */
	@PostMapping("/findByYearAndMonth")
	public String findByYearAndMonth(Model model, @RequestParam String year, @RequestParam String month) {
		List<UdaPayment> list2 = service.findUdaPayementsByYearAndMonth(year, month);
		int totalPrice = service.getTotalPrice();
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("list", list2);
		return "payments/findByYearAndMonth";
	}

	/**
	 * ID指定で取得
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/find-by-id")
	public String findUdaPaymentById(Model model, @RequestParam int id) {
		UdaPayment payment = service.findUdaPaymentById(id);
		model.addAttribute("udaPayment", payment);
		return "payments/delete";
	}

	/**
	 * 削除
	 * @param model
	 * @return
	 */
	@PostMapping("/delete")
	public String delete(Model model, @RequestParam int id) {
		service.deleteUdaPayment(id);
		return "payments/complete";
	}
}
