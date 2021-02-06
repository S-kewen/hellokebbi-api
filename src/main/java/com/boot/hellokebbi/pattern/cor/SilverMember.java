package com.boot.hellokebbi.pattern.cor;

/**
 * @PackageName: com.boot.hellokebbi.pattern.cor
 * @ClassName: SilverMember
 * @Description: This is SilverMember class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-20 17:41
 */
public class SilverMember extends DiscountHandler {
    private int claim = 1000;
    private float discount = 0.9f;

    public SilverMember(DiscountHandler next) {
        super(next);
    }

    @Override
    boolean hasHandler(int integral) {
        return integral >= claim;
    }

    @Override
    public int help(int integral, int amount) {
        if (hasHandler(integral)) {
            return (int) (amount * discount);
        } else {
            return super.help(integral, amount);
        }
    }
}
