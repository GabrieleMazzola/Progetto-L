/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;


public class SimpleTicket implements Products{
	double cost=0;
	String id;
	String name;
	double duration;
	public SimpleTicket(String name, String id, double cost, double duration) {
		this.name = name;
		this.id= id;
		this.cost = cost;
		this.duration = duration;
	}
	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return id;
	}
	@Override
	public double getDuration() {

		return duration;
	}
	

}
