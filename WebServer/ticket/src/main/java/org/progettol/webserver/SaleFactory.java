package org.progettol.webserver;

import java.util.ArrayList;
import java.util.Calendar;

import DateSingleton.DateOperations;
import items.Sale;
import singleton.JSONOperations;

/**
*
*La classe si occupa di costruire i biglietti di un certo utente
**/
public class SaleFactory {
	ArrayList<Sale> Sales;
	int position = 0;
	public SaleFactory(String JsonMyTickets) {
		System.out.println("Eseguito sales");
		Sales = new ArrayList();
		decodeJson(JsonMyTickets);
	}

	private void decodeJson(String JsonMyTickets) {
		Sales = JSONOperations.getInstance().decodeSales(JsonMyTickets);
		}
	
	public ArrayList<Sale> getSales(){
		return Sales;
	} 
	public int getNumberOfSales(){
		return Sales.size();
	}
	
	public boolean hasNext(){
		return  position < Sales.size();
	}
	public void nextSale(){
		position++;
	}
	public String getDescription(){
		return Sales.get(position).getProduct().getDescription();
	}
	
	//TODO sostituibile con pattern Mapper
	public String getBuyDay(){
		return (singleton.DateOperations.getInstance().getDay(Sales.get(position).getSaleDate())); 
	}
	public String getBuyMonth(){
		return (singleton.DateOperations.getInstance().getMonth(Sales.get(position).getSaleDate())); 
	}
	public String getBuyYear(){
		return (singleton.DateOperations.getInstance().getYear(Sales.get(position).getSaleDate())); 
	}
	public String getBuyHour(){
		return (singleton.DateOperations.getInstance().getHour(Sales.get(position).getSaleDate())); 
	}
	public String getBuyMinute(){
		return (singleton.DateOperations.getInstance().getMinute(Sales.get(position).getSaleDate())); 
	}
	public String getExpDay(){
		return (singleton.DateOperations.getInstance().getDay(Sales.get(position).getExpiryDate()));
	}
	public String getExpMonth(){
		return (singleton.DateOperations.getInstance().getMonth(Sales.get(position).getExpiryDate())); 
	}
	public String getExpYear(){
		return (singleton.DateOperations.getInstance().getYear(Sales.get(position).getExpiryDate())); 
	}
	public String getExpHour(){
		return (singleton.DateOperations.getInstance().getHour(Sales.get(position).getExpiryDate())); 
	}
	public String getExpMinute(){
		return (singleton.DateOperations.getInstance().getMinute(Sales.get(position).getExpiryDate())); 
	}
	public boolean isValid(){
		return Sales.get(position).getExpiryDate().after(Calendar.getInstance().getTime());
		
	}
	
	
}
