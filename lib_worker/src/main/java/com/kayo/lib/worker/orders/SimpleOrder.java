package com.kayo.lib.worker.orders;

import com.kayo.lib.worker.callbacks.OrderCallback;
import com.kayo.lib.worker.orders.AbsOrder;

/**
 * Kayo
 * 2019/01/12
 * 22:38
 */
public class SimpleOrder extends AbsOrder {

    public SimpleOrder() {
        super();
    }

    public SimpleOrder(OrderCallback callback) {
        super(callback);
    }
}
