package com.boot.hellokebbi.pattern.cor;

/**
 * @PackageName: com.boot.hellokebbi.pattern.cor
 * @ClassName: DiscountHandler
 * @Description: This is DiscountHandler class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-20 17:36
 * @remark: 通過chain of responsibility實現不同會員等級打折制度，降低耦合度，增強靈活性
 */
public abstract class DiscountHandler {
    private DiscountHandler next = null;

    public DiscountHandler(DiscountHandler next) {
        this.next = next;
    }

    public DiscountHandler getNext() {
        return next;
    }

    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    abstract boolean hasHandler(int integral);

    public int help(int integral, int amount) {
        if (next != null) {
            return next.help(integral, amount);
        } else {
            throw new UnsupportedOperationException();
        }
    } //1
}
