package com.forezp.config.webflux;

import org.springframework.core.codec.Decoder;
import org.springframework.http.codec.DecoderHttpMessageReader;

public class HttpMessageReader extends DecoderHttpMessageReader<Object> {
    /**
     * Create an instance wrapping the given {@link Decoder}.
     *
     * @param decoder
     */
    public HttpMessageReader(Decoder<Object> decoder) {
        super(decoder);
    }
}
