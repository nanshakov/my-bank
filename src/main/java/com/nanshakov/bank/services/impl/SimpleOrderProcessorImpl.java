package com.nanshakov.bank.services.impl;

import com.nanshakov.bank.dto.ExecutionReport;
import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.validation.constraints.Null;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SimpleOrderProcessorImpl implements SimpleOrderProcessor {

    @Value("${folderWithScripts}")
    private String folderWithScripts;

    @Override
    public ExecutionReport process(Order order) {
        log.info("Processing {} order {} with amount {}", order.getOrderType(), order.getOrderId(),
                order.getAmount());
        GroovyObject groovyObject = findClassFor(order);
        if (groovyObject == null) {
            log.error("Can't find applicable method to process order = {}", order);
            throw new IllegalStateException("Can't find applicable method to process order");
        }
        return (ExecutionReport) groovyObject.invokeMethod("result", order);
    }

    private GroovyObject findClassFor(Order order) {
        GroovyClassLoader loader = new GroovyClassLoader();
        File[] files = getGroovyClasses();
        if (files == null) {
            log.error("Folder {} is empty!", folderWithScripts);
            return null;
        }
        log.debug("Find {} files", files.length);
        for (File file : files) {
            Class orderProcessor;
            try {
                orderProcessor = loader.parseClass(file);
                GroovyObject groovyObject = (GroovyObject) orderProcessor.newInstance();
                if ((boolean) groovyObject.invokeMethod("isApplying", order)) {
                    log.info("Using {} to process order {}", groovyObject.getClass(), order);
                    return groovyObject;
                }
            } catch (IOException | InstantiationException | IllegalAccessException e) {
                log.error(e);
            }
        }
        return null;
    }

    @Null
    private File[] getGroovyClasses() {
        File dir = new File(new File("").getAbsolutePath() + File.separator + folderWithScripts);
        FileFilter fileFilter = new WildcardFileFilter("*.groovy");
        return dir.listFiles(fileFilter);
    }
}
