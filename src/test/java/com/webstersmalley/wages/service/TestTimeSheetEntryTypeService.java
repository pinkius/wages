package com.webstersmalley.wages.service;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
public class TestTimeSheetEntryTypeService extends AbstractSpringAwareBase {

    @Test
    public void testTimeSheetEntryTypeAdmin() {
        int count = timeSheetEntryTypeService.findAll().size();
        timeSheetEntryTypeService.save(new TimeSheetEntryType("Day Shift", new BigDecimal(5.00)));
        Assert.assertTrue(count < timeSheetEntryTypeService.findAll().size());
    }
}
