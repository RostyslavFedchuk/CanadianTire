package org.canadian.tire.api.service;


import org.canadian.tire.api.RestClient;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected RestClient restClient;
}
