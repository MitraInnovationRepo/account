package com.mitralabs.customer.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.mitralabs.customer.CustomerAggregate")
public interface CustomerEvent extends Event {
}
