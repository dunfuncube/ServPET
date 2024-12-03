package com.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // ch2-p65  ch3-77  ch8-p139 
public class MyController {
	
	@GetMapping("/") // ch2-p65  ch3-77  ch8-p139
	public String myMethod1() {
		return "index1";  // --> 網頁位址：src\main\resources\templates\index1.html  // --> p137(p265)
	}

	@GetMapping("/index2") // ch2-p65  ch3-77  ch8-p139
	public String myMethod2() {
		return "index2";  // --> 網頁位址：src\main\resources\templates\index2.html  // --> p137(p265)
	}
	
	@GetMapping("/index3") // ch2-p65  ch3-77  ch8-p139
	@ResponseBody
	public String myMethod3() {
		return "<font color=red><b>.....index3.....<b></font>";
	}
	
	
	
}