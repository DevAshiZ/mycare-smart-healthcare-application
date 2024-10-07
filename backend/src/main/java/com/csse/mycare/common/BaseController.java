package com.csse.mycare.common;

import com.csse.mycare.masterservice.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    protected MasterService masterService;
}
