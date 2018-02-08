package com.zpg.mypupate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zpg.mypupate.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
