package pages;

import helpers.Hash;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.PcUser;
import controller.UserController;

public class ServerLoginServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Something's happening in login");
		response.setContentType("application/json;charset=UTF-8");

		JsonObject json;
		String userLogin = request.getParameter("user");
		String pass = request.getParameter("password");

		PcUser tempUser= new UserController().getUserByLogin(userLogin);
		if(tempUser==null){
			System.out.println("PopComUser unknown");
			json = Json.createObjectBuilder()
					.add("accepted", false)
					.build();
		}else if(!Hash.compare(pass, tempUser.getUser().getPassword())){
			System.out.println("Pass not OK");
			json = Json.createObjectBuilder()
					.add("accepted", false)
					.build();
		}else{
			System.out.println("Pass OK");
			json = Json.createObjectBuilder()
					.add("accepted", true)
					.build();
		}

//		PrintWriter out = response.getWriter();
//		out.print(json);
//		out.flush();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
	
}