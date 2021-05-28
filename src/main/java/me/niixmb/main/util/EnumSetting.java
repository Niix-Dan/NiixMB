package me.niixmb.main.util;

import java.util.Objects;

/**
 *
 * @author SrNox_
 */
public final class EnumSetting<T extends Enum<T>> {
    private final T[] values;
    private T selected;
    private final T defaultSelected;
    public EnumSetting(String name, String description, T[] values, T selected) {
	this.values = Objects.requireNonNull(values);
	this.selected = Objects.requireNonNull(selected);
	defaultSelected = selected;
    }
    
    public T[] getValues()
	{
		return values;
	}
	
	public T getSelected()
	{
		return selected;
	}
	
	public T getDefaultSelected()
	{
		return defaultSelected;
	}
    
    
}
