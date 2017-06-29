package com.voler.person.app.report;

import com.voler.annotation.ReportEvent;
import com.voler.annotation.ReportParameter;

/**
 * ReportConfig Created by voler on 2017/6/23.
 * 说明：
 */

public class ReportConfig {
    @ReportEvent
    class nishishui{
        @ReportParameter
        String fdsjalf;
        @ReportParameter("WTH")
        String fdsjadalf;
    }
}
