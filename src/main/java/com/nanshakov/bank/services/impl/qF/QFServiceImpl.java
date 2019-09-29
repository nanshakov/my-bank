package com.nanshakov.bank.services.impl.qF;

import com.nanshakov.bank.services.SimpleOrderProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import quickfix.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class QFServiceImpl implements Application {

    @Autowired
    SimpleOrderProcessor orderProcessor;

    private Acceptor acceptor;
    private final ConcurrentHashMap<SessionID, List<Message>> history = new ConcurrentHashMap<>();

    @PostConstruct
    void postConstruct() throws ConfigError {
        SessionSettings settings = new SessionSettings(QFServiceImpl.class.getResourceAsStream("/fix.properties"));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        acceptor = new SocketAcceptor(this, storeFactory, settings, logFactory, messageFactory);
        acceptor.start();
    }

    @PreDestroy
    void preDestroy() {
        acceptor.stop();
    }

    @Override
    public void onCreate(SessionID sessionID) {

    }

    @Override
    public void onLogon(SessionID sessionID) {
        log.info("client {} is logon", sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        log.info("client {} is logout", sessionID);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        history.computeIfAbsent(sessionID, x -> new LinkedList<>()).add(message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) {
        history.computeIfAbsent(sessionID, x -> new LinkedList<>()).add(message);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) {
        history.computeIfAbsent(sessionID, x -> new LinkedList<>()).add(message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) {
        history.computeIfAbsent(sessionID, x -> new LinkedList<>()).add(message);
    }

    public ConcurrentHashMap<SessionID, List<Message>> getHistory() {
        return history;
    }
}
