package com.apptech.first.client.ui;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class UIUtil {
	
	private static DateTimeFormat SHORT_DATE_FMT = DateTimeFormat.getFormat("dd MMM yyyy");
	private static NumberFormat CURRENCY_FMT = NumberFormat.getFormat("$#,##0.00;($#,##0.00)");
	
	
	public static String shortDateFmt(Date date) {
		if(date == null){
			return "";
		}
		else{
			return SHORT_DATE_FMT.format(date);
		}
	}
	
	public static String currencyFmt(Number number) {
		if(number == null){
			return "";
		}
		else{
			return CURRENCY_FMT.format(number);
		}
	}
	
	public static Number currencyFmtToNumber(String currency) {
		if(currency.equals("")){
			return 0;
		}
		else{
			return CURRENCY_FMT.parse(currency);
					
		}
	}
	
}
