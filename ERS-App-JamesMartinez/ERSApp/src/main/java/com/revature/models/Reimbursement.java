package com.revature.models;

import java.sql.Blob;
import java.sql.Date;

public class Reimbursement {
	
	private int reimbursementId;
	private float amount;
	private Date timeSubmitted;
	private Date timeResolved;
	private String description;
	private Blob receipt;
	private int authorId;
	private String resolverIdAndName;
	private String reimbStatus;
	private String reimbType;
	
	public Reimbursement() {
		
	}
	
	public Reimbursement(int reimbursementId, float amount, Date timeSubmitted, Date timeResolved, String description,
			Blob receipt, int authorId, String resolverIdAndName, String reimbStatus, String reimbType) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverIdAndName = resolverIdAndName;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Date timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Date getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Date timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getResolverIdAndName() {
		return resolverIdAndName;
	}

	public void setResolverIdAndName(String resolverIdAndName) {
		this.resolverIdAndName = resolverIdAndName;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbursementId;
		result = prime * result + ((resolverIdAndName == null) ? 0 : resolverIdAndName.hashCode());
		result = prime * result + ((timeResolved == null) ? 0 : timeResolved.hashCode());
		result = prime * result + ((timeSubmitted == null) ? 0 : timeSubmitted.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (resolverIdAndName == null) {
			if (other.resolverIdAndName != null)
				return false;
		} else if (!resolverIdAndName.equals(other.resolverIdAndName))
			return false;
		if (timeResolved == null) {
			if (other.timeResolved != null)
				return false;
		} else if (!timeResolved.equals(other.timeResolved))
			return false;
		if (timeSubmitted == null) {
			if (other.timeSubmitted != null)
				return false;
		} else if (!timeSubmitted.equals(other.timeSubmitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amount=" + amount + ", timeSubmitted="
				+ timeSubmitted + ", timeResolved=" + timeResolved + ", description=" + description + ", receipt="
				+ receipt + ", authorId=" + authorId + ", resolverIdAndName=" + resolverIdAndName + ", reimbStatus="
				+ reimbStatus + ", reimbType=" + reimbType + "]";
	}

}
