package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;
import ru.levelp.myapp.model.Supplier;

@Controller
public class AddPartController {
    @Autowired
    private PartsDAO dao;

    @Autowired
    private AddPartBean addPartBean;

    @RequestMapping(path = "/add-part", method = RequestMethod.POST)
    public String postAddPart(@RequestParam String partId,
                              @RequestParam String title,
                              @RequestParam int supplierId,
                              ModelMap model) {
        Part part;
        dao.getEm().getTransaction().begin();
        try {
            Supplier supplier = addPartBean.findSupplier(supplierId);

            part = dao.createPart(partId, title, supplier);
            dao.getEm().getTransaction().commit();
        } catch (Throwable t) {
            dao.getEm().getTransaction().rollback();
            t.printStackTrace();

//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add part: " + t.getMessage());

            return "error";
        }

        AddPartCompleteBean bean = new AddPartCompleteBean(part.getPartId(),
                part.getTitle(), part.getSupplier().getName());

        model.addAttribute("bean", bean);

        return "addPartComplete";
    }
}
