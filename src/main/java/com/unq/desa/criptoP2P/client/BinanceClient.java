package com.unq.desa.criptoP2P.client;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url="${client.post.baseUrl}")
public interface BinanceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/price?symbol=")
    Cryptocurrency getCryptocurrency(String cryptocurrency);


}
