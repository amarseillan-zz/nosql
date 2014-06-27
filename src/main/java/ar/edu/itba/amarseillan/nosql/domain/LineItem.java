package ar.edu.itba.amarseillan.nosql.domain;

import java.util.Date;

import org.jongo.marshall.jackson.oid.ObjectId;

public class LineItem {

	@ObjectId
	private String _id;
	
	private int lineNumber;
	private double quantity;
	private double extendedPrice;
	private double discount;
	private double tax;
	private String returnFlag;
	private String lineStatus;
	private Date shipDate;
	private Date commitDate;
	private Date receiptDate;
	private String shipInStruct;
	private String shipMode;
	private String comment;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getExtendedPrice() {
		return extendedPrice;
	}

	public void setExtendedPrice(double extendedPrice) {
		this.extendedPrice = extendedPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Date getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getShipInStruct() {
		return shipInStruct;
	}

	public void setShipInStruct(String shipInStruct) {
		this.shipInStruct = shipInStruct;
	}

	public String getShipMode() {
		return shipMode;
	}

	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "LineItem [_id=" + _id + ", lineNumber=" + lineNumber
				+ ", quantity=" + quantity + ", extendedPrice=" + extendedPrice
				+ ", discount=" + discount + ", tax=" + tax + ", returnFlag="
				+ returnFlag + ", lineStatus=" + lineStatus + ", shipDate="
				+ shipDate + ", commitDate=" + commitDate + ", receiptDate="
				+ receiptDate + ", shipInStruct=" + shipInStruct
				+ ", shipMode=" + shipMode + ", comment=" + comment + "]";
	}
	
	
	
}
