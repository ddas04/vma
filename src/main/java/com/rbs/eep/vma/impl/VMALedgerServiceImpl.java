package com.rbs.eep.vma.impl;

import com.rbs.eep.vma.App;
import com.rbs.eep.vma.VMALedgerService;
import io.bluebank.braid.client.BraidProxyClient;
import io.bluebank.braid.corda.serialisation.BraidCordaJacksonInit;
import io.cordite.dgl.corda.LedgerAPI;
import io.cordite.dgl.corda.account.Account;
import io.cordite.test.utils.BraidClientHelper;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import kotlin.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VMALedgerServiceImpl implements VMALedgerService {
    private static Logger log = LoggerFactory.getLogger(VMALedgerServiceImpl.class);
    
    public static final int PORT = 8080;
    public static final String SERVICE_NAME = "ledger";
    public static final String HOST = "emea.alpha.cordite.foundation";
        

    @Override
    public List listAccounts() {

        Vertx vertx = Vertx.vertx();
        BraidCordaJacksonInit.Companion.init();
        BraidProxyClient braidClient = BraidClientHelper.INSTANCE.braidClient(PORT, SERVICE_NAME, HOST, vertx);
        LedgerAPI ledgerService = braidClient.bind(LedgerAPI.class, this::onError, this::onCompleted);

        ledgerService.listAccounts().setHandler(result -> {
            if (result.succeeded()) {
                 result.result(); //this is not correct at all
            } else {
            }

        });
      return null;
    }

    private Unit onError(Throwable err) {
        return null;
    }

    private Unit onCompleted() {
        return null;
    }
}
