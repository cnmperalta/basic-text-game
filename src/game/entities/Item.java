package game.entities;

public class Item {
    private int itemID;
	private String itemName;
	private String itemType;
    private Room location;

    public Item() {}

    public Item(int itemID, String itemName, String itemType, Room location) {
        this.itemID = itemID;
		this.itemName = itemName;
		this.itemType = itemType;
        this.location = location;
    }
	
	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @return the location
	 */
	public Room getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Room location) {
		this.location = location;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
}
