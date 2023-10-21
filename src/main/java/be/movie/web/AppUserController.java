package be.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import be.movie.domain.AppUser;
import be.movie.domain.AppUserRepository;
import jakarta.validation.Valid;

@Controller
public class AppUserController {
	
	@Autowired
	private AppUserRepository urepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("adduser")
	@PreAuthorize("hasAuthority('ADMIN')")
		public String addUser(Model model) {
		model.addAttribute("appuser", new AppUser());
		return "adduser";
	}
	@PostMapping("saveuser")
		public String saveUser(@Valid AppUser appuser, BindingResult result, Model model) {
		if (urepository.findByUsername(appuser.getUsername()) != null) {
		  result.rejectValue("username", "error.appuser", "Username already in use!");
		    }
		
		if(result.hasErrors()) {
		model.addAttribute("appuser", appuser);
	    model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "appuser", result);
		return "adduser";
		}
		appuser.setPasswordHash(bCryptPasswordEncoder.encode(appuser.getPasswordHash()));
		urepository.save(appuser);
		model.addAttribute("successMessage", "New user has been added!");
	    model.addAttribute("appuser", new AppUser());
	    return "adduser";
	}
}
