package com.zpg.mypupate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.zpg.mypupate.model.Item;
import com.zpg.mypupate.model.ItemDetail;
import com.zpg.mypupate.model.View;
import com.zpg.mypupate.repository.ItemDetailRepository;
import com.zpg.mypupate.repository.ItemRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemDetailRepository itemDetailRepository;
	@JsonView(View.ItemWithDetails.class)
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@GetMapping("/itemDetails")
	@Transactional
	public List<Item> getAllItemDetails() {
		return itemRepository.findAll();
	}
	@JsonView(View.Item.class)
	@GetMapping("/items/name")
	public List<Item> getAllItemsByName() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return itemRepository.findAll(sort);
	}
	@JsonView(View.ItemWithDetails.class)
	@PostMapping("/items")
	@Transactional
	public Item createItem(@Valid @RequestBody Item item) {
		return itemRepository.save(item);
	}
	@JsonView(View.ItemWithDetails.class)
	@PutMapping("/items")
	@Transactional
	public ResponseEntity<Item> updatePutItem(@Valid @RequestBody Item item) {
		Item foundItem = itemRepository.findOne(item.getId());
		if (foundItem == null) {
			return ResponseEntity.notFound().build();
		}
		ItemDetail foundItemDetail = foundItem.getItemDetail();
		
		foundItemDetail.setAmount(item.getItemDetail().getAmount());
		foundItemDetail.setAmountUnit(item.getItemDetail().getAmountUnit());
		foundItemDetail.setDescription(item.getItemDetail().getDescription());
		foundItem.setName(item.getName());
		foundItemDetail.setReference(item.getItemDetail().getReference());
		foundItemDetail.setSafe(item.getItemDetail().getSafe());
		foundItemDetail.setHits(item.getItemDetail().getHits());
		foundItemDetail.setType(item.getItemDetail().getType());
		foundItem.setItemDetail(foundItemDetail);
		
		Item updatedItem = itemRepository.save(foundItem);
		return ResponseEntity.ok(updatedItem);
	}
	@JsonView(View.ItemWithDetails.class)
	@GetMapping("/items/{id}")
	@Transactional
	public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemId) {
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		item.getItemDetail().setHits(item.getItemDetail().getHits() + 1);
		return ResponseEntity.ok().body(item);
	}
	@JsonView(View.ItemWithDetails.class)
	@PutMapping("/items/{id}")
	@Transactional
	public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long itemId,
			@Valid @RequestBody Item item) {
		Item foundItem = itemRepository.findOne(itemId);
		if (foundItem == null) {
			return ResponseEntity.notFound().build();
		}
		ItemDetail foundItemDetail = foundItem.getItemDetail();
		
		foundItemDetail.setAmount(item.getItemDetail().getAmount());
		foundItemDetail.setAmountUnit(item.getItemDetail().getAmountUnit());
		foundItemDetail.setDescription(item.getItemDetail().getDescription());
		foundItem.setName(item.getName());
		foundItemDetail.setReference(item.getItemDetail().getReference());
		foundItemDetail.setSafe(item.getItemDetail().getSafe());
		foundItemDetail.setHits(item.getItemDetail().getHits());
		foundItemDetail.setType(item.getItemDetail().getType());
		foundItem.setItemDetail(foundItemDetail);
		
		Item updatedItem = itemRepository.save(foundItem);
		return ResponseEntity.ok(updatedItem);
	}
	@JsonView(View.ItemWithDetails.class)
	@DeleteMapping("/items/{id}")
	@Transactional
	public ResponseEntity<Item> deleteItem(@PathVariable(value = "id") Long itemId) {
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		
		itemRepository.delete(item);
		return ResponseEntity.ok().body(item);
	}
	

}
