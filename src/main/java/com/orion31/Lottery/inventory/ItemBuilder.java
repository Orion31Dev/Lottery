package com.orion31.Lottery.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder {
    
    private Material type = null;
    private MaterialData data = null;
    private int quantity = 1;
    private String name = null;
    private List<String> lore = null;
    private boolean unbreakable = false;
    
    public ItemBuilder() {
	
    }
    
    public ItemBuilder(ItemStack itemStack) {
	type = itemStack.getType();
	data = itemStack.getData();
	quantity = itemStack.getAmount();
	name = itemStack.getItemMeta().getDisplayName();
	lore = itemStack.getItemMeta().getLore();
	unbreakable = itemStack.getItemMeta().isUnbreakable();
    }
    
    public ItemBuilder setName(String name) {
	this.name = name;
	return this;
    }
    
    public ItemBuilder setLore(String... lore) {
	this.lore = Arrays.asList(lore);
	return this;
    }
    
    public ItemBuilder setLore(List<String> lore) {
	this.lore = lore;
	return this;
    }
    
    public ItemBuilder setUnbreakable(boolean unbreakable) {
	this.unbreakable = unbreakable;
	return this;
    }
    
    public ItemBuilder setData(MaterialData data) {
	this.data = data;
	return this;
    }
    
    
    public ItemBuilder setItem(Material type, int quantity) {
	this.type = type;
	this.quantity = quantity;
	return this;
    }

    public ItemStack build() {
	ItemStack itemStack = new ItemStack(type, quantity);
	itemStack.setData(data);
	ItemMeta itemMeta = itemStack.getItemMeta();
	if (name != null) itemMeta.setDisplayName(name);
	if (lore != null) itemMeta.setLore(lore);
	itemMeta.setUnbreakable(unbreakable);
	itemStack.setItemMeta(itemMeta);
	return itemStack;
    }
 }
