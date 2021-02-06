package com.boot.hellokebbi.pattern.cor;

/**
 * @PackageName: com.boot.hellokebbi.pattern.cor
 * @ClassName: GoldMember
 * @Description: This is GoldMember class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-20 17:41
 */
public class GoldMember extends DiscountHandler {
    private int claim = 2000;
    private float discount = 0.8f;

    public GoldMember(DiscountHandler next) {
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
