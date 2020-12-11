package com.stepanova.travelWithUs.servlet.page;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stepanova.travelWithUs.model.CurrentAccount;
import com.stepanova.travelWithUs.model.SocialAccount;
import com.stepanova.travelWithUs.servlet.AbstractController;
import com.stepanova.travelWithUs.util.RoutingUtils;
import com.stepanova.travelWithUs.util.SessionUtils;
@WebServlet("/from-social")
public class FromSocialController extends AbstractController {
	private static final long serialVersionUID = -8146770694377066438L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		if (code != null) {
			SocialAccount socialAccount = getSocialService().getSocialAccount(code);
			CurrentAccount currentAccount = getOrdersService().authentificate(socialAccount);
			SessionUtils.setCurrentAccount(req, currentAccount);
			RoutingUtils.redirect("/my-orders", req, resp);
		} else {
			LOGGER.warn("Parameter code not found");
			RoutingUtils.redirect("/sign-in", req, resp);
		}
	}
}
