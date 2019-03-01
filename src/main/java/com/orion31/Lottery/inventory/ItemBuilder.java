package com.orion31.Lottery.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    
    private Material type = null;
    private short durability = -1;
    private int quantity = 1;
    private String name = null;
    private List<String> lore = null;
    private boolean unbreakable = false;
    
    public ItemBuilder() {
    }
    
    public ItemBuilder(ItemStack itemStack) {
	type = itemStack.getType();
	durability = itemStack.getDurability();
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
    
    public ItemBuilder setDurability(short durability) {
	this.durability = durability;
	return this;
    }
    
    
    public ItemBuilder setItem(Material type, int quantity) {
	this.type = type;
	this.quantity = quantity;
	return this;
    }

    public ItemStack build() {
	ItemStack itemStack = new ItemStack(type, quantity);
	ItemMeta itemMeta = itemStack.getItemMeta();
	if (name != null) itemMeta.setDisplayName(name);
	if (lore != null) itemMeta.setLore(lore);
	itemMeta.setUnbreakable(unbreakable);
	itemStack.setItemMeta(itemMeta);
	if (durability != -1) itemStack.setDurability(durability);;
	return itemStack;
    }
 }
