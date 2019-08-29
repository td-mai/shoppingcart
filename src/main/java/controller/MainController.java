package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dao.ProductDAO;
import entity.Product;
import model.PaginationResult;
import model.ProductInfo;

@Controller
@Transactional
@EnableWebMvc
public class MainController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}
	
	@RequestMapping("/productList")
	public String listProductHandler(Model model,//
			@RequestParam(value="name", defaultValue="") String likeName,//
			@RequestParam(value="page", defaultValue ="1") int page) {
		final int maxResult = 10;
		final int maxNavigationPage = 10;
		
		PaginationResult <ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
		model.addAttribute("paginationProducts", result);
		
		return "productList";
	}

}
