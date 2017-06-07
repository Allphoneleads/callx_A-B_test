package com.allphoneleads.api.proctor.domain;

public class ProvidedContext {
	
	private String offersSize;

    private String offersIdsAsc;
    
    

	public ProvidedContext(String offersSize, String offersIdsAsc) {
		this.offersSize = offersSize;
		this.offersIdsAsc = offersIdsAsc;
	}

	public String getOffersSize() {
		return offersSize;
	}

	public void setOffersSize(String offersSize) {
		this.offersSize = offersSize;
	}

	public String getOffersIdsAsc() {
		return offersIdsAsc;
	}

	public void setOffersIdsAsc(String offersIdsAsc) {
		this.offersIdsAsc = offersIdsAsc;
	}

   

}
