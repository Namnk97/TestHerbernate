package demo.crud1.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import demo.crud1.Dao.UserDao;
import demo.crud1.model.User;

@WebServlet({ "/UserServlet", "/UserServlet/insert", "/UserServlet/update", "/UserServlet/delete",
		"/UserServlet/edit","/UserServlet/reset"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao qlDao;
	private User us;

	public UserServlet() {
		this.qlDao = new UserDao();
		this.us = new User();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("delete")) {
			this.delete(request, response);
		} else if (url.contains("edit")) {
			this.edit(request, response);
		} else if (url.contains("reset")) {
			this.us = new User();
			request.setAttribute("user", us);
		}
		this.findAll(request, response);
		request.getRequestDispatcher("/Form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("insert")) {
			this.create(request, response);
		} else if (url.contains("update")) {
			this.update(request, response);
		}
		this.findAll(request, response);
		request.getRequestDispatcher("/Form.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BeanUtils.populate(us, request.getParameterMap());
			this.qlDao.insert(us);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			us = this.qlDao.findByID(id);
			BeanUtils.populate(us, request.getParameterMap());
			this.qlDao.update(us);
			request.setAttribute("user", us);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			us.setId(id);
			this.qlDao.delete(us);
			request.setAttribute("user", us);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<User> listUser = qlDao.getAll();
			request.setAttribute("listUser", listUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			us = this.qlDao.findByID(id);
			request.setAttribute("user", us);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
