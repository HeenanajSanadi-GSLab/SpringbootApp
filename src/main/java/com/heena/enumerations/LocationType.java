package com.heena.enumerations;

public enum LocationType {
FACILITY("facility"),BUILDING("building"),FLOOR("floor"),DEPARTMENT("department"),ROOM("room"),BED("bed");
	

   
    
    private final String code;

    private LocationType(String code) {
        this.code = code;
    }

    public String getLocationType() {
        return code;
    }


}
