package ru.levelp.myapp.web;

import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/add-part")
public class AddPartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String partId = req.getParameter("partId");
        String title = req.getParameter("title");
        String supplierId = req.getParameter("supplier");

        PartsDAO dao = (PartsDAO) getServletContext().getAttribute("partsDAO");
        AddPartBean addPartBean = (AddPartBean) getServletContext().getAttribute("addPartBean");

        Part part;
        dao.getEm().getTransaction().begin();
        try {
            Supplier supplier = addPartBean.findSupplier(Integer.parseInt(supplierId));

            part = dao.createPart(partId, title, supplier);
            dao.getEm().getTransaction().commit();
        } catch (Throwable t) {
            dao.getEm().getTransaction().rollback();
            t.printStackTrace();

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add part: " + t.getMessage());
            return;
        }

        AddPartCompleteBean bean = new AddPartCompleteBean(part.getPartId(),
                part.getTitle(), part.getSupplier().getName());

        req.setAttribute("bean", bean);
        req.getRequestDispatcher("/addPartComplete.jsp").forward(req, resp);
    }
}
