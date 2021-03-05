package com.softeem.service.impl;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PositionServiceImplTest {

    @Test
    public void toggleStatus() {
        PositionServiceImplTest positionServiceImplTest = new PositionServiceImplTest();
        try {
            positionServiceImplTest.toExp();
//          throw new HHException("sssd");
        }catch (HHException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void toExp() throws HHException{
        throw new HHException("sssd");
    }
}
class HHException extends Exception{
    public HHException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public HHException(String message) {
        super(message);
    }
}