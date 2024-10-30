package org.canadian.tire.ui.business;

import org.canadian.tire.spring.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneralBO {

    @Autowired
    protected PropertyManager propertyManager;
}
