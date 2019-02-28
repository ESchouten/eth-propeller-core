package org.adridadou.ethereum.propeller.event;

import org.adridadou.ethereum.propeller.values.TransactionInfo;
import io.reactivex.Observable;

import java.util.concurrent.CompletableFuture;

/**
 * Created by davidroon on 27.04.16.
 * This code is released under Apache 2 license
 */
public class EthereumEventHandler {
    private final CompletableFuture<Void> ready = new CompletableFuture<>();
    private final OnBlockHandler onBlockHandler;
    private final OnTransactionHandler onTransactionHandler;
    private long currentBlockNumber;

    public EthereumEventHandler() {
        this.onBlockHandler = new OnBlockHandler();
        this.onTransactionHandler = new OnTransactionHandler();
    }

    public void onBlock(BlockInfo block) {
        onBlockHandler.newElement(block);
        currentBlockNumber = block.blockNumber;
    }

    public void onTransactionExecuted(TransactionInfo tx) {
        onTransactionHandler.on(tx);
    }

    public void onTransactionDropped(TransactionInfo tx) {
        onTransactionHandler.on(tx);
    }

    public void onReady() {
        ready.complete(null);
    }

    public CompletableFuture<Void> ready() {
        return ready;
    }

    public long getCurrentBlockNumber() {
        return currentBlockNumber;
    }

    public Observable<BlockInfo> observeBlocks() {
        return onBlockHandler.observable;
    }

    public Observable<TransactionInfo> observeTransactions() {
        return onTransactionHandler.observable;
    }
}
