package com.tourism;

import java.io.Serializable;

public class Place implements Serializable {//using serializable here for writing an object to file
    String placename;
    String locationname;
    String placespeciality;
    String landmark;
	public Place(String placename, String locationname, String placespeciality, String landmark) {
		super();
		this.placename = placename;
		this.locationname = locationname;
		this.placespeciality = placespeciality;
		this.landmark = landmark;
	}
	@Override
	public String toString() {
		return "Place [placename=" + placename + ", locationname=" + locationname + ", placespeciality="
				+ placespeciality + ", landmark=" + landmark + "]";
	}
    
}
