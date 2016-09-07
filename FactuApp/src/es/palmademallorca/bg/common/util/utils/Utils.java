package es.palmademallorca.bg.common.util.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public final class Utils {
	
	
	public static BigDecimal round(double value, int numberOfDigitsAfterDecimalPoint) {
		BigDecimal bigDecimal = new BigDecimal(value);
		bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint, 
				BigDecimal.ROUND_HALF_UP);
		return bigDecimal;
	}
	
	/*private static BigDecimal round(BigDecimal num, int numDecimal) {
        System.out.println("BigDecimal: " + num.toString());

        // Use Locale.ENGLISH for '.' as decimal separator
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(numDecimal);
        nf.setRoundingMode(RoundingMode.HALF_UP);

        if(Math.abs(num.doubleValue()) - Math.abs(num.intValue()) != 0){
            nf.setMinimumFractionDigits(numDecimal);
        }

        System.out.println("Formatted: " + nf.format(num));
        return String.valueOf(nf.format(num));
    }*/
}
