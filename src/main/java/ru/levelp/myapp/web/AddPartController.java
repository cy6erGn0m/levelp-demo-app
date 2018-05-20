package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;

import javax.servlet.http.HttpSession;

@Controller
public class AddPartController {
    @Autowired
    private PartsDAO dao;

    @Autowired
    private AddPartBean bean;

    @GetMapping(path = "/add-part")
    public String addPartPage(HttpSession session, ModelMap model) {
        if (ensureLoggedIn(session)) return "redirect:/";

        model.addAttribute("addPartBean", bean);

        return "addPart";
    }

    @RequestMapping(path = "/add-part", method = RequestMethod.POST)
    public String postAddPart(@RequestParam String partId,
                              @RequestParam String title,
                              @RequestParam("supplier") int supplierId,
                              ModelMap model,
                              HttpSession session) {

        if (ensureLoggedIn(session)) return "redirect:/";

        Part part;
        try {
            part = dao.createPart(partId, title, supplierId);
        } catch (Throwable t) {
            t.printStackTrace();

//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add part: " + t.getMessage());

            return "error";
        }

        AddPartCompleteBean bean = new AddPartCompleteBean(part.getPartId(),
                part.getTitle(), part.getSupplier().getName());

        model.addAttribute("bean", bean);

        return "addPartComplete";
    }

    private static boolean ensureLoggedIn(HttpSession session) {
        if (session.getAttribute("userName") == null) {
            return true;
        }
        return false;
    }
}
