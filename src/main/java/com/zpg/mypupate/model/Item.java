package com.zpg.mypupate.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
@Entity
@Table(name = "items")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Item implements Serializable{

	private static final long serialVersionUID = -9130899939834251702L;

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@JsonView(View.Item.class)
	    private Long id;

	    @NotBlank
	    @JsonView(View.Item.class)
	    private String name;


	    @OneToOne(cascade =  CascadeType.ALL, optional = false)
	    @JoinColumn(name = "item_detail_id")
	    @JsonView(View.ItemWithDetails.class)
	    private ItemDetail itemDetail;

	
		public ItemDetail getItemDetail() {
			return itemDetail;
		}

		public void setItemDetail(ItemDetail itemDetail) {
			this.itemDetail = itemDetail;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		


}
