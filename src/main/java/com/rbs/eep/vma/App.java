package com.rbs.eep.vma;

import io.bluebank.braid.client.BraidProxyClient;
import io.bluebank.braid.corda.serialisation.BraidCordaJacksonInit;
import io.cordite.dgl.corda.LedgerAPI;
import io.cordite.dgl.corda.account.Account.State;
import io.cordite.test.utils.BraidClientHelper;
import io.vertx.core.Vertx;
import kotlin.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        Vertx vertx = Vertx.vertx();
        BraidCordaJacksonInit.Companion.init();
        BraidProxyClient braidClient = BraidClientHelper.INSTANCE.braidClient(8080, "ledger", "emea.alpha.cordite.foundation", vertx);
        LedgerAPI ledgerService = braidClient.bind(LedgerAPI.class, this::onError, this::onCompleted);
        ledgerService.listAccounts().setHandler(result -> {
            if (result.succeeded()) {
                result.result().forEach((state) -> log.debug(state.toString()));

            } else {

            }
        });
    }


    private Unit onError(Throwable err) {
        return null;
    }

    private Unit onCompleted() {
        return null;
    }

}
