package org.amaap.task.bookinventory.sales;

import org.junit.jupiter.api.Test;

class SalesManagerTest {

    private SalesManager salesManager;



    @Test
    void shouldAbleToCalculateTotalAmountOfBooks() {

        salesManager.getTotalAmount("Design Pattern", 23);
           

    }


}