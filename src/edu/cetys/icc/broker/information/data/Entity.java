package edu.cetys.icc.broker.information.data;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public abstract class Entity {

	private long creationTime;
	
	private UUID uuid;
	
	protected EntityType type;
	
	private long validity;
	
	public Entity() {
		Calendar calendar = new GregorianCalendar();
		this.creationTime = calendar.getTimeInMillis();
		this.uuid = UUID.randomUUID();
		this.validity = -1;
	}

	public long getCreationTime() {
		return this.creationTime;
	}
	
	public UUID getUUID() {
		return this.uuid;
	}
	
	public EntityType getType() {
		return this.type;
	}

	public long getValidity() {
		return this.validity;
	}
	
	public void setValidity(long validity) {
		this.validity = validity;
	}
}
