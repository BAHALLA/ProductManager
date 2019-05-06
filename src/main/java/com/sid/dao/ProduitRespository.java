package com.sid.dao;

import com.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProduitRespository extends JpaRepository<Produit,Long> {

   @Query("select p from Produit p where p.description like :x")
    Page<Produit> chercher(@Param("x") String mc, Pageable pageable);
}
