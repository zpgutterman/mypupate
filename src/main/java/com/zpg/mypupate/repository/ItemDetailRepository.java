package com.zpg.mypupate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zpg.mypupate.model.ItemDetail;

@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long>{

}
