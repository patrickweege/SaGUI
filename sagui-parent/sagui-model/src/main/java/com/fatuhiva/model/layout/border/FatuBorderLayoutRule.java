package com.fatuhiva.model.layout.border;

import com.fatuhiva.model.layout.IFatuLayoutRule;

public class FatuBorderLayoutRule implements IFatuLayoutRule<FatuBorderLayout> {
	
	public static final FatuBorderLayoutRule NORTH = new FatuBorderLayoutRule(FatuRegion.NORTH);
	public static final FatuBorderLayoutRule SOUTH = new FatuBorderLayoutRule(FatuRegion.SOUTH);
	public static final FatuBorderLayoutRule WEST = new FatuBorderLayoutRule(FatuRegion.WEST);
	public static final FatuBorderLayoutRule EAST = new FatuBorderLayoutRule(FatuRegion.EAST);
	public static final FatuBorderLayoutRule CENTER = new FatuBorderLayoutRule(FatuRegion.CENTER);
	
	private final FatuRegion region;
	private final boolean split;

	public FatuBorderLayoutRule(FatuRegion region, boolean split) {
		this.region = region;
		this.split = split;
	}

	public FatuBorderLayoutRule(FatuRegion region) {
		this(region, false);
	}
	
	public FatuRegion getRegion() {
		return region;
	}
	
	public boolean isSplit() {
		return split;
	}
			

}
