package com.inventoryapi.inventory;

import org.bukkit.inventory.ItemStack;

public class PagedItem {
	
	public int location;
	public ItemStack item;
	
	public PagedItem(int location, ItemStack item) {
		this.location = location;
		this.item = item;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public ItemStack getItem() {
		return this.item;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public void setItem(ItemStack item) {
		this.item = item;
	}

}
