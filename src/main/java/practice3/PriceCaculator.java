package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private Order _order;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;

    public PriceCaculator(Order order){
        this._order = order;
        this.subTotal = new BigDecimal(0);
        this.tax = new BigDecimal(0);
        this.grandTotal = new BigDecimal(0);
    }

    public BigDecimal calculate() {
        calculateOrderLineItem();
        calculateSubtractDiscount();
        calculateTax();
        calculateGrand();

        return grandTotal;
    }

    private void calculateGrand() {
        grandTotal = subTotal.add(tax);
    }

    private void calculateTax() {
        tax = subTotal.multiply(this._order.getTax());
    }

    private void calculateSubtractDiscount() {
        for (BigDecimal discount : _order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void calculateOrderLineItem() {
        for (OrderLineItem lineItem : _order.getOrderLineItemList()) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }
}
