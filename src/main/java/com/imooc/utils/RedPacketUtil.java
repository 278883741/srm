package com.imooc.utils;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

/**
 * 抢红包帮助类
 */
public class RedPacketUtil {
    /**
     * 二倍均值法拆红包，金额以分为单位
     *
     * @param totalAmount
     * @param totalPeople
     * @return
     */
    public static List<Integer> divideRedPacket(final Integer totalAmount, final Integer totalPeople) {
        List<Integer> list = Lists.newLinkedList();
        if (totalAmount > 0 && totalPeople > 0) {
            // 剩余金额
            Integer restAmount = totalAmount;
            // 剩余人数
            Integer restPeople = totalPeople;

            Random random = new Random();
            int amount;
            // i是人，当剩一个人时候就不用算了，剩下的钱就是红包金额了
            for (int i = 1; i < totalPeople; i++) {
                // 左闭右开，所以-1，但是最后可能=0所以加1，为1分钱
                amount = random.nextInt(restAmount / restPeople * 2 - 1) + 1;
                list.add(amount);

                restAmount = restAmount - amount;
                restPeople = restPeople - 1;
            }
            list.add(restAmount);
        }
        return list;
    }
}
