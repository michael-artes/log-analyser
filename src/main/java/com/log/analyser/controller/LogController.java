package com.log.analyser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.log.analyser.dto.ListResultDTO;
import com.log.analyser.model.IpsBloquedsModel;
import com.log.analyser.service.LogAnalyserService;

@Controller
public class LogController {
	
	@Autowired
	private LogAnalyserService logAnalyserService;

    @RequestMapping(method=RequestMethod.GET, value="home")
    public String loginMessage(){
        return "home";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="ips-bloqued")
    public ModelAndView ipsBloqueds(){
    	
    	ModelAndView view = new ModelAndView("ipsbloqueds");
    	List<IpsBloquedsModel> findAllIpsBloqueds = logAnalyserService.findAllIpsBloqueds();
    	
    	view.addObject("list", findAllIpsBloqueds);
    	
        return view;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="search")
    public ModelAndView search(String startDate, String duration, int threshold, RedirectAttributes attributes) {
    	
    	ModelAndView view = new ModelAndView("redirect:/home");
    	
    	StringBuilder sbValidate = logAnalyserService.validateParam(startDate, duration, threshold);
    	
    	if (!sbValidate.toString().isEmpty()) {
    		view.addObject("errors", sbValidate.toString());
    		return view;
    	}
    	
    	List<ListResultDTO> ipsByThreshold = logAnalyserService.getIpsByThreshold(startDate, duration, threshold);
		attributes.addFlashAttribute("listIps", ipsByThreshold);
		
		if (ipsByThreshold.isEmpty()) {
			attributes.addFlashAttribute("msgInfo", "No ip found!");
		}
    	
    	return view;
    }
	
    
    
}
