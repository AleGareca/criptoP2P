package com.unq.desa.criptoP2P.client;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="BinanceClient",url="${client.post.baseUrl}")
public interface BinanceClient {
    @GetMapping(value = "/price?symbol=")
    public Cryptocurrency getCryptocurrency(String cryptocurrency);


}
