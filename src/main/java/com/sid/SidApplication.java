package com.sid;

import com.sid.dao.ProduitRespository;
import com.sid.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SidApplication {

    public static void main(String[] args) {

       ApplicationContext context= SpringApplication.run(SidApplication.class, args);

        ProduitRespository produitRespository = context.getBean(ProduitRespository.class);

        produitRespository.save(new Produit("Dell XPS", 2470.0, 10));
        produitRespository.save(new Produit("HP 480", 1570.0, 14));
        produitRespository.save(new Produit("Axus 780", 1970.0, 16));
        produitRespository.save(new Produit("Lenovo", 1860.0, 18));


        produitRespository.findAll().forEach( p-> {
            System.out.println(p.getDescription() + " " + p.getPrix());
        });

    }

}
