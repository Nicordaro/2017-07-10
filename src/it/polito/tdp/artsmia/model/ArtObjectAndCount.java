package it.polito.tdp.artsmia.model;

public class ArtObjectAndCount {
	private int artObjectID;
	private int count;

	public ArtObjectAndCount(int artObjectID, int count) {
		super();
		this.artObjectID = artObjectID;
		this.count = count;
	}

	public int getArtObjectID() {
		return artObjectID;
	}

	public void setArtObjectID(int artObjectID) {
		this.artObjectID = artObjectID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
