package noticeBoard;
import java.io.IOException; 
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//import com.learning.hello.contoller.MangathaGameController;
//import com.learning.hello.contoller.MangathaGameController.Deck;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addNotice")
public class NoticeBoardServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    private JakartaServletWebApplication application;
    private TemplateEngine templateEngine;
	private Notice noticeBoard;

    @Override
    public void init() throws ServletException {
        super.init();
        noticeBoard = new Notice(); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String contactName = req.getParameter("contactName");
        String contactPhone = req.getParameter("contactPhone");

        Notice notice = new Notice(title, content, contactName, contactPhone);

        noticeBoard.addNotice(notice);
        
        final IWebExchange webExchange = this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    //ctx.setVariable("reading", oc.getReading());
	    templateEngine.process("noticeBoard", ctx, resp.getWriter());
    }
}
