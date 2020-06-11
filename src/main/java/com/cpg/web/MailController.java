package com.cpg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpg.metier.MailService;
@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/mail")
    String index(@RequestParam(value = "recipient",required=true) String recipient,@RequestParam(value ="message",required=true) String message) {
		try {
			 mailService.prepareAndSend(recipient, message);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        	e.getMessage();
        	System.out.println(e.getMessage());
        }
		
		//mailService.prepareAndSend("chadisassi@gmail.com", "this is test");
	//	System.out.println("ouais");
        return "redirect:/index";
    }
	
	
	@RequestMapping(value="/report",method=RequestMethod.GET)
	private String report () {
		
		return "report";
	}
	
}
