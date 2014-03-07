package com.webstersmalley.wages.service;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
public class TestFakeDataService extends AbstractSpringAwareBase {


    @Resource
    private TimeSheetEntryService timeSheetEntryService;

    @Test
    public void testFakeDataService() {
        int i = timeSheetEntryService.findAll().size();
        fakeDataService.addFakeData();
        Assert.assertTrue(timeSheetEntryService.findAll().size() > i);
    }
}
