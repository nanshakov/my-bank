package com.nanshakov.bank.services.impl;

import com.nanshakov.bank.dto.ExecutionReport;
import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SimpleOrderProcessorImpl implements SimpleOrderProcessor {

    @Override
    public ExecutionReport process(Order order) {
        log.info("{} order {} with amount {} was processed", order.getOrderType(), order.getOrderId(),
                order.getAmount());
        GroovyObject groovyObject = findClassFor(order);
        return (ExecutionReport) groovyObject.invokeMethod("result", order);
    }

    private GroovyObject findClassFor(Order order) throws IOException, IllegalAccessException, InstantiationException {
        GroovyClassLoader loader = new GroovyClassLoader();
        getGroovyClasses().forEachOrdered(f -> {
            Class orderProseccor = loader.parseClass(new File(new File(".").getAbsolutePath(), "CalcMath.groovy"));
            GroovyObject groovyObject = (GroovyObject) orderProseccor.newInstance();
            if ((boolean) groovyObject.invokeMethod("isApplying", order)) {
                return groovyObject;
            }
        });
        return null;
    }

    private Stream<File> getGroovyClasses() {
        File dir = new File(new File(new File(".").getAbsolutePath());
        FileFilter fileFilter = new WildcardFileFilter("*.txt");
        return Arrays.stream(Objects.requireNonNull(dir.listFiles(fileFilter)));
    }
}
