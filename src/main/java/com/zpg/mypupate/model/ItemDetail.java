package com.zpg.mypupate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "itemDetails")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class ItemDetail implements Serializable{

	private static final long serialVersionUID = -5069369974742627258L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@JsonView(View.ItemWithDetails.class)
		private Long id;
	
	    @NotBlank
	    @JsonView(View.ItemWithDetails.class)
	    private String type;
	    
	    @NotBlank
	    @JsonView(View.ItemWithDetails.class)
	    private String safe;
	    
	    @JsonView(View.ItemWithDetails.class)
	    private Long amount;
	    
	    @JsonView(View.ItemWithDetails.class)
	    private String amountUnit;
	    
	    @JsonView(View.ItemWithDetails.class)
	    private String description;
	    
	    @JsonView(View.ItemWithDetails.class)
	    private String reference;
	    
	    @JsonView(View.ItemWithDetails.class)
	    private int hits;
	    
	    @JsonView(View.ItemWithDetails.class)
	    @Column(nullable = false, updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date createdAt;

	    @JsonView(View.ItemWithDetails.class)
	    @Column(nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @LastModifiedDate
	    private Date updatedAt;
	    
	    public Long getAmount() {
			return amount;
		}

		public void setAmount(Long amount) {
			this.amount = amount;
		}

		public String getAmountUnit() {
			return amountUnit;
		}

		public void setAmountUnit(String amountUnit) {
			this.amountUnit = amountUnit;
		}

		public int getHits() {
			return hits;
		}

		public void setHits(int hits) {
			this.hits = hits;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getReference() {
			return reference;
		}

		public void setReference(String reference) {
			this.reference = reference;
		}
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSafe() {
			return safe;
		}

		public void setSafe(String safe) {
			this.safe = safe;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
}
