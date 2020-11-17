package com.task.model.modelEnums;
/*
The status of a trip can be
    - COMPLETED: Tap ON and OFF on different stops
    - INCOMPLETE: Tap ON and never OFF
    - CANCELLED: Tap ON and OFF on the same stop
 */
public enum StatusType {
    COMPLETED,
    INCOMPLETE,
    CANCELLED
}
