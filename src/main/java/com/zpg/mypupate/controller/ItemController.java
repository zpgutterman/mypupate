package com.zpg.mypupate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zpg.mypupate.model.Item;
import com.zpg.mypupate.repository.ItemRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@GetMapping("/items/name")
	public List<Item> getAllItemsByName() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		return itemRepository.findAll(sort);
	}

	@PostMapping("/items")
	public Item createItem(@Valid @RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	@PutMapping("/items")
	public ResponseEntity<Item> updatePutItem(@Valid @RequestBody Item item) {
		Item foundItem = itemRepository.findOne(item.getId());
		if (foundItem == null) {
			return ResponseEntity.notFound().build();
		}
		foundItem.setAmount(item.getAmount());
		foundItem.setAmountUnit(item.getAmountUnit());
		foundItem.setDescription(item.getDescription());
		foundItem.setName(item.getName());
		foundItem.setReference(item.getReference());
		foundItem.setSafe(item.getSafe());
		foundItem.setHits(item.getHits());
		foundItem.setType(item.getType());
		
		Item updatedItem = itemRepository.save(foundItem);
		return ResponseEntity.ok(updatedItem);
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemId) {
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(item);
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long itemId,
			@Valid @RequestBody Item itemDetails) {
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		item.setAmount(itemDetails.getAmount());
		item.setAmountUnit(itemDetails.getAmountUnit());
		item.setDescription(itemDetails.getDescription());
		item.setName(itemDetails.getName());
		item.setReference(itemDetails.getReference());
		item.setSafe(itemDetails.getSafe());
		item.setHits(itemDetails.getHits());
		item.setType(itemDetails.getType());
		
		Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<Item> deleteItem(@PathVariable(value = "id") Long itemId) {
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		
		itemRepository.delete(item);
		return ResponseEntity.ok().body(item);
	}
	

}
