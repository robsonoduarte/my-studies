package br.com.mystudies.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.Priority;
import br.com.mystudies.domain.enun.SprintStatus;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;


@WebServlet("/sprint")
public class SprintServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       

	@EJB
	private SprintService sprintService;
	
	
    public SprintServlet() {
        super();
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendToSprintPage(
				request,
				response,
				sprintService.getCurrentSprint()
			);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = 
				request.getParameter("action");
		

		if(action != null){
			
			switch (action) {				
				case "NEWSPRINT":
					sendToSprintFormFragment(
							request,
							response,
							sprintService.containsSprintInRun()
						);
					break;
				case "CREATESPRINT":
					sprintService.create(getSprint(request, response));
					sendToSprintStoriesFragment(request, response);
					break;
			}	
		}
	}


	
	private Sprint getSprint(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			return new Sprint(
					sdf.parse(request.getParameter("startDate")),
					sdf.parse(request.getParameter("finalDate")), 
					SprintStatus.RUNNING
				);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
	}



	private void sendToSprintFormFragment(HttpServletRequest request,HttpServletResponse response, boolean containsSprintInRun) throws ServletException, IOException {
		request.setAttribute("containsSprintInRun", containsSprintInRun);
		request.getRequestDispatcher("pages/sprint/sprint-form-fragment.jsp").forward(request, response);				
	}
	
	
	private void sendToSprintStoriesFragment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("pages/sprint/sprint-stories-fragment.jsp").forward(request, response);				
	}

	
	private void sendToSprintPage(HttpServletRequest request,HttpServletResponse response, Sprint sprint) throws ServletException, IOException {
		request.setAttribute("includeStoriesFragment", true);
		request.setAttribute("sprint", sprint);
		request.getRequestDispatcher("pages/sprint/sprint.jsp").forward(request, response);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	private void temp(HttpServletRequest request, HttpServletResponse response) {
		request.getParameter("startDate");
		request.getParameter("finalDate");
		request.setAttribute("sprint", getSprint(request));
	}


	
	private Object getSprint(HttpServletRequest request) {
		
		Sprint sprint = new Sprint();
		
		sprint.setStories(new HashSet<Story>());
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		sprint.getStories().add(new Story("tesmte", Priority.HEIGHT, StoryStatus.IN_SPRINT, new Date()));
		
		return sprint;
	}

}