package com.github.joraclista.kraken;

import com.github.joraclista.kraken.auth.Auth;
import com.github.joraclista.kraken.model.request.KrakenSyncRequestImpl.SingleResizeRequestImpl;
import com.github.joraclista.kraken.model.response.AbstractKrakenResponse.SingleResizeResponseImpl;
import junitparams.JUnitParamsRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by Alisa
 * version 1.0.
 */
@RunWith(JUnitParamsRunner.class)
@Slf4j
public class KrakenApiAuthTest extends BaseTest {

    @Test
    public void wrongAuthData() {
        SingleResizeResponseImpl response = getKrakenApi().post(SingleResizeRequestImpl.builder()
                .auth(new Auth("anythig", "anythig"))
                .url(getImageOriginalUrl())
                .lossy(true)
                .build());
        assertFalse(response.isSuccess());
        assertNotNull(response.getMessage());
        assertEquals(response.getHttpStatusCode(), Integer.valueOf(UNAUTHORIZED.value()));
        assertEquals(response.getHttpStatusText(), UNAUTHORIZED.getReasonPhrase());
        assertTrue(response.getMessage().contains("Unnknown API Key"));
    }

    @Test
    public void correctAuthData() {
        SingleResizeResponseImpl response = getKrakenApi().post(SingleResizeRequestImpl.builder()
                .auth(getAuth())
                .url(getImageOriginalUrl())
                .lossy(true)
                .build());

        assertTrue(response.isSuccess());
        assertNull(response.getMessage());
        assertEquals(response.getHttpStatusCode(), Integer.valueOf(OK.value()));
        assertEquals(response.getHttpStatusText(), OK.getReasonPhrase());
    }

}
