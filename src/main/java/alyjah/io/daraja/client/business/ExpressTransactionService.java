package alyjah.io.daraja.client.business;

import alyjah.io.daraja.client.model.MpesaExpressTransactions;

import java.math.BigDecimal;

public interface ExpressTransactionService {

    MpesaExpressTransactions update(String checkoutRequestID, String merchantRequestID, int resultCode, String resultDesc);

    void save(String checkoutRequestID, String merchantRequestID, BigDecimal amount, String phoneNumber, String accountReference, String transactionTimestamp);
}
