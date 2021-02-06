package com.boot.hellokebbi.pattern.cor;

/**
 * @PackageName: com.boot.hellokebbi.pattern.cor
 * @ClassName: DiamondMember
 * @Description: This is DiamondMember class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-20 17:41
 */
public class DiamondMember extends DiscountHandler {
    private int claim = 5000;
    //積分要求
    private float discount = 0.7f;

    //折扣
    public DiamondMember(DiscountHandler next) {
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
