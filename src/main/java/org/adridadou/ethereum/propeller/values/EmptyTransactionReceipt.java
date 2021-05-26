package org.adridadou.ethereum.propeller.values;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by felipe.forbeck on 20.02.19.
 * This code is released under Apache 2 license
 */
public class EmptyTransactionReceipt extends TransactionReceipt {

    public EmptyTransactionReceipt() {
        super(EthHash.empty(), EthHash.empty(), Optional.empty(), EthAddress.empty(), EthAddress.empty(), EthAddress.empty(), EthData.empty(), "", EthData.empty(), false, Collections.EMPTY_LIST, EthValue.ether(0l));
    }
}
