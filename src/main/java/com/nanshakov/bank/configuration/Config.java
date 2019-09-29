package com.nanshakov.bank.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author Nikita Anshakov
 * @version 20.09.2019
 * @since 20.09.2019
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Config {

}
