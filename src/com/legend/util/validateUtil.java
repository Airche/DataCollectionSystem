package com.legend.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.legend.model.User;
import com.legend.model.security.Right;

public class validateUtil {

	public static boolean isValid(String src) {
		return !(src == null || src.trim().length() == 0);
	}

	public static boolean isValid(Collection col) {
		if (col == null || col.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean hasRight(String ns, String actionName, HttpServletRequest req) {
		if (!isValid(ns) || ns.equals("/")) {
			ns = "";
		}
		if (actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf('?'));
		}
		String url = ns + "/" + actionName;
		HttpSession session = req.getSession();
		Map<String, Right> map = (Map<String, Right>) session.getServletContext().getAttribute("all_rights_map");
		Right r = map.get(url);
		if (r == null || r.isCommon()) {
			return true;
		} else {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				return false;
			} else {
				if (user.isSuperAdmin()) {
					return true;
				} else {
					if (user.hashRight(r)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

}
