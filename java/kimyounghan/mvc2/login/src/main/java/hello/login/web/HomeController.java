package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumenetresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final MemberRepository memberRepository;
	private final SessionManager sessionManager;

	// @GetMapping("/")
	public String home() {
		return "home";
	}

	//    @GetMapping("/")
	public String homeLogin(
		@CookieValue(name = "memberId", required = false) Long memberId,
		Model model) {
		if (memberId == null) {
			return "home";
		}
		//로그인
		Member loginMember = memberRepository.findById(memberId);
		if (loginMember == null) {
			return "home";
		}
		model.addAttribute("member", loginMember);
		return "loginHome";
	}

	//    @GetMapping("/")
	public String homeLoginV2(HttpServletRequest request, Model model) {
		Member session = (Member)sessionManager.getSession(request);
		if (session == null) {
			return "home";
		}

		model.addAttribute("member", session);
		return "loginHome";
	}

	//    @GetMapping("/")
	public String homeLoginV3(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "home";
		}
		Member attribute = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		model.addAttribute("member", attribute);
		return "loginHome";
	}

	// @GetMapping("/")
	public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
		Model model) {
		if (member == null) {
			return "home";
		}
		model.addAttribute("member", member);
		return "loginHome";
	}


	@GetMapping("/")
	public String homeLoginV4ArgumentResolver(@Login Member member,
		Model model) {
		if (member == null) {
			return "home";
		}
		model.addAttribute("member", member);
		return "loginHome";
	}
}