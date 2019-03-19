package org.adridadou.ethereum.propeller.solidity;

import org.adridadou.ethereum.propeller.solidity.abi.AbiEntry;
import org.adridadou.ethereum.propeller.solidity.converters.decoders.SolidityTypeDecoder;
import org.adridadou.ethereum.propeller.values.EventData;

import java.util.List;

/**
 * Created by davidroon on 02.04.17.
 * This code is released under Apache 2 license
 */
public class SolidityEvent<T> {
    private final AbiEntry description;
    private final List<List<SolidityTypeDecoder>> decoders;
    private final Class<T> entityClass;
    private final List<Class<?>> eventParameters;;

    public SolidityEvent(AbiEntry description, List<List<SolidityTypeDecoder>> decoders, List<Class<?>> eventParameters) {
        this.description = description;
        this.decoders = decoders;
        this.entityClass = null;
        this.eventParameters = eventParameters;
    }

    public SolidityEvent(AbiEntry description, List<List<SolidityTypeDecoder>> decoders, Class<T> entityClass) {
        this.description = description;
        this.decoders = decoders;
        this.entityClass = entityClass;
        this.eventParameters = null;
    }

    public boolean match(EventData data) {
        return data.getEventSignature().equals(description.signature()) || data.getEventSignature().equals(description.signatureLong());
    }

    public List<Object> parseParameters(EventData eventData) {
        return description.decodeParameters(eventData, decoders, eventParameters);
    }

    public T parseEvent(EventData eventData, Class<T> clsResult) {
        return (T) description.decode(eventData, decoders, clsResult);
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public boolean rawDefinition() {
        return entityClass == null;
    }
}
