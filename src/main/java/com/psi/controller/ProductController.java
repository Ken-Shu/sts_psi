package com.psi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psi.entity.Product;
import com.psi.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository productrepository;
	
	// 部門維護首頁
	@GetMapping("/")
	public String index(@ModelAttribute Product product,  Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("products", productrepository.findAll());
		return "product";
	}
	
	@GetMapping("/{id}")
	public String get(@PathVariable("id") Long id, Model model) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("product", productrepository.getById(id));
		model.addAttribute("products", productrepository.findAll());
		return "product";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		productrepository.deleteById(id);
		return "redirect:../";
	}
	
	@PostMapping("/")
	public String add(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "POST");
			model.addAttribute("products", productrepository.findAll());
			return "product";
		}
		productrepository.save(product);
		return "redirect:./";
	}
	
	@PutMapping("/")
	public String update(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("_method", "PUT");
			model.addAttribute("departments", productrepository.findAll());
			return "department";
		}
		productrepository.save(product);
		return "redirect:./";
	}
	
}