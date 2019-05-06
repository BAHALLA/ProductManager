package com.sid.web;

import com.sid.dao.ProduitRespository;
import com.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitRespository produitRespository;

    @RequestMapping(value = "/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "5") int s,
                        @RequestParam(name = "mc", defaultValue = "") String mc) {
        Page<Produit> produits = produitRespository.chercher("%" + mc + "%", new PageRequest(p, s));

        model.addAttribute("listProduits", produits.getContent());
        int[] pages = new int[produits.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("size", s);
        model.addAttribute("currentPage", p);
        model.addAttribute("mc", mc);
        return "produits";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Long id, String mc, int page, int size) {
        produitRespository.deleteById(id);
        return "redirect:/index?page=" + page + "&size=" + size + "&mc=" + mc;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formulaire(Model model) {
        model.addAttribute("produit", new Produit());
        return "addProduit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String ajouter(Model model, @Valid Produit produit, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "addProduit";
        produitRespository.save(produit);
        return "confirmation";
    }
}
