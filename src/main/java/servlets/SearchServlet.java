package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SearchService;

import java.sql.SQLException;
import java.util.logging.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final SearchService searchService = new SearchService();

    private static final Logger logger = Logger.getLogger(SearchServlet.class.toString());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/search.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String search = req.getParameter("search");
            String filter = req.getParameter("filter");
            RequestDispatcher requestDispatcher;
            List<String> found = null;
            switch (filter) {

                case "Лекарства":
                    found = searchService.searchByMedicament(search);
                    break;
                case "Доктора":
                    found = searchService.searchByDoctor(search);
                    break;
                case "Записи":
                    found = searchService.searchByNote(search);
                    break;
            }
            assert found != null;
            if (found.isEmpty()) {
                req.getSession().setAttribute("search", "ЗАПРОС НЕ ВЕРНУЛ НИ ОДНОГО ЗНАЧЕНИЯ!");
            } else {
                req.getSession().setAttribute("search", found);
            }
        requestDispatcher = req.getRequestDispatcher("WEB-INF/search.jsp");
        requestDispatcher.forward(req, resp);
        found.clear();
    }
}
