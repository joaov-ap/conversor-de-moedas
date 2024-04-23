public class CurrencyConverter {
    private String baseCode;
    private String targetCode;
    private double amount;
    private double conversionResult;

    public CurrencyConverter(String baseCode, String targetCode, double amount) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.amount = amount;
    }

    public CurrencyConverter(ConversionResult conversionResult) {
        this.conversionResult = Double.parseDouble(conversionResult.conversion_result());
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public double getAmount() {
        return amount;
    }

    public double getConversionResult() {
        return conversionResult;
    }
}
