package com.api.test.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.test.event.RegistrationCompleteEvent;
import com.api.test.model.User;
import com.api.test.service.UserService;
import com.api.test.token.VerificationToken;
import com.api.test.token.VerificationTokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

	private final UserService userService;
	private final ApplicationEventPublisher publisher;
	private final VerificationTokenRepository tokenRepository;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@GetMapping("/home")
	public String Home() {
		return "This is Home page";
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
		User user = userService.registerUser(registrationRequest);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success!  Please, check your email for to complete your registration";
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginrequest) {
		System.out.println(loginrequest);
		System.out.println(loginrequest.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginrequest.getEmail(), loginrequest.getPassword()));
          System.out.println(authentication.toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<String>("Login successfully", HttpStatus.OK);

	}
	

	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token) {
		VerificationToken theToken = tokenRepository.findByToken(token);
		if (theToken.getUser().isEnabled()) {
			return "This account has already been verified, please, login.";
		}
		String verificationResult = userService.validateToken(token);
		if (verificationResult.equalsIgnoreCase("valid")) {
			return "Email verified successfully. Now you can login to your account";
		}
		return "Invalid verification token";
	}

	@GetMapping("/users")
	public List<User> getUser() {
		return userService.getUsers();
	}

	public String applicationUrl(HttpServletRequest request) {

		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
