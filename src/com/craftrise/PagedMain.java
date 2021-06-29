package com.craftrise;

import org.bukkit.plugin.java.JavaPlugin;

import com.craftrise.listener.InventoryListener;

public class PagedMain extends JavaPlugin {
	
	public static PagedMain instance;

	public void onEnable() {
		instance = this;
		registerListener();
		System.out.println("["+getDescription().getName()+"] Aktif!");
	}
	
	public void registerListener() {
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
	}
	
	public static PagedMain getInstance() {
		return instance;
	}
	
}
