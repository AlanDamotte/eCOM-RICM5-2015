package com.ecom.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Propriétés du bean */
	private Integer id;

	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	public void setId( Integer id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setProductId( Long productId ) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public int getQuantity(){
		return quantity;
	}
}
