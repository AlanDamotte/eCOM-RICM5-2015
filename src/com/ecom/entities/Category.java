package com.ecom.entities;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;;

@Entity
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CATEGORY_ID")
    private Long id;
    private String support;
    private String shape;
    private Dimension dimension;
    @ManyToMany
    @JoinTable(name="CATEGORY_PRODUCTS",
		    joinColumns=@JoinColumn(name="CI_CATEGORY_ID",referencedColumnName="CATEGORY_ID"),
		    inverseJoinColumns=@JoinColumn(name="CI_PRODUCT_ID",referencedColumnName="PRODUCT_ID"))
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
    
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension){
        this.dimension = dimension;
    }
}
