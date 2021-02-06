package com.boot.hellokebbi.pattern.cor;

/**
 * @PackageName: com.boot.hellokebbi.pattern.cor
 * @ClassName: OrdinaryMember
 * @Description: This is OrdinaryMember class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-20 17:41
 */
public class OrdinaryMember extends DiscountHandler {
    private int claim = 0;
    private float discount = 1f;

    public OrdinaryMember(DiscountHandler next) {
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
