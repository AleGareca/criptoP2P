package com.unq.desa.criptoP2P;

import com.unq.desa.criptoP2P.service.IQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schuduled {

        @Autowired
        private IQuotationService quotationService;

        @Scheduled(fixedRate = 200000)
        public void quotesUppdate() {
            System.out.println(
                    "Fixed delay task - " + System.currentTimeMillis() / 600000);
            this.quotationService.quotesUpdate();
        }
}
