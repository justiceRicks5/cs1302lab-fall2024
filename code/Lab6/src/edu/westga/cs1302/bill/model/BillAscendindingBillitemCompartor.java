package edu.westga.cs1302.bill.model;

import java.util.Comparator;

/**
 * sorter for bill items
 * 
 * @author justice ricks
 * @version fall 2024
 * 
 */
public class BillAscendindingBillitemCompartor implements Comparator<BillItem> {

	@Override
	public int compare(BillItem item1, BillItem item2) {
			if (item1.getAmount() < item2.getAmount()) {
				return -1;
			} else if (item1.getAmount() > item2.getAmount()) {
				return 1;
			} else {
				return 0;
			}
		}

	@Override
	public String toString() {
		return "Ascending";
	}
}