package com.bittercode.model;

import java.io.Serializable;

public class search implements Serializable {
    private String barcode ; 
    private String name ; 
    private String author ;
    private String genre;
 
    publlic search(String barcode , String name , String author, String genre){
        this.barcode = barcode;
        this.name = name ;
        this.author = author;
       this.genre = genre;
    } 
    public search (){
        return super;
    }
    public String getBarcode(){
        return barcode;
    }
    public void setBarcode (String barcode){
        this.barcode = barcode;
    }
    public String getName (){
        return name;
    }
    public void setName(String name){
        this.name = name ;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getGenre (){
        return genre ;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    
}