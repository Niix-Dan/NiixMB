package me.niixmb.main;

public enum Category
{
	BLOCKS("Blocos"),
	MOVEMENT("Movimentacao"),
	COMBAT("Combate"),
	RENDER("Renderizacao"),
	CHAT("Chat"),
	FUN("Brincadeiras"),
	ITEMS("Items"),
	OTHER("Outros");
	
	private final String name;
	
	private Category(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
